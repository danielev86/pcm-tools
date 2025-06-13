package com.developwithhomer.pcmexporter.controller.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class UserDataViewBean implements Serializable {

    private String userData;
    private List<GAMCarrerDataViewBean> gamCarrerData;

    private TeamViewBean team;

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    public List<GAMCarrerDataViewBean> getGamCarrerData() {
        return gamCarrerData;
    }

    public void setGamCarrerData(List<GAMCarrerDataViewBean> gamCarrerData) {
        this.gamCarrerData = gamCarrerData;
    }

    public TeamViewBean getTeam() {
        return team;
    }

    public void setTeam(TeamViewBean team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDataViewBean)) return false;

        UserDataViewBean that = (UserDataViewBean) o;

        if (!getUserData().equals(that.getUserData())) return false;
        if (!getGamCarrerData().equals(that.getGamCarrerData())) return false;
        return getTeam().equals(that.getTeam());
    }

    @Override
    public int hashCode() {
        int result = getUserData().hashCode();
        result = 31 * result + getGamCarrerData().hashCode();
        result = 31 * result + getTeam().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserDataViewBean{" +
                "userData='" + userData + '\'' +
                ", gamCarrerData=" + gamCarrerData +
                ", team=" + team +
                '}';
    }
}
