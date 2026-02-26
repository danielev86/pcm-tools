package com.redcatdev86.ui.model;

import javafx.beans.property.*;

public class CyclistBean {

    // --- Identity ---
    private final IntegerProperty idCyclist = new SimpleIntegerProperty();
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty firstLastName = new SimpleStringProperty();
    private final IntegerProperty fkIdTeam = new SimpleIntegerProperty();

    // --- Main stats ---
    private final DoubleProperty currentAbility = new SimpleDoubleProperty();
    private final DoubleProperty potential = new SimpleDoubleProperty();

    // --- Skills (charac + limit) ---
    private final IntegerProperty charPlain = new SimpleIntegerProperty();
    private final IntegerProperty limitPlain = new SimpleIntegerProperty();

    private final IntegerProperty charMountain = new SimpleIntegerProperty();
    private final IntegerProperty limitMountain = new SimpleIntegerProperty();

    private final IntegerProperty charMediumMountain = new SimpleIntegerProperty();
    private final IntegerProperty limitMediumMountain = new SimpleIntegerProperty();

    private final IntegerProperty charDownhilling = new SimpleIntegerProperty();
    private final IntegerProperty limitDownhilling = new SimpleIntegerProperty();

    private final IntegerProperty charCobble = new SimpleIntegerProperty();
    private final IntegerProperty limitCobble = new SimpleIntegerProperty();

    private final IntegerProperty charTimeTrial = new SimpleIntegerProperty();
    private final IntegerProperty limitTimeTrial = new SimpleIntegerProperty();

    private final IntegerProperty charPrologue = new SimpleIntegerProperty();
    private final IntegerProperty limitPrologue = new SimpleIntegerProperty();

    private final IntegerProperty charSprint = new SimpleIntegerProperty();
    private final IntegerProperty limitSprint = new SimpleIntegerProperty();

    private final IntegerProperty charAcceleration = new SimpleIntegerProperty();
    private final IntegerProperty limitAcceleration = new SimpleIntegerProperty();

    private final IntegerProperty charEndurance = new SimpleIntegerProperty();
    private final IntegerProperty limitEndurance = new SimpleIntegerProperty();

    private final IntegerProperty charResistance = new SimpleIntegerProperty();
    private final IntegerProperty limitResistance = new SimpleIntegerProperty();

    private final IntegerProperty charRecuperation = new SimpleIntegerProperty();
    private final IntegerProperty limitRecuperation = new SimpleIntegerProperty();

    private final IntegerProperty charHill = new SimpleIntegerProperty();
    private final IntegerProperty limitHill = new SimpleIntegerProperty();

    private final IntegerProperty charBaroudeur = new SimpleIntegerProperty();
    private final IntegerProperty limitBaroudeur = new SimpleIntegerProperty();

    // --- Constructor ---
    public CyclistBean() {
    }

    // =======================
    // GETTER + SETTER
    // =======================

    public int getIdCyclist() { return idCyclist.get(); }
    public void setIdCyclist(int v) { idCyclist.set(v); }
    public IntegerProperty idCyclistProperty() { return idCyclist; }

    public String getFirstName() { return firstName.get(); }
    public void setFirstName(String v) { firstName.set(v); }
    public StringProperty firstNameProperty() { return firstName; }

    public String getLastName() { return lastName.get(); }
    public void setLastName(String v) { lastName.set(v); }
    public StringProperty lastNameProperty() { return lastName; }

    public String getFirstLastName() { return firstLastName.get(); }
    public void setFirstLastName(String v) { firstLastName.set(v); }
    public StringProperty firstLastNameProperty() { return firstLastName; }

    public int getFkIdTeam() { return fkIdTeam.get(); }
    public void setFkIdTeam(int v) { fkIdTeam.set(v); }
    public IntegerProperty fkIdTeamProperty() { return fkIdTeam; }

    public double getCurrentAbility() { return currentAbility.get(); }
    public void setCurrentAbility(double v) { currentAbility.set(v); }
    public DoubleProperty currentAbilityProperty() { return currentAbility; }

    public double getPotential() { return potential.get(); }
    public void setPotential(double v) { potential.set(v); }
    public DoubleProperty potentialProperty() { return potential; }

    // ---- Skills ----

    public int getCharPlain() { return charPlain.get(); }
    public void setCharPlain(Integer v) { charPlain.set(v); }
    public IntegerProperty charPlainProperty() { return charPlain; }

    public int getLimitPlain() { return limitPlain.get(); }
    public void setLimitPlain(Integer v) { limitPlain.set(v); }
    public IntegerProperty limitPlainProperty() { return limitPlain; }

    public int getCharMountain() { return charMountain.get(); }
    public void setCharMountain(Integer v) { charMountain.set(v); }
    public IntegerProperty charMountainProperty() { return charMountain; }

    public int getLimitMountain() { return limitMountain.get(); }
    public void setLimitMountain(Integer v) { limitMountain.set(v); }
    public IntegerProperty limitMountainProperty() { return limitMountain; }

