package com.redcatdev86.ui.model;

import javafx.beans.property.*;

public class TeamBean {

    private final IntegerProperty idTeam = new SimpleIntegerProperty();

    private final StringProperty shortName = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty jerseyAbbreviation = new SimpleStringProperty();
    private final StringProperty abbreviation = new SimpleStringProperty();

    // integers in DB (0/1 etc.) -> keep as int for simplicity
    private final IntegerProperty licensed = new SimpleIntegerProperty();
    private final IntegerProperty fkIdCountry = new SimpleIntegerProperty();
    private final StringProperty suffixeMail = new SimpleStringProperty();
    private final StringProperty managerGeneral = new SimpleStringProperty();

    private final IntegerProperty fkIdDivision = new SimpleIntegerProperty();
    private final IntegerProperty fkIdNextDivision = new SimpleIntegerProperty();
    private final IntegerProperty fkIdPrevDivision = new SimpleIntegerProperty();

    private final IntegerProperty fkIdRace = new SimpleIntegerProperty();
    private final IntegerProperty preraceTeam = new SimpleIntegerProperty();
    private final IntegerProperty selected = new SimpleIntegerProperty();

    private final StringProperty constant = new SimpleStringProperty();

    private final IntegerProperty fkIdCalendar1 = new SimpleIntegerProperty();
    private final IntegerProperty fkIdCalendar2 = new SimpleIntegerProperty();
    private final IntegerProperty fkIdCalendar3 = new SimpleIntegerProperty();

    private final DoubleProperty currentEvaluation = new SimpleDoubleProperty();
    private final DoubleProperty prevYearEvaluation = new SimpleDoubleProperty();
    private final DoubleProperty nextYearEvaluation = new SimpleDoubleProperty();

    private final IntegerProperty sponsorFuture = new SimpleIntegerProperty();
    private final IntegerProperty defaultPicking = new SimpleIntegerProperty();
    private final IntegerProperty transferEvoBudgetMinYear = new SimpleIntegerProperty();
    private final IntegerProperty transferEvoRiderTypeMinYear = new SimpleIntegerProperty();
    private final IntegerProperty fkIdTeamRiderTypeDistrib = new SimpleIntegerProperty();

    private final StringProperty raceLike = new SimpleStringProperty();
    private final StringProperty raceDislike = new SimpleStringProperty();

    private final IntegerProperty budget = new SimpleIntegerProperty();
    private final StringProperty color = new SimpleStringProperty();
    private final DoubleProperty riderTypeImportance = new SimpleDoubleProperty();
    private final StringProperty secondaryColor = new SimpleStringProperty();
    private final IntegerProperty yearBudgetUpdate = new SimpleIntegerProperty();

    // getters/setters
    public int getIdTeam() { return idTeam.get(); }
    public void setIdTeam(int v) { idTeam.set(v); }
    public IntegerProperty idTeamProperty() { return idTeam; }

    public String getShortName() { return shortName.get(); }
    public void setShortName(String v) { shortName.set(v); }
    public StringProperty shortNameProperty() { return shortName; }

    public String getName() { return name.get(); }
    public void setName(String v) { name.set(v); }
    public StringProperty nameProperty() { return name; }

    public String getJerseyAbbreviation() { return jerseyAbbreviation.get(); }
    public void setJerseyAbbreviation(String v) { jerseyAbbreviation.set(v); }
    public StringProperty jerseyAbbreviationProperty() { return jerseyAbbreviation; }

    public String getAbbreviation() { return abbreviation.get(); }
    public void setAbbreviation(String v) { abbreviation.set(v); }
    public StringProperty abbreviationProperty() { return abbreviation; }

    public int getLicensed() { return licensed.get(); }
    public void setLicensed(int v) { licensed.set(v); }
    public IntegerProperty licensedProperty() { return licensed; }

    public int getFkIdCountry() { return fkIdCountry.get(); }
    public void setFkIdCountry(int v) { fkIdCountry.set(v); }
    public IntegerProperty fkIdCountryProperty() { return fkIdCountry; }

    public String getSuffixeMail() { return suffixeMail.get(); }
    public void setSuffixeMail(String v) { suffixeMail.set(v); }
    public StringProperty suffixeMailProperty() { return suffixeMail; }

    public String getManagerGeneral() { return managerGeneral.get(); }
    public void setManagerGeneral(String v) { managerGeneral.set(v); }
    public StringProperty managerGeneralProperty() { return managerGeneral; }

    public int getFkIdDivision() { return fkIdDivision.get(); }
    public void setFkIdDivision(int v) { fkIdDivision.set(v); }
    public IntegerProperty fkIdDivisionProperty() { return fkIdDivision; }

    public int getFkIdNextDivision() { return fkIdNextDivision.get(); }
    public void setFkIdNextDivision(int v) { fkIdNextDivision.set(v); }
    public IntegerProperty fkIdNextDivisionProperty() { return fkIdNextDivision; }

    public int getFkIdPrevDivision() { return fkIdPrevDivision.get(); }
    public void setFkIdPrevDivision(int v) { fkIdPrevDivision.set(v); }
    public IntegerProperty fkIdPrevDivisionProperty() { return fkIdPrevDivision; }

