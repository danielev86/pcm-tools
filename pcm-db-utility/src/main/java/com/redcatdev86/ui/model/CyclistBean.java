package com.redcatdev86.ui.model;

public class CyclistBean {

    private final int idCyclist;
    private final String firstName;
    private final String lastName;
    private final String firstLastName;
    private final int fkIdTeam; // coerente con ScoutBean: null -> 0

    private final Integer birthdate;
    private final Double popularity;
    private final Double popularityMax;
    private final Double potential;
    private final Double currentAbility;

    private final Integer size;
    private final Integer weight;

    private final Integer charPlain; private final Integer limitPlain;
    private final Integer charMountain; private final Integer limitMountain;
    private final Integer charMediumMountain; private final Integer limitMediumMountain;
    private final Integer charDownhilling; private final Integer limitDownhilling;
    private final Integer charCobble; private final Integer limitCobble;
    private final Integer charTimeTrial; private final Integer limitTimeTrial;
    private final Integer charPrologue; private final Integer limitPrologue;
    private final Integer charSprint; private final Integer limitSprint;
    private final Integer charAcceleration; private final Integer limitAcceleration;
    private final Integer charEndurance; private final Integer limitEndurance;
    private final Integer charResistance; private final Integer limitResistance;
    private final Integer charRecuperation; private final Integer limitRecuperation;
    private final Integer charHill; private final Integer limitHill;
    private final Integer charBaroudeur; private final Integer limitBaroudeur;

    public CyclistBean(
            int idCyclist,
            String firstName,
            String lastName,
            String firstLastName,
            Integer fkIdTeam,
            Integer birthdate,
            Double popularity,
            Double popularityMax,
            Double potential,
            Double currentAbility,
            Integer size,
            Integer weight,
            Integer charPlain, Integer limitPlain,
            Integer charMountain, Integer limitMountain,
            Integer charMediumMountain, Integer limitMediumMountain,
            Integer charDownhilling, Integer limitDownhilling,
            Integer charCobble, Integer limitCobble,
            Integer charTimeTrial, Integer limitTimeTrial,
            Integer charPrologue, Integer limitPrologue,
            Integer charSprint, Integer limitSprint,
            Integer charAcceleration, Integer limitAcceleration,
            Integer charEndurance, Integer limitEndurance,
            Integer charResistance, Integer limitResistance,
            Integer charRecuperation, Integer limitRecuperation,
            Integer charHill, Integer limitHill,
            Integer charBaroudeur, Integer limitBaroudeur
    ) {
        this.idCyclist = idCyclist;
        this.firstName = firstName;
        this.lastName = lastName;
        this.firstLastName = firstLastName;
        this.fkIdTeam = fkIdTeam == null ? 0 : fkIdTeam;

        this.birthdate = birthdate;
        this.popularity = popularity;
        this.popularityMax = popularityMax;
        this.potential = potential;
        this.currentAbility = currentAbility;

        this.size = size;
        this.weight = weight;

        this.charPlain = charPlain; this.limitPlain = limitPlain;
        this.charMountain = charMountain; this.limitMountain = limitMountain;
        this.charMediumMountain = charMediumMountain; this.limitMediumMountain = limitMediumMountain;
        this.charDownhilling = charDownhilling; this.limitDownhilling = limitDownhilling;
        this.charCobble = charCobble; this.limitCobble = limitCobble;
        this.charTimeTrial = charTimeTrial; this.limitTimeTrial = limitTimeTrial;
        this.charPrologue = charPrologue; this.limitPrologue = limitPrologue;
        this.charSprint = charSprint; this.limitSprint = limitSprint;
        this.charAcceleration = charAcceleration; this.limitAcceleration = limitAcceleration;
        this.charEndurance = charEndurance; this.limitEndurance = limitEndurance;
        this.charResistance = charResistance; this.limitResistance = limitResistance;
        this.charRecuperation = charRecuperation; this.limitRecuperation = limitRecuperation;
        this.charHill = charHill; this.limitHill = limitHill;
        this.charBaroudeur = charBaroudeur; this.limitBaroudeur = limitBaroudeur;
    }

    public int getIdCyclist() { return idCyclist; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getFirstLastName() { return firstLastName; }
    public int getFkIdTeam() { return fkIdTeam; }

    public Integer getBirthdate() { return birthdate; }
    public Double getPopularity() { return popularity; }
    public Double getPopularityMax() { return popularityMax; }
    public Double getPotential() { return potential; }
    public Double getCurrentAbility() { return currentAbility; }

    public Integer getSize() { return size; }
    public Integer getWeight() { return weight; }

    public Integer getCharPlain() { return charPlain; }
    public Integer getLimitPlain() { return limitPlain; }
    public Integer getCharMountain() { return charMountain; }
    public Integer getLimitMountain() { return limitMountain; }
    public Integer getCharMediumMountain() { return charMediumMountain; }
    public Integer getLimitMediumMountain() { return limitMediumMountain; }
    public Integer getCharDownhilling() { return charDownhilling; }
    public Integer getLimitDownhilling() { return limitDownhilling; }
    public Integer getCharCobble() { return charCobble; }
    public Integer getLimitCobble() { return limitCobble; }
    public Integer getCharTimeTrial() { return charTimeTrial; }
    public Integer getLimitTimeTrial() { return limitTimeTrial; }
    public Integer getCharPrologue() { return charPrologue; }
    public Integer getLimitPrologue() { return limitPrologue; }
    public Integer getCharSprint() { return charSprint; }
    public Integer getLimitSprint() { return limitSprint; }
    public Integer getCharAcceleration() { return charAcceleration; }
    public Integer getLimitAcceleration() { return limitAcceleration; }
    public Integer getCharEndurance() { return charEndurance; }
    public Integer getLimitEndurance() { return limitEndurance; }
    public Integer getCharResistance() { return charResistance; }
    public Integer getLimitResistance() { return limitResistance; }
    public Integer getCharRecuperation() { return charRecuperation; }
    public Integer getLimitRecuperation() { return limitRecuperation; }
    public Integer getCharHill() { return charHill; }
    public Integer getLimitHill() { return limitHill; }
    public Integer getCharBaroudeur() { return charBaroudeur; }
    public Integer getLimitBaroudeur() { return limitBaroudeur; }

    public String getDisplayName() {
        String fn = firstName == null ? "" : firstName;
        String ln = lastName == null ? "" : lastName;
        return (fn + " " + ln).trim();
    }
}