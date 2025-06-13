package com.developwithhomer.pcmexporter.service;

import com.developwithhomer.pcmexporter.controller.bean.UserDataViewBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@Slf4j
@SpringBootTest
@TestPropertySource(properties = {"dbValue=Career_7", "pcmuser=danielev86", "gameVersion=24", "enable_test=false"})
public class UserDataServiceTest {

    @Autowired
    private UserDataService userDataService;

    @Test
    public void getUserDataInfoTest(){
        UserDataViewBean userDataViewBean = userDataService.getUserDetail();
        Assertions.assertTrue(userDataViewBean != null && userDataViewBean.getTeam() != null && userDataViewBean.getTeam().getId().compareTo(Long.valueOf(4l)) == 0);
    }
}
