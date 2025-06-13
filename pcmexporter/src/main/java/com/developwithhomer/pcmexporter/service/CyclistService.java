package com.developwithhomer.pcmexporter.service;

import com.developwithhomer.pcmexporter.controller.bean.CyclistViewBean;

import java.util.List;

public interface CyclistService {

    List<CyclistViewBean> getCyclystsByTeamId(Long idTeam);

}
