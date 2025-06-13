package com.developwithhomer.pcmexporter.controller;

import com.developwithhomer.pcmexporter.controller.bean.TeamViewBean;
import com.developwithhomer.pcmexporter.controller.bean.UserDataViewBean;
import com.developwithhomer.pcmexporter.service.TeamService;
import com.developwithhomer.pcmexporter.service.UserDataService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("teamSummaryController")
@ViewScoped
public class TeamSummaryBackingBean implements Serializable {

    @Inject
    private UserDataService userDataService;
    @Inject
    private TeamService teamService;

    private UserDataViewBean userData;

    private TeamViewBean team;

    @PostConstruct
    public void init(){
        userData = userDataService.getUserDetail();
        if (userData.getTeam() != null && userData.getTeam().getId() != null){
            team = teamService.getTeamById(userData.getTeam().getId());
        }
    }

    public UserDataViewBean getUserData() {
        return userData;
    }

    public void setUserData(UserDataViewBean userData) {
        this.userData = userData;
    }

    public TeamViewBean getTeam() {
        return team;
    }

    public void setTeam(TeamViewBean team) {
        this.team = team;
    }
}
