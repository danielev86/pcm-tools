package com.redcatdev86.ui.view;

import com.redcatdev86.service.TeamChooserService;
import com.redcatdev86.ui.model.TeamChooserRow;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class TeamChooserView extends VBox {

    private static final String ALL = "All";

    private final TeamChooserService service = new TeamChooserService();

    private final TableView<TeamChooserRow> tableView = new TableView<>();
    private final ComboBox<String> divisionCombo = new ComboBox<>();
    private final ComboBox<String> countryCombo = new ComboBox<>();
    private final ComboBox<String> continentCombo = new ComboBox<>();

    private List<TeamChooserRow> allRows;

    public TeamChooserView() {
        getStyleClass().addAll("page-box", "page-team-chooser");
        setAlignment(Pos.TOP_LEFT);
        setSpacing(18);

        Label title = new Label("Team Chooser");
        title.getStyleClass().add("page-title");

        Label subtitle = new Label("Filtra i team per divisione, country e continent.");
        subtitle.getStyleClass().add("page-description");

        HBox filtersBox = createFiltersBox();
        configureTable();

        allRows = service.loadRows();
        populateFilters();
        applyFilters();

        VBox.setVgrow(tableView, Priority.ALWAYS);

        getChildren().addAll(title, subtitle, filtersBox, tableView);
    }

    private HBox createFiltersBox() {
        Label divisionLabel = new Label("Division");
        divisionLabel.getStyleClass().add("filter-label");

        Label countryLabel = new Label("Country");
        countryLabel.getStyleClass().add("filter-label");

        Label continentLabel = new Label("Continent");
        continentLabel.getStyleClass().add("filter-label");

        divisionCombo.getStyleClass().add("filter-combo");
        countryCombo.getStyleClass().add("filter-combo");
        continentCombo.getStyleClass().add("filter-combo");

        divisionCombo.setPrefWidth(180);
        countryCombo.setPrefWidth(180);
        continentCombo.setPrefWidth(180);

        divisionCombo.setOnAction(e -> applyFilters());
        countryCombo.setOnAction(e -> applyFilters());
        continentCombo.setOnAction(e -> applyFilters());

        VBox divisionBox = new VBox(6, divisionLabel, divisionCombo);
        VBox countryBox = new VBox(6, countryLabel, countryCombo);
        VBox continentBox = new VBox(6, continentLabel, continentCombo);

        HBox box = new HBox(16, divisionBox, countryBox, continentBox);
        box.getStyleClass().add("filters-box");

        return box;
    }

    private void configureTable() {
        tableView.getStyleClass().add("team-table");
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        tableView.setPlaceholder(new Label("Nessun team trovato"));

        TableColumn<TeamChooserRow, BigDecimal> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreColumn.setPrefWidth(100);

        TableColumn<TeamChooserRow, String> teamColumn = new TableColumn<>("Team");
        teamColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        teamColumn.setPrefWidth(280);

        TableColumn<TeamChooserRow, String> divisionColumn = new TableColumn<>("Division");
        divisionColumn.setCellValueFactory(new PropertyValueFactory<>("division"));
        divisionColumn.setPrefWidth(150);

        TableColumn<TeamChooserRow, Integer> countryColumn = new TableColumn<>("Country");
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("countryId"));
        countryColumn.setPrefWidth(120);

        TableColumn<TeamChooserRow, String> flagColumn = new TableColumn<>("Flag");
        flagColumn.setCellValueFactory(new PropertyValueFactory<>("countryFlag"));
        flagColumn.setPrefWidth(120);

        TableColumn<TeamChooserRow, String> continentColumn = new TableColumn<>("Continent");
        continentColumn.setCellValueFactory(new PropertyValueFactory<>("continent"));
        continentColumn.setPrefWidth(160);

        tableView.getColumns().setAll(
                scoreColumn,
                teamColumn,
                divisionColumn,
                countryColumn,
                flagColumn,
                continentColumn
        );
    }

    private void populateFilters() {
        List<String> divisions = allRows.stream()
                .map(TeamChooserRow::getDivision)
                .filter(s -> s != null && !s.isBlank())
                .distinct()
                .sorted()
                .toList();

        List<String> countries = allRows.stream()
                .map(row -> String.valueOf(row.getCountryId()))
                .distinct()
                .sorted()
                .toList();

        List<String> continents = allRows.stream()
                .map(TeamChooserRow::getContinent)
                .filter(s -> s != null && !s.isBlank())
                .distinct()
                .sorted()
                .toList();

        divisionCombo.getItems().setAll(ALL);
        divisionCombo.getItems().addAll(divisions);

        countryCombo.getItems().setAll(ALL);
        countryCombo.getItems().addAll(countries);

        continentCombo.getItems().setAll(ALL);
        continentCombo.getItems().addAll(continents);

        divisionCombo.setValue(ALL);
        countryCombo.setValue(ALL);
        continentCombo.setValue(ALL);
    }

    private void applyFilters() {
        String selectedDivision = valueOrAll(divisionCombo.getValue());
        String selectedCountry = valueOrAll(countryCombo.getValue());
        String selectedContinent = valueOrAll(continentCombo.getValue());

        List<TeamChooserRow> filtered = allRows.stream()
                .filter(row -> ALL.equals(selectedDivision) || Objects.equals(row.getDivision(), selectedDivision))
                .filter(row -> ALL.equals(selectedCountry) || Objects.equals(String.valueOf(row.getCountryId()), selectedCountry))
                .filter(row -> ALL.equals(selectedContinent) || Objects.equals(row.getContinent(), selectedContinent))
                .sorted(Comparator
                        .comparing(TeamChooserRow::getScore, Comparator.reverseOrder())
                        .thenComparing(TeamChooserRow::getTeamName, String.CASE_INSENSITIVE_ORDER))
                .toList();

        tableView.setItems(FXCollections.observableArrayList(filtered));
    }

    private String valueOrAll(String value) {
        return value == null || value.isBlank() ? ALL : value;
    }
}