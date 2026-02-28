package com.redcatdev86.service;

import com.redcatdev86.backend.MiscDao;

public class MiscService {

    private final MiscDao dao = new MiscDao();

    public int resetFatigueForTeam(int idTeam) {
        return dao.resetFatigueForTeam(idTeam);
    }

    public int setAllCyclistsAvailableToTransfer() {
        return dao.setAllCyclistsAvailableToTransfer();
    }
}