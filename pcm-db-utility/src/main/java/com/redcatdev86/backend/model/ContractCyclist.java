package com.redcatdev86.backend.model;

public class ContractCyclist {
    private Integer idContractCyclist;
    private String cyclistFullName;
    private String actualTeam;
    private String prevTeam;

    private Integer periodWage;
    private Integer yearBegin;
    private Integer yearEnd;

    private Integer activeContract;
    private Integer role;

    public Integer getIdContractCyclist() { return idContractCyclist; }
    public void setIdContractCyclist(Integer idContractCyclist) { this.idContractCyclist = idContractCyclist; }

    public String getCyclistFullName() { return cyclistFullName; }
    public void setCyclistFullName(String cyclistFullName) { this.cyclistFullName = cyclistFullName; }

    public String getActualTeam() { return actualTeam; }
    public void setActualTeam(String actualTeam) { this.actualTeam = actualTeam; }

    public String getPrevTeam() { return prevTeam; }
    public void setPrevTeam(String prevTeam) { this.prevTeam = prevTeam; }

    public Integer getPeriodWage() { return periodWage; }
    public void setPeriodWage(Integer periodWage) { this.periodWage = periodWage; }

    public Integer getYearBegin() { return yearBegin; }
    public void setYearBegin(Integer yearBegin) { this.yearBegin = yearBegin; }

    public Integer getYearEnd() { return yearEnd; }
    public void setYearEnd(Integer yearEnd) { this.yearEnd = yearEnd; }

    public Integer getActiveContract() { return activeContract; }
    public void setActiveContract(Integer activeContract) { this.activeContract = activeContract; }

    public Integer getRole() { return role; }
    public void setRole(Integer role) { this.role = role; }
}