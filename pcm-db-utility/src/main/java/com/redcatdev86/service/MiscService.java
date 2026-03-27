package com.redcatdev86.service;

import com.redcatdev86.backend.MiscDao;
import com.redcatdev86.backend.model.CyclistMisc;
import com.redcatdev86.ui.model.CyclistMiscBean;

import java.util.List;

public class MiscService {

    private final MiscDao dao = new MiscDao();

    public int resetFatigueForTeam(int idTeam) {
        return dao.resetFatigueForTeam(idTeam);
    }

    public int setAllCyclistsAvailableToTransfer() {
        return dao.setAllCyclistsAvailableToTransfer();
    }

    public List<CyclistMiscBean> getAllCyclistsMisc() {
        return dao.findAllCyclistsMiscOrderedByLastName()
                .stream()
                .map(c -> this.fromBackend(c))
                .toList();
    }

    public int resetFatigueForCyclist(int idCyclist) {
        return dao.resetFatigueForCyclist(idCyclist);
    }

    public CyclistMiscBean fromBackend(CyclistMisc c) {
        CyclistMiscBean b = new CyclistMiscBean();
        b.setIdCyclist(c.getIdCyclist());
        b.setFirstName(c.getFirstName());
        b.setLastName(c.getLastName());
        return b;
    }
}