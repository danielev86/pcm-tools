package com.developwithhomer.pcmexporter.repository;

import com.developwithhomer.pcmexporter.repository.model.Cyclist;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
public class CyclistRepositoryTest {

    @Autowired
    private CyclistRepository cyclistRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void findAllCyclist(){
        List<Cyclist> cyclists = cyclistRepository.findAll();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(cyclists));
    }

    @Test
    void findCyclystByTeamID(){
        List<Cyclist> cyclists = cyclistRepository.findCyclistByTeam(1L);
        Assertions.assertTrue(cyclists.size() == 30);
    }
}
