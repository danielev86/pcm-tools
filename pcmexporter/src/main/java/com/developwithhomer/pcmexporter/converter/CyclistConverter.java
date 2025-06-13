package com.developwithhomer.pcmexporter.converter;

import com.developwithhomer.pcmexporter.controller.bean.CyclistViewBean;
import com.developwithhomer.pcmexporter.repository.model.Cyclist;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CyclistConverter {

    CyclistViewBean fromService(Cyclist cyclist);

    List<CyclistViewBean> fromServiceList(List<Cyclist> cyclists);

}
