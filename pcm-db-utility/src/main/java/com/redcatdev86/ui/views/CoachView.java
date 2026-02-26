package com.redcatdev86.ui.views;

import com.redcatdev86.service.CoachService;
import com.redcatdev86.service.TeamService;
import com.redcatdev86.ui.model.CoachBean;
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
import javafx.util.StringConverter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoachView implements AppView {

    private final BorderPane root = new BorderPane();
    private final TableView<CoachBean> table = new TableView<>();

    private final ObservableList<CoachBean> masterData = FXCollections.observableArrayList();
    private final FilteredList<CoachBean> filteredData = new FilteredList<>(masterData, c -> true);

    private final ObservableList<TeamBean> teamItems = FXCollections.observableArrayList();
    private final Map<Integer, String> teamNameMap = new HashMap<>();

    private final CoachService coachService = new CoachService();
    private final TeamService teamService = new TeamService();

    private TextField txtName;
    private TextField txtLastName;
    private ComboBox<TeamBean> cmbTeam;

    public CoachView() {
        root.setPadding(new Insets(12));
        root.setTop(buildTop());
        root.setCenter(buildTable());

        loadTeams();
        loadData();
        applyFilters();
    }

    @Override
    public String getTitle() {
        return "View Coach";
    }

    @Override
    public Node getRoot() {
        return root;
    }

    private Node buildTop() {
        Label title = new Label(getTitle());
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label lblName = new Label("First name:");
        txtName = new TextField();
        txtName.setPrefWidth(180);
        txtName.textProperty().addListener((o,a,b) -> applyFilters());

        Label lblLast = new Label("Last name:");
        txtLastName = new TextField();
        txtLastName.setPrefWidth(180);
        txtLastName.textProperty().addListener((o,a,b) -> applyFilters());

        Label lblTeam = new Label("Team:");
        cmbTeam = new ComboBox<>();
        cmbTeam.setPrefWidth(280);
        cmbTeam.setPromptText("All teams");
        cmbTeam.valueProperty().addListener((o,a,b) -> applyFilters());

        // ✅ fix: show team name in combo (no package/class)
        cmbTeam.setConverter(new StringConverter<>() {
            @Override public String toString(TeamBean t) {
                if (t == null) return "";
                String name = t.getName();
                if (name == null || name.isBlank()) name = t.getShortName();
                return name == null ? ("ID " + t.getIdTeam()) : name;
            }
            @Override public TeamBean fromString(String s) {
                return null; // not used
            }
        });

        // optional: show team name in the dropdown list too
        cmbTeam.setCellFactory(list -> new ListCell<>() {
            @Override protected void updateItem(TeamBean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    String name = item.getName();
                    if (name == null || name.isBlank()) name = item.getShortName();
                    setText(name == null ? ("ID " + item.getIdTeam()) : name);
                }
            }
        });

        HBox filters = new HBox(14, lblName, txtName, lblLast, txtLastName, lblTeam, cmbTeam);
        filters.setPadding(new Insets(10, 0, 14, 0));

        Button btnSave = new Button("Save Selected");
        btnSave.setOnAction(e -> onSaveSelected());
        btnSave.disableProperty().bind(table.getSelectionModel().selectedItemProperty().isNull());

        Button btnReload = new Button("Reload");
        btnReload.setOnAction(e -> loadData());

        HBox actions = new HBox(10, btnSave, btnReload);
        actions.setPadding(new Insets(0, 0, 10, 0));

        return new VBox(6, title, filters, actions, new Separator());
    }

    private Node buildTable() {
        table.setEditable(true);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        SortedList<CoachBean> sorted = new SortedList<>(filteredData);
        sorted.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sorted);

        StringConverter<Integer> safeInt = new StringConverter<>() {
            @Override public String toString(Integer v) { return v == null ? "" : String.valueOf(v); }
            @Override public Integer fromString(String s) {
                if (s == null || s.trim().isEmpty()) return 0;
                try { return Integer.parseInt(s.trim()); } catch (Exception ex) { return 0; }
            }
        };

        // --- Minimal columns ---
        TableColumn<CoachBean, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("idCoach"));
        colId.setPrefWidth(70);

        TableColumn<CoachBean, String> colFirst = new TableColumn<>("First name");
        colFirst.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colFirst.setPrefWidth(170);

        TableColumn<CoachBean, String> colLast = new TableColumn<>("Last name");
        colLast.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colLast.setPrefWidth(200);

        TableColumn<CoachBean, String> colTeam = new TableColumn<>("Team");
        colTeam.setCellValueFactory(cell -> {
            String name = teamNameMap.get(cell.getValue().getFkIdTeam());
            return new SimpleStringProperty(name == null ? "" : name);
        });
        colTeam.setPrefWidth(280);

        TableColumn<CoachBean, Integer> colWork = new TableColumn<>("Work amount");
        colWork.setCellValueFactory(new PropertyValueFactory<>("workAmount"));
        colWork.setPrefWidth(120);
        colWork.setCellFactory(TextFieldTableCell.forTableColumn(safeInt));

        TableColumn<CoachBean, Integer> colWage = new TableColumn<>("Wage");
        colWage.setCellValueFactory(new PropertyValueFactory<>("wage"));
        colWage.setPrefWidth(120);
        colWage.setCellFactory(TextFieldTableCell.forTableColumn(safeInt));

        TableColumn<CoachBean, Integer> colEnd = new TableColumn<>("Contract end");
        colEnd.setCellValueFactory(new PropertyValueFactory<>("contractEnd"));
        colEnd.setPrefWidth(140);
        colEnd.setCellFactory(TextFieldTableCell.forTableColumn(safeInt));

        table.getColumns().setAll(colId, colFirst, colLast, colTeam, colWork, colWage, colEnd);

        return table;
    }

    private void loadData() {
        List<CoachBean> coaches = coachService.getAllCoaches();
        masterData.setAll(coaches);
        applyFilters();
    }

    private void loadTeams() {
        List<TeamBean> teams = teamService.getAllTeams();
        teamItems.setAll(teams);
        cmbTeam.setItems(teamItems);

        teamNameMap.clear();
        for (TeamBean t : teams) {
            String name = t.getName();
            if (name == null || name.isBlank()) name = t.getShortName();
            teamNameMap.put(t.getIdTeam(), name == null ? "" : name);
        }
    }

    private void applyFilters() {
        String fn = txtName.getText() == null ? "" : txtName.getText().trim().toLowerCase();
        String ln = txtLastName.getText() == null ? "" : txtLastName.getText().trim().toLowerCase();
        TeamBean selectedTeam = cmbTeam.getValue();
        Integer selectedTeamId = selectedTeam == null ? null : selectedTeam.getIdTeam();

        filteredData.setPredicate(c -> {
            if (!fn.isEmpty() && (c.getFirstName() == null || !c.getFirstName().toLowerCase().contains(fn)))
                return false;

            if (!ln.isEmpty() && (c.getLastName() == null || !c.getLastName().toLowerCase().contains(ln)))
                return false;

            if (selectedTeamId != null && c.getFkIdTeam() != selectedTeamId)
                return false;

            return true;
        });
    }

    private void onSaveSelected() {
        table.edit(-1, null);

        CoachBean selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        String label = (safe(selected.getFirstName()) + " " + safe(selected.getLastName())).trim();
        if (label.isBlank()) label = "ID " + selected.getIdCoach();

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Save");
        confirm.setHeaderText("Save selected coach?");
        confirm.setContentText("This will update: " + label);
        ButtonType ok = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirm.getButtonTypes().setAll(ok, cancel);

        var res = confirm.showAndWait();
        if (res.isEmpty() || res.get() != ok) return;

        try {
            coachService.saveEditable(selected);

            Alert done = new Alert(Alert.AlertType.INFORMATION);
            done.setTitle("Saved");
            done.setHeaderText("Saved successfully");
            done.setContentText("Coach updated.");
            done.showAndWait();

        } catch (Exception ex) {
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setTitle("Error");
            err.setHeaderText("Save failed");
            err.setContentText(ex.getMessage());
            err.showAndWait();
        }
    }

    private String safe(String s) {
        return s == null ? "" : s;
    }
}