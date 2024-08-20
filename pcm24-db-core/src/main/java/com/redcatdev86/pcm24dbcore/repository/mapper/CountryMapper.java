package com.redcatdev86.pcm24dbcore.repository.mapper;

import com.redcatdev86.pcm24dbcore.repository.model.Country;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CountryMapper {

    @Select("SELECT country.IDcountry as ID" +
            ", country.CONSTANT as countyCode" +
            ", country.gene_sz_flag as countryName" +
            ", country.value_i_training_structure as trainingStructure" +
            ", country.value_i_cycling_popularity as cyclingPopularity" +
            " FROM STA_country as country ")
    @ResultType(value = Country.class)
    List<Country> findAll();

    @Select("SELECT country.IDcountry as ID" +
            ", country.CONSTANT as countryCode" +
            ", country.gene_sz_flag as countryName" +
            ", country.value_i_training_structure as trainingStructure" +
            ", country.value_i_cycling_popularity as cyclingPopularity" +
            " FROM STA_country as country " +
            " WHERE country.IDcountry = #{countryId}")
    @ResultType(value = Country.class)
    Country findByCountryID(@Param("countryId") Long countryId);

}
