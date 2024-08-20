package com.redcatdev86.pcm24dbcore.repository;


import com.redcatdev86.pcm24dbcore.repository.mapper.CountryMapper;
import com.redcatdev86.pcm24dbcore.repository.model.Country;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class CountryRepositoryTest {

    @Autowired
    private CountryMapper countryMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void findAllCountry(){
        List<Country> countries = countryMapper.findAll();
        Assertions.assertNotNull(countries);
        if(CollectionUtils.isNotEmpty(countries)){
            countries.forEach(c -> log.info(c.toString()));
        }
    }

    @Test
    public void findCountryById(){
        Country country = countryMapper.findByCountryID(2L);
        Assertions.assertNotNull(country);
        Assertions.assertEquals(2L, country.getId());
        Assertions.assertEquals("ITA", country.getCountryCode());
    }
}
