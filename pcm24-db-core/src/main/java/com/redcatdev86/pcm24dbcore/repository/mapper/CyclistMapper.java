package com.redcatdev86.pcm24dbcore.repository.mapper;


import com.redcatdev86.pcm24dbcore.repository.handler.BirthdateTypeHandler;
import com.redcatdev86.pcm24dbcore.repository.model.Cyclist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CyclistMapper {


    @Select(value = "SELECT c.IDcyclist, c.gene_sz_lastname" +
            ", c.gene_sz_firstname, c.gene_i_birthdate" +
            ", c.charac_i_plain, c.limit_i_plain" +
            ", c.charac_i_mountain, c.limit_i_mountain" +
            ", c.charac_i_medium_mountain, c.limit_i_medium_mountain" +
            ", c.charac_i_downhilling, c.limit_i_downhilling" +
            ", c.charac_i_cobble, c.limit_i_cobble" +
            ", c.charac_i_timetrial, c.limit_i_timetrial" +
            ", c.charac_i_prologue, c.limit_i_prologue" +
            ", c.charac_i_sprint, c.limit_i_sprint" +
            ", c.charac_i_acceleration, c.limit_i_acceleration" +
            ", c.charac_i_endurance, c.limit_i_endurance" +
            ", c.charac_i_resistance, c.limit_i_resistance" +
            ", c.charac_i_recuperation, c.limit_i_recuperation" +
            ", c.charac_i_hill, c.limit_i_hill" +
            ", c.charac_i_baroudeur, c.limit_i_baroudeur" +

            " FROM DYN_cyclist c ")
    @Results(id="cyclistDetailMapper", value = {
            @Result(property = "id", column = "IDcyclist"),
            @Result(property = "lastName", column = "gene_sz_lastname"),
            @Result(property = "firstName", column = "gene_sz_firstname"),
            @Result(property = "birthdate", column = "gene_i_birthdate", typeHandler = BirthdateTypeHandler.class),
            @Result(property = "cPlain", column = "charac_i_plain"),
            @Result(property = "cMountain", column = "charac_i_mountain"),
            @Result(property = "cMediumMontain", column = "charac_i_medium_mountain"),
            @Result(property = "cDownhilling", column = "charac_i_downhilling"),
            @Result(property = "cCobble", column = "charac_i_cobble"),
            @Result(property = "cTimetrial", column = "charac_i_timetrial"),
            @Result(property = "cPrologue", column = "charac_i_prologue"),
            @Result(property = "cSprint", column = "charac_i_sprint"),
            @Result(property = "cAcceleration", column = "charac_i_acceleration"),
            @Result(property = "cEndurance", column = "charac_i_endurance"),
            @Result(property = "cResistance", column = "charac_i_resistance"),
            @Result(property = "cRecuperation", column = "charac_i_recuperation"),
            @Result(property = "cHill", column = "charac_i_hill"),
            @Result(property = "cBaroudeur", column = "charac_i_baroudeur"),
            @Result(property = "lPlain", column = "limit_i_plain"),
            @Result(property = "lMountain", column = "limit_i_mountain"),
            @Result(property = "lMediumMontain", column = "limit_i_medium_mountain"),
            @Result(property = "lDownhilling", column = "limit_i_downhilling"),
            @Result(property = "lCobble", column = "limit_i_cobble"),
            @Result(property = "lTimetrial", column = "limit_i_timetrial"),
            @Result(property = "lPrologue", column = "limit_i_prologue"),
            @Result(property = "lSprint", column = "limit_i_sprint"),
            @Result(property = "lAcceleration", column = "limit_i_acceleration"),
            @Result(property = "lEndurance", column = "limit_i_endurance"),
            @Result(property = "lResistance", column = "limit_i_resistance"),
            @Result(property = "lRecuperation", column = "limit_i_recuperation"),
            @Result(property = "lHill", column = "limit_i_hill"),
            @Result(property = "lBaroudeur", column = "limit_i_baroudeur")
    })
    List<Cyclist> findAllCyclist();
}
