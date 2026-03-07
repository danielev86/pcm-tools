package com.redcatdev86.backend;

public class TeamBuilderRecord {

    private final String sponsorName;
    private final int regionId;
    private final String regionConstant;
    private final int countryId;
    private final String countryConstant;
    private final String countryFlag;
    private final int continentId;
    private final String continentConstant;

    public TeamBuilderRecord(String sponsorName,
                             int regionId,
                             String regionConstant,
                             int countryId,
                             String countryConstant,
                             String countryFlag,
                             int continentId,
                             String continentConstant) {
        this.sponsorName = sponsorName;
        this.regionId = regionId;
        this.regionConstant = regionConstant;
        this.countryId = countryId;
        this.countryConstant = countryConstant;
        this.countryFlag = countryFlag;
        this.continentId = continentId;
        this.continentConstant = continentConstant;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public int getRegionId() {
        return regionId;
    }

    public String getRegionConstant() {
        return regionConstant;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getCountryConstant() {
        return countryConstant;
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