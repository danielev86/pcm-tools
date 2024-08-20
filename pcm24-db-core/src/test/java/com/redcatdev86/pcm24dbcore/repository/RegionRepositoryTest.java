package com.redcatdev86.pcm24dbcore.repository;


import com.redcatdev86.pcm24dbcore.repository.mapper.RegionMapper;
import com.redcatdev86.pcm24dbcore.repository.model.Region;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class RegionRepositoryTest {

    @Autowired
    private RegionMapper regionMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void findAllRegionTest(){
        List<Region> regions = regionMapper.findAllRegion();
        Assertions.assertNotNull(regions);
    }

    @Test
    public void findRegionByIdTest(){
        Region region = regionMapper.findRegionById(202L);
        Assertions.assertNotNull(region);
        Assertions.assertEquals(202L, region.getId());
        Assertions.assertEquals("Piemonte", region.getRegionName());
    }
}
