package com.redcatdev86.backend.model;

import java.io.Serializable;

public class Scout implements Serializable {

    private final int idScout;
    private final String firstName;
    private final String lastName;
    private final Integer fkIdTeam;
    private final Integer wage;
    private final Integer contractEnd;
    private final Integer tr;

    public Scout(int idScout, String firstName, String lastName,
                 Integer fkIdTeam, Integer wage, Integer contractEnd, Integer tr) {
        this.idScout = idScout;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fkIdTeam = fkIdTeam;
        this.wage = wage;
        this.contractEnd = contractEnd;
        this.tr = tr;
    }

    public int getIdScout() { return idScout; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Integer getFkIdTeam() { return fkIdTeam; }
    public Integer getWage() { return wage; }
    public Integer getContractEnd() { return contractEnd; }
    public Integer getTr() { return tr; }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}