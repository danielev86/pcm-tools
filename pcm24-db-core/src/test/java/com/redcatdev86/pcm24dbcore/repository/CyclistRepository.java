package com.redcatdev86.pcm24dbcore.repository;


import com.redcatdev86.pcm24dbcore.repository.mapper.CyclistMapper;
import com.redcatdev86.pcm24dbcore.repository.model.Cyclist;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class CyclistRepository {

    @Autowired
    private CyclistMapper cyclistMapper;

    @Test
    public void findAllCyclist(){
        List<Cyclist> cyclists = cyclistMapper.findAllCyclist();
        Assertions.assertNotNull(cyclists);
    }
}
