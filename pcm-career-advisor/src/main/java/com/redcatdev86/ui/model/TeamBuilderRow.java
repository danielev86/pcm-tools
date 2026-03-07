package com.redcatdev86.ui.model;

public class TeamBuilderRow {

    private final String sponsorName;
    private final int regionId;
    private final String region;
    private final int countryId;
    private final String country;
    private final String countryFlag;
    private final int continentId;
    private final String continent;

    public TeamBuilderRow(String sponsorName,
                          int regionId,
                          String region,
                          int countryId,
                          String country,
                          String countryFlag,
                          int continentId,
                          String continent) {
        this.sponsorName = sponsorName;
        this.regionId = regionId;
        this.region = region;
        this.countryId = countryId;
        this.country = country;
        this.countryFlag = countryFlag;
        this.continentId = continentId;
        this.continent = continent;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public int getRegionId() {
        return regionId;
    }

    public String getRegion() {
        return region;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getCountry() {
        return country;
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
}