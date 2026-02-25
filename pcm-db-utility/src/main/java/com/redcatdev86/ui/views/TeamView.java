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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class TeamView implements AppView {

    private final BorderPane root = new BorderPane();
    private final TableView<TeamBean> table = new TableView<>();

    private final ObservableList<TeamBean> masterData = FXCollections.observableArrayList();
    private final FilteredList<TeamBean> filteredData = new FilteredList<>(masterData, t -> true);

    private final TeamService teamService = new TeamService();

    private TextField txtSearch;

    public TeamView() {
        root.setPadding(new Insets(12));
        root.setTop(buildTopBar());
        root.setCenter(buildTable());
        loadData();
    }

    @Override
    public String getTitle() {
        return "View Team";
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
        txtSearch.setPrefWidth(280);
        txtSearch.setPromptText("name / shortname / abbreviation...");
        txtSearch.textProperty().addListener((obs, o, n) -> applyFilter());

        Button btnClear = new Button("Clear");
        btnClear.setOnAction(e -> txtSearch.clear());

        Button btnReload = new Button("Reload");
        btnReload.setOnAction(e -> loadData());

        HBox row = new HBox(10, lblSearch, txtSearch, btnClear, btnReload);
        row.setPadding(new Insets(10, 0, 16, 0));

        Separator sep = new Separator();
        return new VBox(6, title, row, sep);
    }

    private Node buildTable() {
        table.setEditable(false);

        // Helper per creare colonne velocemente
        table.getColumns().add(col("ID", "idTeam", 80));
        table.getColumns().add(col("Name", "name", 260));
        table.getColumns().add(col("Short", "shortName", 160));
        table.getColumns().add(col("Jersey Abbr", "jerseyAbbreviation", 140));
        table.getColumns().add(col("Abbr", "abbreviation", 120));
        table.getColumns().add(col("Licensed", "licensed", 90));
        table.getColumns().add(col("Country", "fkIdCountry", 90));
        table.getColumns().add(col("Email Suffix", "suffixEmail", 140));
        table.getColumns().add(col("Manager", "managerGeneral", 180));
        table.getColumns().add(col("Division", "fkIdDivision", 90));
        table.getColumns().add(col("Next Div", "fkIdNextDivision", 90));
        table.getColumns().add(col("Prev Div", "fkIdPrevDivision", 90));
        table.getColumns().add(col("Race", "fkIdRace", 80));
        table.getColumns().add(col("PreRace Team", "preRaceTeam", 110));
        table.getColumns().add(col("Selected", "selected", 90));
        table.getColumns().add(col("CONSTANT", "constant", 120));
        table.getColumns().add(col("Cal1", "fkIdCalendar1", 80));
        table.getColumns().add(col("Cal2", "fkIdCalendar2", 80));
        table.getColumns().add(col("Cal3", "fkIdCalendar3", 80));
        table.getColumns().add(col("Eval Curr", "currentEvaluation", 100));
        table.getColumns().add(col("Eval Prev", "prevYearEvaluation", 100));
        table.getColumns().add(col("Eval Next", "nextYearEvaluation", 100));
        table.getColumns().add(col("Sponsor Fut", "sponsorFuture", 110));
        table.getColumns().add(col("Default Pick", "defaultPicking", 110));
        table.getColumns().add(col("TrEvo Bud MinY", "transferEvoBudgetMinYear", 140));
        table.getColumns().add(col("TrEvo RT MinY", "transferEvoRiderTypeMinYear", 140));
        table.getColumns().add(col("RT Distrib", "fkIdTeamRiderTypeDistrib", 110));
        table.getColumns().add(col("Race Like", "raceLikeList", 160));
        table.getColumns().add(col("Race Dislike", "raceDislikeList", 160));
        table.getColumns().add(col("Budget", "budget", 100));
        table.getColumns().add(col("Color", "color", 120));
        table.getColumns().add(col("RT Importance", "riderTypeImportance", 120));
        table.getColumns().add(col("2nd Color", "secondaryColor", 120));
        table.getColumns().add(col("YearBudUpd", "yearBudgetUpdate", 110));

        SortedList<TeamBean> sorted = new SortedList<>(filteredData);
        sorted.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sorted);

        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY); // molte colonne: meglio scroll
        return table;
    }

    private <T> TableColumn<TeamBean, T> col(String title, String property, double width) {
        TableColumn<TeamBean, T> c = new TableColumn<>(title);
        c.setCellValueFactory(new PropertyValueFactory<>(property));
        c.setPrefWidth(width);
        return c;
    }

    private void loadData() {
        List<TeamBean> teams = teamService.getAllTeams();
        masterData.setAll(teams);
        applyFilter();
    }

    private void applyFilter() {
        String q = txtSearch.getText() == null ? "" : txtSearch.getText().trim().toLowerCase();

        filteredData.setPredicate(t -> {
            if (q.isEmpty()) return true;
            String name = t.getName() == null ? "" : t.getName().toLowerCase();
            String shortName = t.getShortName() == null ? "" : t.getShortName().toLowerCase();
            String abbr = t.getAbbreviation() == null ? "" : t.getAbbreviation().toLowerCase();
            return name.contains(q) || shortName.contains(q) || abbr.contains(q);
        });
    }
}