package com.redcatdev86.backend.model;

public class ContractCyclistOffer {

    private Integer idContractOffer;
    private String cyclistFullName;
    private String actualTeam;

    private Integer periodWage;
    private Integer numYears;
    private Integer finalFlag;
    private Integer dateResolve;
    private Integer patienceTries;
    private Integer role;

    public Integer getIdContractOffer() { return idContractOffer; }
    public void setIdContractOffer(Integer idContractOffer) { this.idContractOffer = idContractOffer; }

    public String getCyclistFullName() { return cyclistFullName; }
    public void setCyclistFullName(String cyclistFullName) { this.cyclistFullName = cyclistFullName; }

    public String getActualTeam() { return actualTeam; }
    public void setActualTeam(String actualTeam) { this.actualTeam = actualTeam; }

    public Integer getPeriodWage() { return periodWage; }
    public void setPeriodWage(Integer periodWage) { this.periodWage = periodWage; }

    public Integer getNumYears() { return numYears; }
    public void setNumYears(Integer numYears) { this.numYears = numYears; }

    public Integer getFinalFlag() { return finalFlag; }
    public void setFinalFlag(Integer finalFlag) { this.finalFlag = finalFlag; }

    public Integer getDateResolve() { return dateResolve; }
    public void setDateResolve(Integer dateResolve) { this.dateResolve = dateResolve; }

    public Integer getPatienceTries() { return patienceTries; }
    public void setPatienceTries(Integer patienceTries) { this.patienceTries = patienceTries; }

    public Integer getRole() { return role; }
    public void setRole(Integer role) { this.role = role; }
}