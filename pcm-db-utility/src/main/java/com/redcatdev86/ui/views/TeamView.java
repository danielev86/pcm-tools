package com.redcatdev86.ui.views;

import com.redcatdev86.service.TeamService;
import com.redcatdev86.ui.model.TeamBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.util.List;

public class TeamView implements AppView {

    private final BorderPane root = new BorderPane();
    private final TableView<TeamBean> table = new TableView<>();

    private final ObservableList<TeamBean> masterData = FXCollections.observableArrayList();
    private final FilteredList<TeamBean> filteredData = new FilteredList<>(masterData, t -> true);

    private final TeamService teamService = new TeamService();

    private TextField txtSearch;
    private ComboBox<String> cmbLicensed;

    public TeamView() {
        root.setPadding(new Insets(12));
        root.setTop(buildTop());
        root.setCenter(buildTable());

        loadData();
        applyFilters();
    }

    @Override
    public String getTitle() {
        return "View Team";
    }

    @Override
    public Node getRoot() {
        return root;
    }

    // =========================
    // TOP (Title + Filters + Actions)
    // =========================

    private Node buildTop() {

        Label title = new Label(getTitle());
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // --- Filters ---
        Label lblSearch = new Label("Search:");
        txtSearch = new TextField();
        txtSearch.setPrefWidth(260);
        txtSearch.setPromptText("name / short name / abbr...");
        txtSearch.textProperty().addListener((obs, o, n) -> applyFilters());

        Label lblLicensed = new Label("Licensed:");
        cmbLicensed = new ComboBox<>();
        cmbLicensed.getItems().setAll("All", "Licensed (1)", "Not Licensed (0)");
        cmbLicensed.setValue("All");
        cmbLicensed.valueProperty().addListener((obs, o, n) -> applyFilters());

        HBox filters = new HBox(14, lblSearch, txtSearch, lblLicensed, cmbLicensed);
        filters.setPadding(new Insets(10, 0, 14, 0));

        // --- Actions ---
        Button btnSave = new Button("Save Selected");
        btnSave.setOnAction(e -> onSaveSelected());

        Button btnReload = new Button("Reload");
        btnReload.setOnAction(e -> loadData());

        // disable save if nothing selected
        btnSave.disableProperty().bind(
                table.getSelectionModel().selectedItemProperty().isNull()
        );

        HBox actions = new HBox(10, btnSave, btnReload);
        actions.setPadding(new Insets(0, 0, 10, 0));

        return new VBox(6, title, filters, actions, new Separator());
    }

    // =========================
    // TABLE
    // =========================

    private Node buildTable() {

        table.setEditable(true);
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        SortedList<TeamBean> sorted = new SortedList<>(filteredData);
        sorted.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sorted);

        StringConverter<Integer> safeInt = new StringConverter<>() {
            @Override public String toString(Integer v) { return v == null ? "" : String.valueOf(v); }
            @Override public Integer fromString(String s) {
                if (s == null || s.trim().isEmpty()) return 0;
                try { return Integer.parseInt(s.trim()); } catch (Exception ex) { return 0; }
            }
        };

        StringConverter<Double> safeDouble = new StringConverter<>() {
            @Override public String toString(Double v) { return v == null ? "" : String.valueOf(v); }
            @Override public Double fromString(String s) {
                if (s == null || s.trim().isEmpty()) return 0.0;
                try { return Double.parseDouble(s.trim()); } catch (Exception ex) { return 0.0; }
            }
        };

