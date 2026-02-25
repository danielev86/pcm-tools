package com.redcatdev86.backend.model;

public class Team {

    private final int idTeam;
    private final String shortName;
    private final String name;
    private final String jerseyAbbreviation;
    private final String abbreviation;
    private final Integer licensed;
    private final Integer fkIdCountry;
    private final String suffixEmail;
    private final String managerGeneral;
    private final Integer fkIdDivision;
    private final Integer fkIdNextDivision;
    private final Integer fkIdPrevDivision;
    private final Integer fkIdRace;
    private final Integer preRaceTeam;
    private final Integer selected;
    private final String constant;
    private final Integer fkIdCalendar1;
    private final Integer fkIdCalendar2;
    private final Integer fkIdCalendar3;
    private final Double currentEvaluation;
    private final Double prevYearEvaluation;
    private final Double nextYearEvaluation;
    private final Integer sponsorFuture;
    private final Integer defaultPicking;
    private final Integer transferEvoBudgetMinYear;
    private final Integer transferEvoRiderTypeMinYear;
    private final Integer fkIdTeamRiderTypeDistrib;
    private final String raceLikeList;
    private final String raceDislikeList;
    private final Integer budget;
    private final String color;
    private final Double riderTypeImportance;
    private final String secondaryColor;
    private final Integer yearBudgetUpdate;

    public Team(
            int idTeam,
            String shortName,
            String name,
            String jerseyAbbreviation,
            String abbreviation,
            Integer licensed,
            Integer fkIdCountry,
            String suffixEmail,
            String managerGeneral,
            Integer fkIdDivision,
            Integer fkIdNextDivision,
            Integer fkIdPrevDivision,
            Integer fkIdRace,
            Integer preRaceTeam,
            Integer selected,
            String constant,
            Integer fkIdCalendar1,
            Integer fkIdCalendar2,
            Integer fkIdCalendar3,
            Double currentEvaluation,
            Double prevYearEvaluation,
            Double nextYearEvaluation,
            Integer sponsorFuture,
            Integer defaultPicking,
            Integer transferEvoBudgetMinYear,
            Integer transferEvoRiderTypeMinYear,
            Integer fkIdTeamRiderTypeDistrib,
            String raceLikeList,
            String raceDislikeList,
            Integer budget,
            String color,
            Double riderTypeImportance,
            String secondaryColor,
            Integer yearBudgetUpdate
    ) {
        this.idTeam = idTeam;
        this.shortName = shortName;
        this.name = name;
        this.jerseyAbbreviation = jerseyAbbreviation;
        this.abbreviation = abbreviation;
        this.licensed = licensed;
        this.fkIdCountry = fkIdCountry;
        this.suffixEmail = suffixEmail;
        this.managerGeneral = managerGeneral;
        this.fkIdDivision = fkIdDivision;
        this.fkIdNextDivision = fkIdNextDivision;
        this.fkIdPrevDivision = fkIdPrevDivision;
        this.fkIdRace = fkIdRace;
        this.preRaceTeam = preRaceTeam;
        this.selected = selected;
        this.constant = constant;
        this.fkIdCalendar1 = fkIdCalendar1;
        this.fkIdCalendar2 = fkIdCalendar2;
        this.fkIdCalendar3 = fkIdCalendar3;
        this.currentEvaluation = currentEvaluation;
        this.prevYearEvaluation = prevYearEvaluation;
        this.nextYearEvaluation = nextYearEvaluation;
        this.sponsorFuture = sponsorFuture;
        this.defaultPicking = defaultPicking;
        this.transferEvoBudgetMinYear = transferEvoBudgetMinYear;
        this.transferEvoRiderTypeMinYear = transferEvoRiderTypeMinYear;
        this.fkIdTeamRiderTypeDistrib = fkIdTeamRiderTypeDistrib;
        this.raceLikeList = raceLikeList;
        this.raceDislikeList = raceDislikeList;
        this.budget = budget;
        this.color = color;
        this.riderTypeImportance = riderTypeImportance;
        this.secondaryColor = secondaryColor;
        this.yearBudgetUpdate = yearBudgetUpdate;
    }

    public int getIdTeam() { return idTeam; }
    public String getShortName() { return shortName; }
    public String getName() { return name; }
    public String getJerseyAbbreviation() { return jerseyAbbreviation; }
    public String getAbbreviation() { return abbreviation; }
    public Integer getLicensed() { return licensed; }
    public Integer getFkIdCountry() { return fkIdCountry; }
    public String getSuffixEmail() { return suffixEmail; }
    public String getManagerGeneral() { return managerGeneral; }
    public Integer getFkIdDivision() { return fkIdDivision; }
    public Integer getFkIdNextDivision() { return fkIdNextDivision; }
    public Integer getFkIdPrevDivision() { return fkIdPrevDivision; }
    public Integer getFkIdRace() { return fkIdRace; }
    public Integer getPreRaceTeam() { return preRaceTeam; }
    public Integer getSelected() { return selected; }
    public String getConstant() { return constant; }
    public Integer getFkIdCalendar1() { return fkIdCalendar1; }
    public Integer getFkIdCalendar2() { return fkIdCalendar2; }
    public Integer getFkIdCalendar3() { return fkIdCalendar3; }
    public Double getCurrentEvaluation() { return currentEvaluation; }
    public Double getPrevYearEvaluation() { return prevYearEvaluation; }
    public Double getNextYearEvaluation() { return nextYearEvaluation; }
    public Integer getSponsorFuture() { return sponsorFuture; }
    public Integer getDefaultPicking() { return defaultPicking; }
    public Integer getTransferEvoBudgetMinYear() { return transferEvoBudgetMinYear; }
    public Integer getTransferEvoRiderTypeMinYear() { return transferEvoRiderTypeMinYear; }
    public Integer getFkIdTeamRiderTypeDistrib() { return fkIdTeamRiderTypeDistrib; }
    public String getRaceLikeList() { return raceLikeList; }
    public String getRaceDislikeList() { return raceDislikeList; }
    public Integer getBudget() { return budget; }
    public String getColor() { return color; }
    public Double getRiderTypeImportance() { return riderTypeImportance; }
    public String getSecondaryColor() { return secondaryColor; }
    public Integer getYearBudgetUpdate() { return yearBudgetUpdate; }
}