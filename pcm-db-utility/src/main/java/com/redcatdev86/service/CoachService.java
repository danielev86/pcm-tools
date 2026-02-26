package com.redcatdev86.service;

import com.redcatdev86.backend.CoachDao;
import com.redcatdev86.backend.model.Coach;
import com.redcatdev86.ui.model.CoachBean;

import java.util.List;
import java.util.stream.Collectors;

public class CoachService {

    private final CoachDao coachDao = new CoachDao();

    public List<CoachBean> getAllCoaches() {
        return coachDao.findAll().stream().map(this::toBean).collect(Collectors.toList());
    }

    public void saveEditable(CoachBean b) {
        coachDao.updateEditableFields(
                b.getIdCoach(),
                b.getWorkAmount(),
                b.getWage(),
                b.getContractEnd()
        );
    }

    private CoachBean toBean(Coach c) {
        CoachBean b = new CoachBean();
        b.setIdCoach(c.getIdCoach());
        b.setFirstName(c.getFirstName());
        b.setLastName(c.getLastName());
        b.setFkIdTeam(c.getFkIdTeam() == null ? 0 : c.getFkIdTeam());
        b.setFkIdRegion(c.getFkIdRegion() == null ? 0 : c.getFkIdRegion());
        b.setFkIdFame(c.getFkIdFame() == null ? 0 : c.getFkIdFame());
        b.setWorkAmount(c.getWorkAmount() == null ? 0 : c.getWorkAmount());
        b.setWage(c.getWage() == null ? 0 : c.getWage());
        b.setContractEnd(c.getContractEnd() == null ? 0 : c.getContractEnd());
        b.setTrainingStyle(c.getTrainingStyle() == null ? 0 : c.getTrainingStyle());
        return b;
    }
}