        table.getColumns().setAll(
                colInt("ID", "idTeam", 80, false, safeInt),

                colStr("Short Name", "shortName", 160),
                colStr("Name", "name", 240),
                colStr("Jersey Abbr", "jerseyAbbreviation", 140),
                colStr("Abbreviation", "abbreviation", 140),

                colInt("Licensed (0/1)", "licensed", 130, true, safeInt),
                colInt("Country", "fkIdCountry", 110, true, safeInt),
                colStr("Suffix Mail", "suffixeMail", 180),
                colStr("Manager General", "managerGeneral", 200),

                colInt("Division", "fkIdDivision", 110, true, safeInt),
                colInt("Next Div", "fkIdNextDivision", 110, true, safeInt),
                colInt("Prev Div", "fkIdPrevDivision", 110, true, safeInt),

                colInt("Race", "fkIdRace", 110, true, safeInt),
                colInt("PreRace Team", "preraceTeam", 130, true, safeInt),

                colInt("Selected (0/1)", "selected", 130, true, safeInt),
                colStr("Constant", "constant", 160),

                colInt("Calendar1", "fkIdCalendar1", 120, true, safeInt),
                colInt("Calendar2", "fkIdCalendar2", 120, true, safeInt),
                colInt("Calendar3", "fkIdCalendar3", 120, true, safeInt),

                colDouble("Eval Current", "currentEvaluation", 140, true, safeDouble),
                colDouble("Eval Prev", "prevYearEvaluation", 140, true, safeDouble),
                colDouble("Eval Next", "nextYearEvaluation", 140, true, safeDouble),

                colInt("Sponsor Future", "sponsorFuture", 140, true, safeInt),
                colInt("Default Pick (0/1)", "defaultPicking", 160, true, safeInt),
                colInt("Transf Budget MinY", "transferEvoBudgetMinYear", 170, true, safeInt),
                colInt("Transf RiderType MinY", "transferEvoRiderTypeMinYear", 190, true, safeInt),

                colInt("RiderType Distrib", "fkIdTeamRiderTypeDistrib", 160, true, safeInt),

                colStr("Race Like", "raceLike", 200),
                colStr("Race Dislike", "raceDislike", 200),

                colInt("Budget", "budget", 120, true, safeInt),
                colStr("Color", "color", 140),
                colDouble("RiderType Importance", "riderTypeImportance", 180, true, safeDouble),
                colStr("Secondary Color", "secondaryColor", 160),
                colInt("YearBudgetUpdate", "yearBudgetUpdate", 160, true, safeInt)
        );

        return table;
    }

    // =========================
    // SAVE SELECTED
    // =========================

    private void onSaveSelected() {

        table.edit(-1, null);

        TeamBean selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        String teamLabel = safe(selected.getName());
        if (teamLabel.isBlank()) teamLabel = safe(selected.getShortName());
        if (teamLabel.isBlank()) teamLabel = "ID " + selected.getIdTeam();

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Save");
        confirm.setHeaderText("Save selected team?");
        confirm.setContentText("This will update: " + teamLabel);

        ButtonType ok = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirm.getButtonTypes().setAll(ok, cancel);

        var res = confirm.showAndWait();
        if (res.isEmpty() || res.get() != ok) return;

        try {
            teamService.saveTeam(selected);

            Alert done = new Alert(Alert.AlertType.INFORMATION);
            done.setTitle("Saved");
            done.setHeaderText("Saved successfully");
            done.setContentText("Team updated.");
            done.showAndWait();

        } catch (Exception ex) {
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setTitle("Error");
            err.setHeaderText("Save failed");
            err.setContentText(ex.getMessage());
            err.showAndWait();
        }
    }

    // =========================
    // DATA
    // =========================

    private void loadData() {
        List<TeamBean> teams = teamService.getAllTeams();
        masterData.setAll(teams);
        applyFilters();
    }

    private void applyFilters() {

        String q = txtSearch == null ? "" : txtSearch.getText().trim().toLowerCase();
        String lic = cmbLicensed == null ? "All" : cmbLicensed.getValue();

        filteredData.setPredicate(t -> {

            boolean textOk = q.isEmpty()
                    || safe(t.getName()).toLowerCase().contains(q)
                    || safe(t.getShortName()).toLowerCase().contains(q)
                    || safe(t.getAbbreviation()).toLowerCase().contains(q)
                    || safe(t.getJerseyAbbreviation()).toLowerCase().contains(q);

            if (!textOk) return false;

            if ("Licensed (1)".equals(lic)) return t.getLicensed() == 1;
            if ("Not Licensed (0)".equals(lic)) return t.getLicensed() == 0;

            return true;
        });
    }

    // =========================
    // COLUMN HELPERS
    // =========================

    private TableColumn<TeamBean, String> colStr(String title, String prop, double w) {
        TableColumn<TeamBean, String> c = new TableColumn<>(title);
        c.setPrefWidth(w);
        c.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>(prop));
        c.setCellFactory(TextFieldTableCell.forTableColumn());
        return c;
    }

    private TableColumn<TeamBean, Integer> colInt(String title, String prop, double w, boolean editable, StringConverter<Integer> conv) {
        TableColumn<TeamBean, Integer> c = new TableColumn<>(title);
        c.setPrefWidth(w);
        c.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>(prop));
        if (editable) c.setCellFactory(TextFieldTableCell.forTableColumn(conv));
        return c;
    }

    private TableColumn<TeamBean, Double> colDouble(String title, String prop, double w, boolean editable, StringConverter<Double> conv) {
        TableColumn<TeamBean, Double> c = new TableColumn<>(title);
        c.setPrefWidth(w);
        c.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>(prop));
        if (editable) c.setCellFactory(TextFieldTableCell.forTableColumn(conv));
        return c;
    }

    private String safe(String s) {
        return s == null ? "" : s;
    }
}