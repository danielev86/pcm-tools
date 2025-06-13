package com.developwithhomer.pcmexporter.service.impl;

import com.developwithhomer.pcmexporter.controller.bean.CyclistViewBean;
import com.developwithhomer.pcmexporter.converter.CyclistConverter;
import com.developwithhomer.pcmexporter.repository.CyclistRepository;
import com.developwithhomer.pcmexporter.repository.model.Cyclist;
import com.developwithhomer.pcmexporter.service.CyclistService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class CyclistServiceImpl implements CyclistService {

    @Autowired
    private CyclistRepository cyclistRepository;

    @Autowired
    private CyclistConverter cyclistConverter;

    public List<CyclistViewBean> getCyclystsByTeamId(Long idTeam){
        List<Cyclist> cyclists = cyclistRepository.findCyclistByTeam(idTeam);
        List<CyclistViewBean> cyclistList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(cyclists)){
            cyclistList = cyclistConverter.fromServiceList(cyclists);
        }
        return cyclistList;
    }

}
