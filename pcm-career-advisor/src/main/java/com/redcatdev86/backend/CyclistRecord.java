package com.redcatdev86.backend;

public class CyclistRecord {

    private final String firstName;
    private final String lastName;
    private final String region;
    private final String country;
    private final String continent;

    public CyclistRecord(String firstName,
                         String lastName,
                         String region,
                         String country,
                         String continent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
        this.country = country;
        this.continent = continent;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }

    public String getContinent() {
        return continent;
    }
}