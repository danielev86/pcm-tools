package com.redcatdev86.ui.views;

import com.redcatdev86.service.ScoutService;
import com.redcatdev86.service.TeamService;
import com.redcatdev86.ui.model.ScoutBean;
import com.redcatdev86.ui.model.TeamBean;
import javafx.beans.property.SimpleStringProperty;
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

import java.util.*;

public class ScoutView implements AppView {

    private final BorderPane root = new BorderPane();
    private final TableView<ScoutBean> table = new TableView<>();

    private final ObservableList<ScoutBean> masterData = FXCollections.observableArrayList();
    private final FilteredList<ScoutBean> filteredData = new FilteredList<>(masterData, s -> true);

    private final ObservableList<TeamBean> teamItems = FXCollections.observableArrayList();
    private final Map<Integer, String> teamNameMap = new HashMap<>();

    private final ScoutService scoutService = new ScoutService();
    private final TeamService teamService = new TeamService();

    // tracking modifiche
    private final Set<Integer> modifiedIds = new HashSet<>();

    private TextField txtSearch;
    private ComboBox<TeamBean> cmbTeam;
    private Button btnSave;

    public ScoutView() {
        root.setPadding(new Insets(12));
        root.setTop(buildTopBar());
        root.setCenter(buildTable());

        loadTeams();
        loadData();
    }

    @Override
    public String getTitle() {
        return "View Scout";
    }

    @Override
    public Node getRoot() {
        return root;
    }

    private Node buildTopBar() {

        Label title = new Label(getTitle());
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label lblSearch = new Label("Search:");
        txtSearch = new TextField();
        txtSearch.setPrefWidth(220);
        txtSearch.textProperty().addListener((obs, o, n) -> applyFilters());
        HBox searchBox = new HBox(8, lblSearch, txtSearch);

        Label lblTeam = new Label("Team:");
        cmbTeam = new ComboBox<>();
        cmbTeam.setPrefWidth(260);
        cmbTeam.valueProperty().addListener((obs, o, n) -> applyFilters());
        HBox teamBox = new HBox(8, lblTeam, cmbTeam);

        Button btnClear = new Button("Clear");
        btnClear.setOnAction(e -> {
            txtSearch.clear();
            cmbTeam.setValue(null);
            applyFilters();
        });

        btnSave = new Button("Save Changes");
        btnSave.setDisable(true);
        btnSave.setOnAction(e -> saveChanges());

        HBox filtersRow = new HBox(20, searchBox, teamBox, btnClear, btnSave);
        filtersRow.setPadding(new Insets(10, 0, 16, 0));

        Separator sep = new Separator();

        VBox topContainer = new VBox(6, title, filtersRow, sep);
        return topContainer;
    }

    private Node buildTable() {

        table.setEditable(true);

        TableColumn<ScoutBean, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("idScout"));

        TableColumn<ScoutBean, String> colFirstName = new TableColumn<>("First Name");
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<ScoutBean, String> colLastName = new TableColumn<>("Last Name");
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<ScoutBean, String> colTeamName = new TableColumn<>("Team");
        colTeamName.setCellValueFactory(cell -> {
            String name = teamNameMap.get(cell.getValue().getFkIdTeam());
            return new SimpleStringProperty(name == null ? "" : name);
        });

        // Editable Wage
        TableColumn<ScoutBean, Integer> colWage = new TableColumn<>("Wage");
        colWage.setCellValueFactory(new PropertyValueFactory<>("wage"));
        colWage.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colWage.setOnEditCommit(evt -> {
            ScoutBean bean = evt.getRowValue();
            bean.setWage(evt.getNewValue());
            modifiedIds.add(bean.getIdScout());
            btnSave.setDisable(false);
        });

        // Editable Contract End
        TableColumn<ScoutBean, Integer> colContractEnd = new TableColumn<>("Contract End");
        colContractEnd.setCellValueFactory(new PropertyValueFactory<>("contractEnd"));
        colContractEnd.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colContractEnd.setOnEditCommit(evt -> {
            ScoutBean bean = evt.getRowValue();
            bean.setContractEnd(evt.getNewValue());
            modifiedIds.add(bean.getIdScout());
            btnSave.setDisable(false);
        });

        TableColumn<ScoutBean, Integer> colTr = new TableColumn<>("TR");
        colTr.setCellValueFactory(new PropertyValueFactory<>("tr"));

        table.getColumns().setAll(
                colId, colFirstName, colLastName,
                colTeamName, colWage, colContractEnd, colTr
        );

        SortedList<ScoutBean> sorted = new SortedList<>(filteredData);
        sorted.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sorted);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return table;
    }

    private void saveChanges() {

        try {
            for (ScoutBean bean : masterData) {
                if (modifiedIds.contains(bean.getIdScout())) {
                    scoutService.updateWageAndContractEnd(
                            bean.getIdScout(),
                            bean.getWage(),
                            bean.getContractEnd()
                    );
                }
            }

            modifiedIds.clear();
            btnSave.setDisable(true);

            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Success");
            a.setContentText("Changes saved successfully.");
            a.showAndWait();

        } catch (Exception ex) {
            showError("Error saving changes", ex);
        }
    }

    private void loadData() {
        masterData.setAll(scoutService.getAllScouts());
        applyFilters();
    }

    private void loadTeams() {
        List<TeamBean> teams = teamService.getAllTeams();
        teamItems.setAll(teams);
        cmbTeam.setItems(teamItems);

        teamNameMap.clear();
        for (TeamBean t : teams) {
            teamNameMap.put(t.getIdTeam(), t.getName());
        }
    }

    private void applyFilters() {

        String query = txtSearch.getText() == null ? "" :
                txtSearch.getText().trim().toLowerCase();

        TeamBean selectedTeam = cmbTeam.getValue();
        Integer selectedTeamId = selectedTeam == null ? null : selectedTeam.getIdTeam();

        filteredData.setPredicate(s -> {

            boolean textOk = query.isEmpty()
                    || (s.getFirstName() != null && s.getFirstName().toLowerCase().contains(query))
                    || (s.getLastName() != null && s.getLastName().toLowerCase().contains(query));

            if (!textOk) return false;

            if (selectedTeamId == null) return true;

            return s.getFkIdTeam() == selectedTeamId;
        });
    }

    private void showError(String header, Exception ex) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText(header);
        a.setContentText(ex.getMessage());
        a.showAndWait();
    }
}