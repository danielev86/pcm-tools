package com.redcatdev86.backend.model;

import java.io.Serializable;

public class CyclistMisc implements Serializable {

    private int idCyclist;
    private String firstName;
    private String lastName;

    public int getIdCyclist() {
        return idCyclist;
    }

    public void setIdCyclist(int idCyclist) {
        this.idCyclist = idCyclist;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}