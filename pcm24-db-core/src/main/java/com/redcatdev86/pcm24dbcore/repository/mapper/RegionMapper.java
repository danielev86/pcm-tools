package com.redcatdev86.pcm24dbcore.repository.mapper;


import com.redcatdev86.pcm24dbcore.repository.model.Country;
import com.redcatdev86.pcm24dbcore.repository.model.Region;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface RegionMapper {

    @Select("SELECT region.IDregion, region.CONSTANT, region.fkIDcountry " +
            "FROM STA_region region")
    @Results(id="regionMapper", value = {
            @Result(property = "id", column = "IDregion"),
            @Result(property = "regionName", column = "CONSTANT"),
            @Result(property = "country", javaType = Country.class, column = "fkIDcountry"
                    , one = @One(select = "com.redcatdev86.pcm24dbcore.repository.mapper.CountryMapper.findByCountryID", fetchType = FetchType.EAGER))
    })
    List<Region> findAllRegion();

    @Select(" SELECT region.IDregion, region.CONSTANT, region.fkIDcountry " +
            " FROM STA_region region " +
            " WHERE region.IDregion = #{regionId}")
    @ResultMap(value = "regionMapper")
    Region findRegionById(@Param("regionId") Long regionId);


}
