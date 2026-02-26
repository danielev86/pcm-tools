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

    public void updateSkills(int idCyclist,
                             int plain, int mountain, int mediumMountain, int downhilling,
                             int cobble, int timetrial, int prologue, int sprint,
                             int acceleration, int endurance, int resistance, int recuperation,
                             int hill, int baroudeur) {

        cyclistDao.updateSkills(idCyclist,
                plain, mountain, mediumMountain, downhilling,
                cobble, timetrial, prologue, sprint,
                acceleration, endurance, resistance, recuperation,
                hill, baroudeur);
    }

    private CyclistBean toBean(Cyclist c) {
        CyclistBean b = new CyclistBean();

        b.setIdCyclist(c.getIdCyclist());
        b.setFirstName(c.getFirstName());
        b.setLastName(c.getLastName());
        b.setFirstLastName(c.getFirstLastName());

        // fk team può essere null nel backend
        b.setFkIdTeam(c.getFkIdTeam() == null ? 0 : c.getFkIdTeam());

        // questi nel tuo backend model erano wrapper (Integer/Double) -> null-safe
        b.setCurrentAbility(c.getCurrentAbility() == null ? 0.0 : c.getCurrentAbility());
        b.setPotential(c.getPotential() == null ? 0.0 : c.getPotential());

        // skills + limits (null-safe)
        b.setCharPlain(c.getCharPlain() == null ? 0 : c.getCharPlain());
        b.setLimitPlain(c.getLimitPlain() == null ? 0 : c.getLimitPlain());

        b.setCharMountain(c.getCharMountain() == null ? 0 : c.getCharMountain());
        b.setLimitMountain(c.getLimitMountain() == null ? 0 : c.getLimitMountain());

        b.setCharMediumMountain(c.getCharMediumMountain() == null ? 0 : c.getCharMediumMountain());
        b.setLimitMediumMountain(c.getLimitMediumMountain() == null ? 0 : c.getLimitMediumMountain());

        b.setCharDownhilling(c.getCharDownhilling() == null ? 0 : c.getCharDownhilling());
        b.setLimitDownhilling(c.getLimitDownhilling() == null ? 0 : c.getLimitDownhilling());

        b.setCharCobble(c.getCharCobble() == null ? 0 : c.getCharCobble());
        b.setLimitCobble(c.getLimitCobble() == null ? 0 : c.getLimitCobble());

        b.setCharTimeTrial(c.getCharTimeTrial() == null ? 0 : c.getCharTimeTrial());
        b.setLimitTimeTrial(c.getLimitTimeTrial() == null ? 0 : c.getLimitTimeTrial());

        b.setCharPrologue(c.getCharPrologue() == null ? 0 : c.getCharPrologue());
        b.setLimitPrologue(c.getLimitPrologue() == null ? 0 : c.getLimitPrologue());

        b.setCharSprint(c.getCharSprint() == null ? 0 : c.getCharSprint());
        b.setLimitSprint(c.getLimitSprint() == null ? 0 : c.getLimitSprint());

        b.setCharAcceleration(c.getCharAcceleration() == null ? 0 : c.getCharAcceleration());
        b.setLimitAcceleration(c.getLimitAcceleration() == null ? 0 : c.getLimitAcceleration());

        b.setCharEndurance(c.getCharEndurance() == null ? 0 : c.getCharEndurance());
        b.setLimitEndurance(c.getLimitEndurance() == null ? 0 : c.getLimitEndurance());

        b.setCharResistance(c.getCharResistance() == null ? 0 : c.getCharResistance());
        b.setLimitResistance(c.getLimitResistance() == null ? 0 : c.getLimitResistance());

        b.setCharRecuperation(c.getCharRecuperation() == null ? 0 : c.getCharRecuperation());
        b.setLimitRecuperation(c.getLimitRecuperation() == null ? 0 : c.getLimitRecuperation());

        b.setCharHill(c.getCharHill() == null ? 0 : c.getCharHill());
        b.setLimitHill(c.getLimitHill() == null ? 0 : c.getLimitHill());

        b.setCharBaroudeur(c.getCharBaroudeur() == null ? 0 : c.getCharBaroudeur());
        b.setLimitBaroudeur(c.getLimitBaroudeur() == null ? 0 : c.getLimitBaroudeur());

        return b;
    }

}