package com.redcatdev86.ui.model;

import java.math.BigDecimal;

public class TeamChooserRow {

    private final String teamName;
    private final int divisionId;
    private final String division;
    private final int countryId;
    private final String countryFlag;
    private final int continentId;
    private final String continent;
    private final BigDecimal score;

    public TeamChooserRow(String teamName,
                          int divisionId,
                          String division,
                          int countryId,
                          String countryFlag,
                          int continentId,
                          String continent,
                          BigDecimal score) {
        this.teamName = teamName;
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
        this.countryFlag = countryFlag;
        this.continentId = continentId;
        this.continent = continent;
        this.score = score;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public String getDivision() {
        return division;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public int getContinentId() {
        return continentId;
    }

    public String getContinent() {
        return continent;
    }

    public BigDecimal getScore() {
        return score;
    }
}