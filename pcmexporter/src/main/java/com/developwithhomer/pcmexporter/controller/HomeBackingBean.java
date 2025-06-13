package com.developwithhomer.pcmexporter.controller;

import com.developwithhomer.pcmexporter.controller.bean.UserDataViewBean;
import com.developwithhomer.pcmexporter.service.UserDataService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("homeController")
@ViewScoped
public class HomeBackingBean implements Serializable {

    @Inject
    private UserDataService userDataService;

    private UserDataViewBean userData;

    @PostConstruct
    public void init(){
        userData = userDataService.getUserDetail();
    }

    public UserDataViewBean getUserData() {
        return userData;
    }

    public void setUserData(UserDataViewBean userData) {
        this.userData = userData;
    }
}
