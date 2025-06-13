package com.developwithhomer.pcmexporter.repository;

import com.developwithhomer.pcmexporter.repository.model.Team;
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
@Transactional
public class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void findAllTeam(){
        List<Team> teams = teamRepository.findAll();
        Assertions.assertEquals(CollectionUtils.isNotEmpty(teams), true);
    }
}
