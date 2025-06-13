package com.developwithhomer.pcmexporter.converter;

import com.developwithhomer.pcmexporter.controller.bean.ScoutViewBean;
import com.developwithhomer.pcmexporter.repository.model.Scout;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ScoutConverter {

    ScoutViewBean convertToFront(Scout scout);

    Scout convertToBack(Scout scout);

    Set<ScoutViewBean> convertToSet(Set<Scout> scout);

}
