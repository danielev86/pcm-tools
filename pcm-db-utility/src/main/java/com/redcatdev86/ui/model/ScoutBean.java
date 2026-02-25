package com.redcatdev86.ui.model;

import javafx.beans.property.*;

public class ScoutBean {

    private final IntegerProperty idScout = new SimpleIntegerProperty();
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final IntegerProperty fkIdTeam = new SimpleIntegerProperty();
    private final IntegerProperty wage = new SimpleIntegerProperty();
    private final IntegerProperty contractEnd = new SimpleIntegerProperty();
    private final IntegerProperty tr = new SimpleIntegerProperty();

    // opzionale: per gestire null in integer con un flag
    private final BooleanProperty hasTeam = new SimpleBooleanProperty(true);

    public ScoutBean(int idScout, String firstName, String lastName,
                     Integer fkIdTeam, Integer wage, Integer contractEnd, Integer tr) {

        this.idScout.set(idScout);
        this.firstName.set(firstName);
        this.lastName.set(lastName);

        if (fkIdTeam == null) {
            this.fkIdTeam.set(0);
            this.hasTeam.set(false);
        } else {
            this.fkIdTeam.set(fkIdTeam);
            this.hasTeam.set(true);
        }

        this.wage.set(wage == null ? 0 : wage);
        this.contractEnd.set(contractEnd == null ? 0 : contractEnd);
        this.tr.set(tr == null ? 0 : tr);
    }

    // Properties (per TableView binding)
    public IntegerProperty idScoutProperty() { return idScout; }
    public StringProperty firstNameProperty() { return firstName; }
    public StringProperty lastNameProperty() { return lastName; }
    public IntegerProperty fkIdTeamProperty() { return fkIdTeam; }
    public IntegerProperty wageProperty() { return wage; }
    public IntegerProperty contractEndProperty() { return contractEnd; }
    public IntegerProperty trProperty() { return tr; }
    public BooleanProperty hasTeamProperty() { return hasTeam; }

    // Getter comodi
    public int getIdScout() { return idScout.get(); }
    public String getFirstName() { return firstName.get(); }
    public String getLastName() { return lastName.get(); }
    public int getFkIdTeam() { return fkIdTeam.get(); }
    public int getWage() { return wage.get(); }
    public int getContractEnd() { return contractEnd.get(); }
    public int getTr() { return tr.get(); }

    public void setWage(int wage) { this.wage.set(wage); }
    public void setContractEnd(int contractEnd) { this.contractEnd.set(contractEnd); }

}