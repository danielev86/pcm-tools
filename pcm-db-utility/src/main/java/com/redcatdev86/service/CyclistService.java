package com.redcatdev86.service;

import com.redcatdev86.backend.CyclistDao;
import com.redcatdev86.backend.model.Cyclist;
import com.redcatdev86.ui.model.CyclistBean;

import java.util.List;
import java.util.stream.Collectors;

public class CyclistService {

    private final CyclistDao cyclistDao;

    public CyclistService() {
        this.cyclistDao = new CyclistDao();
    }

    public List<CyclistBean> getAllCyclists() {
        return cyclistDao.findAll()
                .stream()
                .map(this::toBean)
                .collect(Collectors.toList());
    }

    private CyclistBean toBean(Cyclist c) {
        return new CyclistBean(
                c.getIdCyclist(),
                c.getFirstName(),
                c.getLastName(),
                c.getFirstLastName(),
                c.getFkIdTeam(),
                c.getBirthdate(),
                c.getPopularity(),
                c.getPopularityMax(),
                c.getPotential(),
                c.getCurrentAbility(),
                c.getSize(),
                c.getWeight(),

                c.getCharPlain(), c.getLimitPlain(),
                c.getCharMountain(), c.getLimitMountain(),
                c.getCharMediumMountain(), c.getLimitMediumMountain(),
                c.getCharDownhilling(), c.getLimitDownhilling(),
                c.getCharCobble(), c.getLimitCobble(),
                c.getCharTimeTrial(), c.getLimitTimeTrial(),
                c.getCharPrologue(), c.getLimitPrologue(),
                c.getCharSprint(), c.getLimitSprint(),
                c.getCharAcceleration(), c.getLimitAcceleration(),
                c.getCharEndurance(), c.getLimitEndurance(),
                c.getCharResistance(), c.getLimitResistance(),
                c.getCharRecuperation(), c.getLimitRecuperation(),
                c.getCharHill(), c.getLimitHill(),
                c.getCharBaroudeur(), c.getLimitBaroudeur()
        );
    }
}