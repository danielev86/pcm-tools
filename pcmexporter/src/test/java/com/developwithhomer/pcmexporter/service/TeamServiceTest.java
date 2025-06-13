package com.developwithhomer.pcmexporter.service;

import com.developwithhomer.pcmexporter.controller.bean.TeamViewBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@SpringBootTest
@TestPropertySource(properties = {"dbValue=Career_7", "pcmuser=danielev86", "gameVersion=24", "enable_test=false"})
public class TeamServiceTest {

    @Autowired
    private TeamService teamService;

    @Test
    public void getAllTeams(){
        List<TeamViewBean> teams = teamService.getAllTeams();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(teams));
    }

}
