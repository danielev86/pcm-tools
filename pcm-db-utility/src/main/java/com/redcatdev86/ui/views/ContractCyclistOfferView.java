package com.redcatdev86.ui.views;

import com.redcatdev86.service.ContractCyclistOfferService;
import com.redcatdev86.ui.model.ContractCyclistOfferBean;
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

public class ContractCyclistOfferView implements AppView {

    private final BorderPane root = new BorderPane();
    private final TableView<ContractCyclistOfferBean> table = new TableView<>();

    private final ObservableList<ContractCyclistOfferBean> master = FXCollections.observableArrayList();
    private final FilteredList<ContractCyclistOfferBean> filtered = new FilteredList<>(master, x -> true);

    private final ContractCyclistOfferService service = new ContractCyclistOfferService();
    private final Set<Integer> modifiedIds = new LinkedHashSet<>();

    private TextField txtCyclist;
    private TextField txtTeam;
    private Button btnSaveAll;

    public ContractCyclistOfferView() {
        root.setPadding(new Insets(12));
        root.setTop(buildTop());
        root.setCenter(buildCenter());
        loadData();
    }

    @Override
    public String getTitle() {
        return "Contract Cyclist Offer";
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
        txtCyclist.setPromptText("type name...");
        txtCyclist.setPrefWidth(240);
        txtCyclist.textProperty().addListener((o, a, b) -> applyFilters());

        Label lblTeam = new Label("Actual Team:");
        txtTeam = new TextField();
        txtTeam.setPromptText("type team...");
        txtTeam.setPrefWidth(240);
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

        TableColumn<ContractCyclistOfferBean, Integer> colId = new TableColumn<>("Offer ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("idContractOffer"));
        colId.setPrefWidth(90);

        TableColumn<ContractCyclistOfferBean, String> colCyclist = new TableColumn<>("Cyclist");
        colCyclist.setCellValueFactory(new PropertyValueFactory<>("cyclistFullName"));
        colCyclist.setPrefWidth(240);

        TableColumn<ContractCyclistOfferBean, String> colTeam = new TableColumn<>("Actual Team");
        colTeam.setCellValueFactory(new PropertyValueFactory<>("actualTeam"));
        colTeam.setPrefWidth(240);

        IntegerStringConverter intConv = new IntegerStringConverter();

        // ✅ Editable fields:
        TableColumn<ContractCyclistOfferBean, Integer> colWage = editableIntCol("Wage", "periodWage", intConv);
        TableColumn<ContractCyclistOfferBean, Integer> colYears = editableIntCol("Years", "numYears", intConv);
        TableColumn<ContractCyclistOfferBean, Integer> colFinal = editableIntCol("Final (0/1)", "finalFlag", intConv);
        TableColumn<ContractCyclistOfferBean, Integer> colResolve = editableIntCol("Resolve Date", "dateResolve", intConv);
        TableColumn<ContractCyclistOfferBean, Integer> colPatience = editableIntCol("Patience", "patienceTries", intConv);
        TableColumn<ContractCyclistOfferBean, Integer> colRole = editableIntCol("Role", "role", intConv);

        table.getColumns().setAll(
                colId, colCyclist, colTeam,
                colWage, colYears, colFinal, colResolve, colPatience, colRole
        );

        SortedList<ContractCyclistOfferBean> sorted = new SortedList<>(filtered);
        sorted.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sorted);

        btnSaveAll = new Button("Save");
        btnSaveAll.setDisable(true);
        btnSaveAll.setOnAction(e -> saveAllUpdates());

        HBox bottom = new HBox(btnSaveAll);
        bottom.setPadding(new Insets(12, 0, 0, 0));

        return new VBox(8, table, bottom);
    }

    private TableColumn<ContractCyclistOfferBean, Integer> editableIntCol(
            String title, String property, IntegerStringConverter conv) {

        TableColumn<ContractCyclistOfferBean, Integer> col = new TableColumn<>(title);
        col.setCellValueFactory(new PropertyValueFactory<>(property));
        col.setCellFactory(TextFieldTableCell.forTableColumn(conv));
        col.setOnEditCommit(evt -> {
            ContractCyclistOfferBean b = evt.getRowValue();
            Integer v = evt.getNewValue();

            switch (property) {
                case "periodWage" -> b.setPeriodWage(v);
                case "numYears" -> b.setNumYears(v);
                case "finalFlag" -> b.setFinalFlag(v);
                case "dateResolve" -> b.setDateResolve(v);
                case "patienceTries" -> b.setPatienceTries(v);
                case "role" -> b.setRole(v);
            }

            if (b.getIdContractOffer() != null) {
                modifiedIds.add(b.getIdContractOffer());
                btnSaveAll.setDisable(modifiedIds.isEmpty());
            }
        });
        return col;
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

    private void saveAllUpdates() {
        try {
            int saved = 0;

            for (ContractCyclistOfferBean b : master) {
                if (b.getIdContractOffer() != null && modifiedIds.contains(b.getIdContractOffer())) {
                    service.updateEditableFields(b);
                    saved++;
                }
            }

            modifiedIds.clear();
            btnSaveAll.setDisable(true);

            Alert ok = new Alert(Alert.AlertType.INFORMATION);
            ok.setHeaderText("Success");
            ok.setContentText("Saved " + saved + " offer(s) successfully.");
            ok.showAndWait();

        } catch (Exception ex) {
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setHeaderText("Save failed");
            err.setContentText(ex.getMessage());
            err.showAndWait();
        }
    }
}