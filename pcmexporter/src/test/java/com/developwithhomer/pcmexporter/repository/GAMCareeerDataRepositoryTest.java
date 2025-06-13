package com.developwithhomer.pcmexporter.repository;

import com.developwithhomer.pcmexporter.repository.model.GAMCareerData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@Slf4j
@SpringBootTest
@TestPropertySource(properties = {"dbValue=Career_7", "pcmuser=danielev86", "gameVersion=24", "enable_test=false"})
@Transactional
public class GAMCareeerDataRepositoryTest {

    @Autowired
    private GAMCareerDataRepository gamRepository;

    @Test
    public void findAll(){
        List<GAMCareerData> parameters = gamRepository.findAll();
        Map<String, GAMCareerData> map = parameters.stream().collect(Collectors.toMap(GAMCareerData::getParameterCode, Function.identity()));
        Assertions.assertTrue(map.get("PROPOINTS") != null);
        map.entrySet().forEach(item -> log.info("PARAMTER_CODE: {}, PARAMETER_VALUE: {}", item.getKey(), item.getValue().getParameterValue()));
    }

    @Test
    public void findGAMCareerDataByParameterCodeIn(){
        List<GAMCareerData> parameters = gamRepository.findGAMCareerDataByParameterCodeIn(asList("PROPOINTS"));
        Assertions.assertTrue(parameters.size() == 1);
    }

}
