package com.redcatdev86.ui.views;

import com.redcatdev86.service.ContractCyclistService;
import com.redcatdev86.ui.model.ContractCyclistBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;

import java.util.LinkedHashSet;
import java.util.Set;

public class ContractCyclistView implements AppView {

    private final BorderPane root = new BorderPane();
    private final TableView<ContractCyclistBean> table = new TableView<>();

    private final ObservableList<ContractCyclistBean> master = FXCollections.observableArrayList();
    private final FilteredList<ContractCyclistBean> filtered = new FilteredList<>(master, x -> true);

    private final ContractCyclistService service = new ContractCyclistService();
    private final Set<Integer> modifiedIds = new LinkedHashSet<>();

    private TextField txtCyclist;
    private TextField txtTeam;
    private Button btnSaveAll;

    public ContractCyclistView() {
        root.setPadding(new Insets(12));
        root.setTop(buildTop());
        root.setCenter(buildCenter());
        loadData();
    }

    @Override
    public String getTitle() {
        return "Contract Cyclist";
    }

    @Override
    public Node getRoot() {
        return root;
    }

    private Node buildTop() {
        Label title = new Label(getTitle());
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label lblCyclist = new Label("Cyclist:");
        txtCyclist = new TextField();
        txtCyclist.setPromptText("type cyclist name...");
        txtCyclist.setPrefWidth(260);
        txtCyclist.textProperty().addListener((o, a, b) -> applyFilters());

        Label lblTeam = new Label("Actual Team:");
        txtTeam = new TextField();
        txtTeam.setPromptText("type team name...");
        txtTeam.setPrefWidth(260);
        txtTeam.textProperty().addListener((o, a, b) -> applyFilters());

        Button btnClear = new Button("Clear");
        btnClear.setOnAction(e -> {
            txtCyclist.clear();
            txtTeam.clear();
            applyFilters();
        });

        HBox row = new HBox(12, lblCyclist, txtCyclist, lblTeam, txtTeam, btnClear);
        row.setPadding(new Insets(10, 0, 16, 0));

        return new VBox(6, title, row, new Separator());
    }

    private Node buildCenter() {
        table.setEditable(true);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<ContractCyclistBean, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("idContractCyclist"));
        colId.setPrefWidth(80);

        TableColumn<ContractCyclistBean, String> colCyclist = new TableColumn<>("Cyclist");
        colCyclist.setCellValueFactory(new PropertyValueFactory<>("cyclistFullName"));
        colCyclist.setPrefWidth(240);

        TableColumn<ContractCyclistBean, String> colTeam = new TableColumn<>("Actual Team");
        colTeam.setCellValueFactory(new PropertyValueFactory<>("actualTeam"));
        colTeam.setPrefWidth(240);

        TableColumn<ContractCyclistBean, String> colPrev = new TableColumn<>("Prev Team");
        colPrev.setCellValueFactory(new PropertyValueFactory<>("prevTeam"));
        colPrev.setPrefWidth(240);

        IntegerStringConverter intConv = new IntegerStringConverter();

        // ✅ Editable only these 3
        TableColumn<ContractCyclistBean, Integer> colWage = new TableColumn<>("Wage");
        colWage.setCellValueFactory(new PropertyValueFactory<>("periodWage"));
        colWage.setCellFactory(TextFieldTableCell.forTableColumn(intConv));
        colWage.setOnEditCommit(e -> {
            ContractCyclistBean b = e.getRowValue();
            b.setPeriodWage(e.getNewValue());
            markModified(b);
        });

        TableColumn<ContractCyclistBean, Integer> colYb = new TableColumn<>("Year Begin");
        colYb.setCellValueFactory(new PropertyValueFactory<>("yearBegin"));
        colYb.setCellFactory(TextFieldTableCell.forTableColumn(intConv));
        colYb.setOnEditCommit(e -> {
            ContractCyclistBean b = e.getRowValue();
            b.setYearBegin(e.getNewValue());
            markModified(b);
        });

        TableColumn<ContractCyclistBean, Integer> colYe = new TableColumn<>("Year End");
        colYe.setCellValueFactory(new PropertyValueFactory<>("yearEnd"));
        colYe.setCellFactory(TextFieldTableCell.forTableColumn(intConv));
        colYe.setOnEditCommit(e -> {
            ContractCyclistBean b = e.getRowValue();
            b.setYearEnd(e.getNewValue());
            markModified(b);
        });

        TableColumn<ContractCyclistBean, Integer> colActive = new TableColumn<>("Active");
        colActive.setCellValueFactory(new PropertyValueFactory<>("activeContract"));
        colActive.setPrefWidth(90);

        TableColumn<ContractCyclistBean, Integer> colRole = new TableColumn<>("Role");
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colRole.setPrefWidth(90);

        table.getColumns().setAll(
                colId, colCyclist, colTeam, colPrev,
                colWage, colYb, colYe,
                colActive, colRole
        );

        SortedList<ContractCyclistBean> sorted = new SortedList<>(filtered);
        sorted.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sorted);

        // ✅ Save button under table (single batch)
        btnSaveAll = new Button("Save");
        btnSaveAll.setDisable(true);
        btnSaveAll.setOnAction(e -> saveAllUpdates());

        HBox bottom = new HBox(btnSaveAll);
        bottom.setPadding(new Insets(12, 0, 0, 0));

        return new VBox(8, table, bottom);
    }

    private void loadData() {
        master.setAll(service.getAll());
        applyFilters();
    }

    private void applyFilters() {
        String qc = txtCyclist.getText() == null ? "" : txtCyclist.getText().trim().toLowerCase();
        String qt = txtTeam.getText() == null ? "" : txtTeam.getText().trim().toLowerCase();

        filtered.setPredicate(b -> {
            if (!qc.isEmpty()) {
                String n = b.getCyclistFullName();
                if (n == null || !n.toLowerCase().contains(qc)) return false;
            }
            if (!qt.isEmpty()) {
                String t = b.getActualTeam();
                if (t == null || !t.toLowerCase().contains(qt)) return false;
            }
            return true;
        });
    }

    private void markModified(ContractCyclistBean b) {
        if (b.getIdContractCyclist() != null) {
            modifiedIds.add(b.getIdContractCyclist());
            btnSaveAll.setDisable(modifiedIds.isEmpty());
        }
    }

    private void saveAllUpdates() {
        try {
            int saved = 0;

            for (ContractCyclistBean b : master) {
                if (b.getIdContractCyclist() != null && modifiedIds.contains(b.getIdContractCyclist())) {
                    service.updateCoreFields(b);
                    saved++;
                }
            }

            modifiedIds.clear();
            btnSaveAll.setDisable(true);

            Alert ok = new Alert(Alert.AlertType.INFORMATION);
            ok.setHeaderText("Success");
            ok.setContentText("Saved " + saved + " contract(s) successfully.");
            ok.showAndWait();

        } catch (Exception ex) {
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setHeaderText("Save failed");
            err.setContentText(ex.getMessage());
            err.showAndWait();
        }
    }
}