    public int getCharMediumMountain() { return charMediumMountain.get(); }
    public void setCharMediumMountain(Integer v) { charMediumMountain.set(v); }
    public IntegerProperty charMediumMountainProperty() { return charMediumMountain; }

    public int getLimitMediumMountain() { return limitMediumMountain.get(); }
    public void setLimitMediumMountain(Integer v) { limitMediumMountain.set(v); }
    public IntegerProperty limitMediumMountainProperty() { return limitMediumMountain; }

    public int getCharDownhilling() { return charDownhilling.get(); }
    public void setCharDownhilling(Integer v) { charDownhilling.set(v); }
    public IntegerProperty charDownhillingProperty() { return charDownhilling; }

    public int getLimitDownhilling() { return limitDownhilling.get(); }
    public void setLimitDownhilling(Integer v) { limitDownhilling.set(v); }
    public IntegerProperty limitDownhillingProperty() { return limitDownhilling; }

    public int getCharCobble() { return charCobble.get(); }
    public void setCharCobble(Integer v) { charCobble.set(v); }
    public IntegerProperty charCobbleProperty() { return charCobble; }

    public int getLimitCobble() { return limitCobble.get(); }
    public void setLimitCobble(Integer v) { limitCobble.set(v); }
    public IntegerProperty limitCobbleProperty() { return limitCobble; }

    public int getCharTimeTrial() { return charTimeTrial.get(); }
    public void setCharTimeTrial(Integer v) { charTimeTrial.set(v); }
    public IntegerProperty charTimeTrialProperty() { return charTimeTrial; }

    public int getLimitTimeTrial() { return limitTimeTrial.get(); }
    public void setLimitTimeTrial(Integer v) { limitTimeTrial.set(v); }
    public IntegerProperty limitTimeTrialProperty() { return limitTimeTrial; }

    public int getCharPrologue() { return charPrologue.get(); }
    public void setCharPrologue(Integer v) { charPrologue.set(v); }
    public IntegerProperty charPrologueProperty() { return charPrologue; }

    public int getLimitPrologue() { return limitPrologue.get(); }
    public void setLimitPrologue(Integer v) { limitPrologue.set(v); }
    public IntegerProperty limitPrologueProperty() { return limitPrologue; }

    public int getCharSprint() { return charSprint.get(); }
    public void setCharSprint(Integer v) { charSprint.set(v); }
    public IntegerProperty charSprintProperty() { return charSprint; }

    public int getLimitSprint() { return limitSprint.get(); }
    public void setLimitSprint(Integer v) { limitSprint.set(v); }
    public IntegerProperty limitSprintProperty() { return limitSprint; }

    public int getCharAcceleration() { return charAcceleration.get(); }
    public void setCharAcceleration(Integer v) { charAcceleration.set(v); }
    public IntegerProperty charAccelerationProperty() { return charAcceleration; }

    public int getLimitAcceleration() { return limitAcceleration.get(); }
    public void setLimitAcceleration(Integer v) { limitAcceleration.set(v); }
    public IntegerProperty limitAccelerationProperty() { return limitAcceleration; }

    public int getCharEndurance() { return charEndurance.get(); }
    public void setCharEndurance(Integer v) { charEndurance.set(v); }
    public IntegerProperty charEnduranceProperty() { return charEndurance; }

    public int getLimitEndurance() { return limitEndurance.get(); }
    public void setLimitEndurance(Integer v) { limitEndurance.set(v); }
    public IntegerProperty limitEnduranceProperty() { return limitEndurance; }

    public int getCharResistance() { return charResistance.get(); }
    public void setCharResistance(Integer v) { charResistance.set(v); }
    public IntegerProperty charResistanceProperty() { return charResistance; }

    public int getLimitResistance() { return limitResistance.get(); }
    public void setLimitResistance(Integer v) { limitResistance.set(v); }
    public IntegerProperty limitResistanceProperty() { return limitResistance; }

    public int getCharRecuperation() { return charRecuperation.get(); }
    public void setCharRecuperation(Integer v) { charRecuperation.set(v); }
    public IntegerProperty charRecuperationProperty() { return charRecuperation; }

    public int getLimitRecuperation() { return limitRecuperation.get(); }
    public void setLimitRecuperation(Integer v) { limitRecuperation.set(v); }
    public IntegerProperty limitRecuperationProperty() { return limitRecuperation; }

    public int getCharHill() { return charHill.get(); }
    public void setCharHill(Integer v) { charHill.set(v); }
    public IntegerProperty charHillProperty() { return charHill; }

    public int getLimitHill() { return limitHill.get(); }
    public void setLimitHill(Integer v) { limitHill.set(v); }
    public IntegerProperty limitHillProperty() { return limitHill; }

    public int getCharBaroudeur() { return charBaroudeur.get(); }
    public void setCharBaroudeur(Integer v) { charBaroudeur.set(v); }
    public IntegerProperty charBaroudeurProperty() { return charBaroudeur; }

    public int getLimitBaroudeur() { return limitHill.get(); }
    public void setLimitBaroudeur(Integer v) { limitHill.set(v); }
    public IntegerProperty limitBaroudeur() { return limitHill; }
}