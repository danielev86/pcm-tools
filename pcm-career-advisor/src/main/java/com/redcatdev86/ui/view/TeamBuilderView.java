package com.redcatdev86.ui.view;

import com.redcatdev86.service.TeamBuilderService;
import com.redcatdev86.ui.model.TeamBuilderRow;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TeamBuilderView extends VBox {

    private static final String ALL = "All";

    private final TeamBuilderService service = new TeamBuilderService();

    private final ComboBox<String> continentCombo = new ComboBox<>();
    private final ComboBox<String> countryCombo = new ComboBox<>();
    private final ComboBox<String> regionCombo = new ComboBox<>();

    private final VBox resultBox = new VBox(10);

    private final List<TeamBuilderRow> allRows;

    private boolean updatingFilters = false;

    public TeamBuilderView() {
        getStyleClass().addAll("page-box", "page-team-builder");
        setAlignment(Pos.TOP_LEFT);
        setSpacing(18);

        Label title = new Label("Team Builder");
        title.getStyleClass().add("page-title");

        Label subtitle = new Label("Select continent, country and region, then generate a sponsor.");
        subtitle.getStyleClass().add("page-description");

        allRows = service.loadRows();

        HBox filters = createFilters();
        Button generateButton = createGenerateButton();

        resultBox.getStyleClass().add("result-box");
        resultBox.setVisible(false);
        resultBox.setManaged(false);

        initializeFilters();

        getChildren().addAll(title, subtitle, filters, generateButton, resultBox);
    }

    private HBox createFilters() {
        Label continentLabel = new Label("Continent");
        continentLabel.getStyleClass().add("filter-label");

        Label countryLabel = new Label("Country");
        countryLabel.getStyleClass().add("filter-label");

        Label regionLabel = new Label("Region");
        regionLabel.getStyleClass().add("filter-label");

        continentCombo.getStyleClass().add("filter-combo");
        countryCombo.getStyleClass().add("filter-combo");
        regionCombo.getStyleClass().add("filter-combo");

        continentCombo.setPrefWidth(220);
        countryCombo.setPrefWidth(220);
        regionCombo.setPrefWidth(220);

        continentCombo.setOnAction(e -> {
            if (!updatingFilters) {
                onContinentChanged();
            }
        });

        countryCombo.setOnAction(e -> {
            if (!updatingFilters) {
                onCountryChanged();
            }
        });

        regionCombo.setOnAction(e -> {
            if (!updatingFilters) {
                onRegionChanged();
            }
        });

        VBox c1 = new VBox(6, continentLabel, continentCombo);
        VBox c2 = new VBox(6, countryLabel, countryCombo);
        VBox c3 = new VBox(6, regionLabel, regionCombo);

        HBox box = new HBox(16, c1, c2, c3);
        box.getStyleClass().add("filters-box");

        return box;
    }

    private Button createGenerateButton() {
        Button button = new Button("Generate Sponsor");
        button.getStyleClass().add("primary-button");
        button.setOnAction(e -> generateSponsor());
        return button;
    }

    private void initializeFilters() {
        updatingFilters = true;

        List<String> continents = allRows.stream()
                .map(TeamBuilderRow::getContinent)
                .filter(s -> !s.isBlank())
                .distinct()
                .sorted()
                .toList();

        continentCombo.getItems().setAll(ALL);
        continentCombo.getItems().addAll(continents);
        continentCombo.setValue(ALL);

        refreshCountryAndRegionByContinent(null);

        updatingFilters = false;
    }

    private void onContinentChanged() {
        String continent = valueOrAll(continentCombo.getValue());

        updatingFilters = true;
        refreshCountryAndRegionByContinent(ALL.equals(continent) ? null : continent);
        updatingFilters = false;
    }

    private void onCountryChanged() {
        String country = valueOrAll(countryCombo.getValue());

        updatingFilters = true;

        if (!ALL.equals(country)) {
            TeamBuilderRow match = allRows.stream()
                    .filter(r -> country.equals(r.getCountry()))
                    .findFirst()
                    .orElse(null);

            if (match != null) {
                continentCombo.setValue(match.getContinent());
                refreshCountryAndRegionByCountry(match.getCountry());
                countryCombo.setValue(match.getCountry());
            }
        } else {
            String continent = valueOrAll(continentCombo.getValue());
            refreshCountryAndRegionByContinent(ALL.equals(continent) ? null : continent);
        }

        updatingFilters = false;
    }

    private void onRegionChanged() {
        String region = valueOrAll(regionCombo.getValue());

        updatingFilters = true;

        if (!ALL.equals(region)) {
            TeamBuilderRow match = allRows.stream()
                    .filter(r -> region.equals(r.getRegion()))
                    .findFirst()
                    .orElse(null);

            if (match != null) {
                continentCombo.setValue(match.getContinent());
                refreshCountryAndRegionByCountry(match.getCountry());
                countryCombo.setValue(match.getCountry());
                regionCombo.setValue(match.getRegion());
            }
        } else {
            String country = valueOrAll(countryCombo.getValue());

            if (!ALL.equals(country)) {
                TeamBuilderRow match = allRows.stream()
                        .filter(r -> country.equals(r.getCountry()))
                        .findFirst()
                        .orElse(null);

                if (match != null) {
                    continentCombo.setValue(match.getContinent());
                    refreshCountryAndRegionByCountry(match.getCountry());
                    countryCombo.setValue(match.getCountry());
                }
            } else {
                String continent = valueOrAll(continentCombo.getValue());
                refreshCountryAndRegionByContinent(ALL.equals(continent) ? null : continent);
            }
        }

        updatingFilters = false;
    }

    private void refreshCountryAndRegionByContinent(String continent) {
        List<TeamBuilderRow> base = allRows.stream()
                .filter(r -> continent == null || continent.equals(r.getContinent()))
                .toList();

        List<String> countries = base.stream()
                .map(TeamBuilderRow::getCountry)
                .filter(s -> !s.isBlank())
                .distinct()
                .sorted()
                .toList();

        List<String> regions = base.stream()
                .map(TeamBuilderRow::getRegion)
                .filter(s -> !s.isBlank())
                .distinct()
                .sorted()
                .toList();

        countryCombo.getItems().setAll(ALL);
        countryCombo.getItems().addAll(countries);
        countryCombo.setValue(ALL);

        regionCombo.getItems().setAll(ALL);
        regionCombo.getItems().addAll(regions);
        regionCombo.setValue(ALL);
    }

    private void refreshCountryAndRegionByCountry(String country) {
        List<TeamBuilderRow> base = allRows.stream()
                .filter(r -> country.equals(r.getCountry()))
                .toList();

        List<String> regions = base.stream()
                .map(TeamBuilderRow::getRegion)
                .filter(s -> !s.isBlank())
                .distinct()
                .sorted()
                .toList();

        regionCombo.getItems().setAll(ALL);
        regionCombo.getItems().addAll(regions);
        regionCombo.setValue(ALL);
    }

    private void generateSponsor() {
        TeamBuilderRow sponsor = service.generateSponsor(
                valueOrAll(continentCombo.getValue()),
                valueOrAll(countryCombo.getValue()),
                valueOrAll(regionCombo.getValue())
        );

        resultBox.getChildren().clear();

        if (sponsor == null) {
            Label empty = new Label("No sponsor found for selected filters.");
            empty.getStyleClass().add("empty-result-label");
            resultBox.getChildren().add(empty);
        } else {
            Label sponsorLabel = new Label(sponsor.getSponsorName());
            sponsorLabel.getStyleClass().add("result-title");

            Label teamType = new Label("Team Type: " + randomTeamType());
            Label continent = new Label("Continent: " + sponsor.getContinent());
            Label country = new Label("Country: " + sponsor.getCountry());
            Label region = new Label("Region: " + sponsor.getRegion());
            Label flag = new Label("Flag: " + sponsor.getCountryFlag());

            teamType.getStyleClass().add("result-text");
            continent.getStyleClass().add("result-text");
            country.getStyleClass().add("result-text");
            region.getStyleClass().add("result-text");
            flag.getStyleClass().add("result-text");

            resultBox.getChildren().addAll(
                    sponsorLabel,
                    teamType,
                    continent,
                    country,
                    region,
                    flag
            );
        }

        resultBox.setVisible(true);
        resultBox.setManaged(true);
    }

    private String randomTeamType() {
        String[] values = {"World Tour", "Professional", "Continental"};
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }

    private String valueOrAll(String value) {
        return value == null || value.isBlank() ? ALL : value;
    }
}