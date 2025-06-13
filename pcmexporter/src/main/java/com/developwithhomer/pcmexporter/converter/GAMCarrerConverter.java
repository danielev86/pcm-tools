package com.developwithhomer.pcmexporter.converter;

import com.developwithhomer.pcmexporter.controller.bean.GAMCarrerDataViewBean;
import com.developwithhomer.pcmexporter.repository.model.GAMCareerData;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GAMCarrerConverter {

    List<GAMCarrerDataViewBean> fromServiceList(List<GAMCareerData> sourceList);

}
