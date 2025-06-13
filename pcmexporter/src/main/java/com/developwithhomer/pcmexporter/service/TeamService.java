package com.developwithhomer.pcmexporter.service;

import com.developwithhomer.pcmexporter.controller.bean.TeamViewBean;

import java.util.List;

public interface TeamService {

    TeamViewBean getTeamById(Long id);

    List<TeamViewBean> getAllTeams();

}
