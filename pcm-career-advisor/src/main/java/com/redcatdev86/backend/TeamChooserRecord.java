package com.redcatdev86.backend;

public class TeamChooserRecord {

    private final String teamName;
    private final int divisionId;
    private final String divisionConstant;
    private final int countryId;
    private final String countryFlag;
    private final int continentId;
    private final String continentConstant;

    public TeamChooserRecord(String teamName,
                             int divisionId,
                             String divisionConstant,
                             int countryId,
                             String countryFlag,
                             int continentId,
                             String continentConstant) {
        this.teamName = teamName;
        this.divisionId = divisionId;
        this.divisionConstant = divisionConstant;
        this.countryId = countryId;
        this.countryFlag = countryFlag;
        this.continentId = continentId;
        this.continentConstant = continentConstant;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public String getDivisionConstant() {
        return divisionConstant;
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

    public String getContinentConstant() {
        return continentConstant;
    }
}