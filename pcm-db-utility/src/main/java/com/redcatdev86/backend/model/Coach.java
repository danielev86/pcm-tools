package com.redcatdev86.backend.model;

import java.io.Serializable;

public class Coach implements Serializable {

    private final int idCoach;
    private final String firstName;
    private final String lastName;
    private final Integer fkIdTeam;
    private final Integer fkIdRegion;
    private final Integer fkIdFame;

    private final Integer workAmount;     // gene_i_work_amount (editable)
    private final Integer wage;           // finan_i_wage (editable)
    private final Integer contractEnd;    // gene_i_contract_end (editable)

    private final Integer trainingStyle;  // gene_i_training_style

    public Coach(int idCoach,
                 String firstName,
                 String lastName,
                 Integer fkIdTeam,
                 Integer fkIdRegion,
                 Integer fkIdFame,
                 Integer workAmount,
                 Integer wage,
                 Integer contractEnd,
                 Integer trainingStyle) {
        this.idCoach = idCoach;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fkIdTeam = fkIdTeam;
        this.fkIdRegion = fkIdRegion;
        this.fkIdFame = fkIdFame;
        this.workAmount = workAmount;
        this.wage = wage;
        this.contractEnd = contractEnd;
        this.trainingStyle = trainingStyle;
    }

    public int getIdCoach() { return idCoach; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Integer getFkIdTeam() { return fkIdTeam; }
    public Integer getFkIdRegion() { return fkIdRegion; }
    public Integer getFkIdFame() { return fkIdFame; }
    public Integer getWorkAmount() { return workAmount; }
    public Integer getWage() { return wage; }
    public Integer getContractEnd() { return contractEnd; }
    public Integer getTrainingStyle() { return trainingStyle; }
}