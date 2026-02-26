package com.redcatdev86.ui.views;

import com.redcatdev86.service.GamCareerDataService;
import com.redcatdev86.ui.model.GamCareerDataBean;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.util.StringConverter;

import java.text.DecimalFormat;

public class GamUserView implements AppView {

    private final VBox root;

    private final TableView<GamCareerDataBean> table = new TableView<>();
    private final ObservableList<GamCareerDataBean> data = FXCollections.observableArrayList();
    private final GamCareerDataService service = new GamCareerDataService();

    private final Label status = new Label();
    private final Button btnSaveAll = new Button("Save All");

    public GamUserView() {
        Label title = new Label(getTitle());
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        VBox.setMargin(title, new Insets(10, 16, 4, 16)); // poco spazio sotto

        setupTable();
        table.setItems(data);

        // --- BOTTOM BAR: message left, button right
        status.setStyle("-fx-opacity: 0.85;");
        btnSaveAll.setOnAction(e -> saveAll());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox bottomBar = new HBox(10, status, spacer, btnSaveAll);
        bottomBar.setAlignment(Pos.CENTER_LEFT);
        bottomBar.setPadding(new Insets(6, 16, 12, 16));

        // --- LAYOUT: table is compact (no extra rows) so VBox is perfect
        root = new VBox(0, title, table, bottomBar);
        VBox.setMargin(table, new Insets(0, 16, 0, 16));

        loadAll();
    }

    private void setupTable() {
        table.setEditable(true);

        // 1) NO “COLONNA VUOTA” A DESTRA: l’ultima colonna (value) si espande
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<GamCareerDataBean, Number> colUid = new TableColumn<>("UID");
        colUid.setCellValueFactory(c -> c.getValue().uidProperty());
        colUid.setMinWidth(120);
        colUid.setMaxWidth(160);

        TableColumn<GamCareerDataBean, String> colConst = new TableColumn<>("CONSTANT");
        colConst.setCellValueFactory(c -> c.getValue().constantProperty());
        colConst.setMinWidth(350);

        TableColumn<GamCareerDataBean, Double> colValue = new TableColumn<>("value");
        colValue.setCellValueFactory(c -> c.getValue().valueProperty().asObject());
        colValue.setMinWidth(160);
        colValue.setStyle("-fx-alignment: CENTER-RIGHT;");

        // 2) Editing value
        DecimalFormat df = new DecimalFormat("#.##########");
        StringConverter<Double> converter = new StringConverter<>() {
            @Override public String toString(Double v) {
                return v == null ? "" : df.format(v);
            }
            @Override public Double fromString(String s) {
                if (s == null) return null;
                String t = s.trim().replace(",", ".");
                if (t.isEmpty()) return null;
                try { return Double.parseDouble(t); } catch (Exception ex) { return null; }
            }
        };

        colValue.setCellFactory(TextFieldTableCell.forTableColumn(converter));
        colValue.setOnEditCommit(evt -> {
            GamCareerDataBean row = evt.getRowValue();
            Double newVal = evt.getNewValue();

            if (newVal == null) {
                row.undo();
                table.refresh();
                showError("Valore non valido", "Inserisci un numero valido (es. 12.5).");
                return;
            }

            row.setValue(newVal);
            status.setText(""); // reset messaggio
            table.refresh();
        });

        // Highlight dirty rows
        table.setRowFactory(tv -> new TableRow<>() {
            @Override
            protected void updateItem(GamCareerDataBean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) setStyle("");
                else if (item.isDirty()) setStyle("-fx-background-color: rgba(255,200,0,0.28);");
                else setStyle("");
            }
        });

        table.getColumns().setAll(colUid, colConst, colValue);

        // 3) NO RIGHE VUOTE SOTTO: altezza = header + (righe * cella)
        table.setFixedCellSize(24);

        // binding: quando cambia data.size(), cambia altezza tabella
        table.prefHeightProperty().bind(table.fixedCellSizeProperty().multiply(Bindings.size(data)).add(30));
        table.minHeightProperty().bind(table.prefHeightProperty());
        table.maxHeightProperty().bind(table.prefHeightProperty());
    }

    private void loadAll() {
        try {
            data.setAll(service.loadAllBeans());
        } catch (Exception ex) {
            showError("Errore DB", ex.getMessage());
        }
    }

    private void saveAll() {
        table.edit(-1, null); // commit edit aperta

        try {
            int dirtyCount = (int) data.stream().filter(GamCareerDataBean::isDirty).count();

            service.saveAllDirty(data);
            table.refresh();

            if (dirtyCount == 0) status.setText("Nessuna modifica da salvare.");
            else status.setText("Salvataggio completato (" + dirtyCount + " record).");

        } catch (Exception ex) {
            showError("Errore salvataggio", ex.getMessage());
        }
    }

    private void showError(String header, String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Errore");
        a.setHeaderText(header);
        a.setContentText(msg);
        a.showAndWait();
    }

    @Override public String getTitle() { return "Gam USER"; }
    @Override public Node getRoot() { return root; }
}