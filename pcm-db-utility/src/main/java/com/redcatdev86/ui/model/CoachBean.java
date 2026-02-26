package com.redcatdev86.ui.model;

import javafx.beans.property.*;

public class CoachBean {

    private final IntegerProperty idCoach = new SimpleIntegerProperty();
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final IntegerProperty fkIdTeam = new SimpleIntegerProperty();

    private final IntegerProperty fkIdRegion = new SimpleIntegerProperty();
    private final IntegerProperty fkIdFame = new SimpleIntegerProperty();

    // editable
    private final IntegerProperty workAmount = new SimpleIntegerProperty();
    private final IntegerProperty wage = new SimpleIntegerProperty();
    private final IntegerProperty contractEnd = new SimpleIntegerProperty();

    private final IntegerProperty trainingStyle = new SimpleIntegerProperty();

    public int getIdCoach() { return idCoach.get(); }
    public void setIdCoach(int v) { idCoach.set(v); }
    public IntegerProperty idCoachProperty() { return idCoach; }

    public String getFirstName() { return firstName.get(); }
    public void setFirstName(String v) { firstName.set(v); }
    public StringProperty firstNameProperty() { return firstName; }

    public String getLastName() { return lastName.get(); }
    public void setLastName(String v) { lastName.set(v); }
    public StringProperty lastNameProperty() { return lastName; }

    public int getFkIdTeam() { return fkIdTeam.get(); }
    public void setFkIdTeam(int v) { fkIdTeam.set(v); }
    public IntegerProperty fkIdTeamProperty() { return fkIdTeam; }

    public int getFkIdRegion() { return fkIdRegion.get(); }
    public void setFkIdRegion(int v) { fkIdRegion.set(v); }
    public IntegerProperty fkIdRegionProperty() { return fkIdRegion; }

    public int getFkIdFame() { return fkIdFame.get(); }
    public void setFkIdFame(int v) { fkIdFame.set(v); }
    public IntegerProperty fkIdFameProperty() { return fkIdFame; }

    public int getWorkAmount() { return workAmount.get(); }
    public void setWorkAmount(int v) { workAmount.set(v); }
    public IntegerProperty workAmountProperty() { return workAmount; }

    public int getWage() { return wage.get(); }
    public void setWage(int v) { wage.set(v); }
    public IntegerProperty wageProperty() { return wage; }

    public int getContractEnd() { return contractEnd.get(); }
    public void setContractEnd(int v) { contractEnd.set(v); }
    public IntegerProperty contractEndProperty() { return contractEnd; }

    public int getTrainingStyle() { return trainingStyle.get(); }
    public void setTrainingStyle(int v) { trainingStyle.set(v); }
    public IntegerProperty trainingStyleProperty() { return trainingStyle; }
}