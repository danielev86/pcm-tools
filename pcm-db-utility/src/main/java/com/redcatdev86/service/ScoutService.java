package com.redcatdev86.service;

import com.redcatdev86.backend.ScoutDao;
import com.redcatdev86.backend.model.Scout;
import com.redcatdev86.ui.model.ScoutBean;

import java.util.List;
import java.util.stream.Collectors;

public class ScoutService {

    private final ScoutDao scoutDao;

    public ScoutService() {
        this.scoutDao = new ScoutDao();
    }

    public ScoutService(ScoutDao scoutDao) {
        this.scoutDao = scoutDao;
    }

    public List<ScoutBean> getAllScouts() {
        List<Scout> scouts = scoutDao.findAll();
        return scouts.stream()
                .map(this::toBean)
                .collect(Collectors.toList());
    }

    public void updateWageAndContractEnd(int idScout, int wage, int contractEnd) {
        scoutDao.updateWageAndContractEnd(idScout, wage, contractEnd);
    }

    private ScoutBean toBean(Scout s) {
        return new ScoutBean(
                s.getIdScout(),
                s.getFirstName(),
                s.getLastName(),
                s.getFkIdTeam(),
                s.getWage(),
                s.getContractEnd(),
                s.getTr()
        );
    }
}