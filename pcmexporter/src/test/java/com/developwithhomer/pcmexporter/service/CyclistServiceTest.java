package com.developwithhomer.pcmexporter.service;

import com.developwithhomer.pcmexporter.controller.bean.CyclistViewBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@Slf4j
@SpringBootTest
@TestPropertySource(properties = {"dbValue=Career_7", "pcmuser=danielev86", "gameVersion=24", "enable_test=false"})
public class CyclistServiceTest {

    @Autowired
    private CyclistService cyclistService;

    @Test
    public void getAllCyclistsTests(){
        List<CyclistViewBean> cyclists = cyclistService.getCyclystsByTeamId(4L);
        cyclists.forEach(cyclist ->log.info("Cyclist DATA: {}", cyclist));
    }
}
