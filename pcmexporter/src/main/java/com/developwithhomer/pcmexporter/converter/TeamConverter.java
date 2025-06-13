package com.developwithhomer.pcmexporter.converter;

import com.developwithhomer.pcmexporter.controller.bean.TeamViewBean;
import com.developwithhomer.pcmexporter.repository.model.Team;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CyclistConverter.class, ScoutConverter.class})
public interface TeamConverter {

    TeamViewBean serviceTo(Team team);

    List<TeamViewBean> serviceToList(List<Team> team);

}
