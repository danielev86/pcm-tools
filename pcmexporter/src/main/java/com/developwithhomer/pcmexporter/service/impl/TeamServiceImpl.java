package com.developwithhomer.pcmexporter.service.impl;

import com.developwithhomer.pcmexporter.controller.bean.TeamViewBean;
import com.developwithhomer.pcmexporter.converter.TeamConverter;
import com.developwithhomer.pcmexporter.repository.TeamRepository;
import com.developwithhomer.pcmexporter.repository.model.Team;
import com.developwithhomer.pcmexporter.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamConverter teamConverter;

    @Transactional
    public TeamViewBean getTeamById(Long id){
        Optional<Team> team = teamRepository.findById(id);
        TeamViewBean teamBean = null;
        if (team.isPresent()){
            teamBean = teamConverter.serviceTo(team.get());
        }
        return teamBean;
    }

    @Transactional
    public List<TeamViewBean> getAllTeams(){
        List<Team> teams = teamRepository.findAll();
        List<TeamViewBean> teamList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(teams)){
            teamList = teamConverter.serviceToList(teams);
        }
        return teamList;
    }

}