    public int getFkIdRace() { return fkIdRace.get(); }
    public void setFkIdRace(int v) { fkIdRace.set(v); }
    public IntegerProperty fkIdRaceProperty() { return fkIdRace; }

    public int getPreraceTeam() { return preraceTeam.get(); }
    public void setPreraceTeam(int v) { preraceTeam.set(v); }
    public IntegerProperty preraceTeamProperty() { return preraceTeam; }

    public int getSelected() { return selected.get(); }
    public void setSelected(int v) { selected.set(v); }
    public IntegerProperty selectedProperty() { return selected; }

    public String getConstant() { return constant.get(); }
    public void setConstant(String v) { constant.set(v); }
    public StringProperty constantProperty() { return constant; }

    public int getFkIdCalendar1() { return fkIdCalendar1.get(); }
    public void setFkIdCalendar1(int v) { fkIdCalendar1.set(v); }
    public IntegerProperty fkIdCalendar1Property() { return fkIdCalendar1; }

    public int getFkIdCalendar2() { return fkIdCalendar2.get(); }
    public void setFkIdCalendar2(int v) { fkIdCalendar2.set(v); }
    public IntegerProperty fkIdCalendar2Property() { return fkIdCalendar2; }

    public int getFkIdCalendar3() { return fkIdCalendar3.get(); }
    public void setFkIdCalendar3(int v) { fkIdCalendar3.set(v); }
    public IntegerProperty fkIdCalendar3Property() { return fkIdCalendar3; }

    public double getCurrentEvaluation() { return currentEvaluation.get(); }
    public void setCurrentEvaluation(double v) { currentEvaluation.set(v); }
    public DoubleProperty currentEvaluationProperty() { return currentEvaluation; }

    public double getPrevYearEvaluation() { return prevYearEvaluation.get(); }
    public void setPrevYearEvaluation(double v) { prevYearEvaluation.set(v); }
    public DoubleProperty prevYearEvaluationProperty() { return prevYearEvaluation; }

    public double getNextYearEvaluation() { return nextYearEvaluation.get(); }
    public void setNextYearEvaluation(double v) { nextYearEvaluation.set(v); }
    public DoubleProperty nextYearEvaluationProperty() { return nextYearEvaluation; }

    public int getSponsorFuture() { return sponsorFuture.get(); }
    public void setSponsorFuture(int v) { sponsorFuture.set(v); }
    public IntegerProperty sponsorFutureProperty() { return sponsorFuture; }

    public int getDefaultPicking() { return defaultPicking.get(); }
    public void setDefaultPicking(int v) { defaultPicking.set(v); }
    public IntegerProperty defaultPickingProperty() { return defaultPicking; }

    public int getTransferEvoBudgetMinYear() { return transferEvoBudgetMinYear.get(); }
    public void setTransferEvoBudgetMinYear(int v) { transferEvoBudgetMinYear.set(v); }
    public IntegerProperty transferEvoBudgetMinYearProperty() { return transferEvoBudgetMinYear; }

    public int getTransferEvoRiderTypeMinYear() { return transferEvoRiderTypeMinYear.get(); }
    public void setTransferEvoRiderTypeMinYear(int v) { transferEvoRiderTypeMinYear.set(v); }
    public IntegerProperty transferEvoRiderTypeMinYearProperty() { return transferEvoRiderTypeMinYear; }

    public int getFkIdTeamRiderTypeDistrib() { return fkIdTeamRiderTypeDistrib.get(); }
    public void setFkIdTeamRiderTypeDistrib(int v) { fkIdTeamRiderTypeDistrib.set(v); }
    public IntegerProperty fkIdTeamRiderTypeDistribProperty() { return fkIdTeamRiderTypeDistrib; }

    public String getRaceLike() { return raceLike.get(); }
    public void setRaceLike(String v) { raceLike.set(v); }
    public StringProperty raceLikeProperty() { return raceLike; }

    public String getRaceDislike() { return raceDislike.get(); }
    public void setRaceDislike(String v) { raceDislike.set(v); }
    public StringProperty raceDislikeProperty() { return raceDislike; }

    public int getBudget() { return budget.get(); }
    public void setBudget(int v) { budget.set(v); }
    public IntegerProperty budgetProperty() { return budget; }

    public String getColor() { return color.get(); }
    public void setColor(String v) { color.set(v); }
    public StringProperty colorProperty() { return color; }

    public double getRiderTypeImportance() { return riderTypeImportance.get(); }
    public void setRiderTypeImportance(double v) { riderTypeImportance.set(v); }
    public DoubleProperty riderTypeImportanceProperty() { return riderTypeImportance; }

    public String getSecondaryColor() { return secondaryColor.get(); }
    public void setSecondaryColor(String v) { secondaryColor.set(v); }
    public StringProperty secondaryColorProperty() { return secondaryColor; }

    public int getYearBudgetUpdate() { return yearBudgetUpdate.get(); }
    public void setYearBudgetUpdate(int v) { yearBudgetUpdate.set(v); }
    public IntegerProperty yearBudgetUpdateProperty() { return yearBudgetUpdate; }
}