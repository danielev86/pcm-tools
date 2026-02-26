package com.redcatdev86.service;

import com.redcatdev86.backend.TeamDao;
import com.redcatdev86.backend.model.Team;
import com.redcatdev86.ui.model.TeamBean;

import java.util.List;
import java.util.stream.Collectors;

public class TeamService {

    private final TeamDao teamDao = new TeamDao();

    public List<TeamBean> getAllTeams() {
        return teamDao.findAll().stream().map(this::toBean).collect(Collectors.toList());
    }

    public void saveTeam(TeamBean b) {
        teamDao.update(toModel(b));
    }

    private TeamBean toBean(Team t) {
        TeamBean b = new TeamBean();
        b.setIdTeam(t.getIdTeam());
        b.setShortName(t.getShortName());
        b.setName(t.getName());
        b.setJerseyAbbreviation(t.getJerseyAbbreviation());
        b.setAbbreviation(t.getAbbreviation());
        b.setLicensed(intOr0(t.getLicensed()));
        b.setFkIdCountry(intOr0(t.getFkIdCountry()));
        b.setSuffixeMail(t.getSuffixeMail());
        b.setManagerGeneral(t.getManagerGeneral());
        b.setFkIdDivision(intOr0(t.getFkIdDivision()));
        b.setFkIdNextDivision(intOr0(t.getFkIdNextDivision()));
        b.setFkIdPrevDivision(intOr0(t.getFkIdPrevDivision()));
        b.setFkIdRace(intOr0(t.getFkIdRace()));
        b.setPreraceTeam(intOr0(t.getPreraceTeam()));
        b.setSelected(intOr0(t.getSelected()));
        b.setConstant(t.getConstant());
        b.setFkIdCalendar1(intOr0(t.getFkIdCalendar1()));
        b.setFkIdCalendar2(intOr0(t.getFkIdCalendar2()));
        b.setFkIdCalendar3(intOr0(t.getFkIdCalendar3()));
        b.setCurrentEvaluation(doubleOr0(t.getCurrentEvaluation()));
        b.setPrevYearEvaluation(doubleOr0(t.getPrevYearEvaluation()));
        b.setNextYearEvaluation(doubleOr0(t.getNextYearEvaluation()));
        b.setSponsorFuture(intOr0(t.getSponsorFuture()));
        b.setDefaultPicking(intOr0(t.getDefaultPicking()));
        b.setTransferEvoBudgetMinYear(intOr0(t.getTransferEvoBudgetMinYear()));
        b.setTransferEvoRiderTypeMinYear(intOr0(t.getTransferEvoRiderTypeMinYear()));
        b.setFkIdTeamRiderTypeDistrib(intOr0(t.getFkIdTeamRiderTypeDistrib()));
        b.setRaceLike(t.getRaceLike());
        b.setRaceDislike(t.getRaceDislike());
        b.setBudget(intOr0(t.getBudget()));
        b.setColor(t.getColor());
        b.setRiderTypeImportance(doubleOr0(t.getRiderTypeImportance()));
        b.setSecondaryColor(t.getSecondaryColor());
        b.setYearBudgetUpdate(intOr0(t.getYearBudgetUpdate()));
        return b;
    }

    private Team toModel(TeamBean b) {
        return new Team(
                b.getIdTeam(),
                b.getShortName(),
                b.getName(),
                b.getJerseyAbbreviation(),
                b.getAbbreviation(),
                b.getLicensed(),
                b.getFkIdCountry(),
                b.getSuffixeMail(),
                b.getManagerGeneral(),
                b.getFkIdDivision(),
                b.getFkIdNextDivision(),
                b.getFkIdPrevDivision(),
                b.getFkIdRace(),
                b.getPreraceTeam(),
                b.getSelected(),
                b.getConstant(),
                b.getFkIdCalendar1(),
                b.getFkIdCalendar2(),
                b.getFkIdCalendar3(),
                b.getCurrentEvaluation(),
                b.getPrevYearEvaluation(),
                b.getNextYearEvaluation(),
                b.getSponsorFuture(),
                b.getDefaultPicking(),
                b.getTransferEvoBudgetMinYear(),
                b.getTransferEvoRiderTypeMinYear(),
                b.getFkIdTeamRiderTypeDistrib(),
                b.getRaceLike(),
                b.getRaceDislike(),
                b.getBudget(),
                b.getColor(),
                b.getRiderTypeImportance(),
                b.getSecondaryColor(),
                b.getYearBudgetUpdate()
        );
    }

    private static int intOr0(Integer v) { return v == null ? 0 : v; }
    private static double doubleOr0(Double v) { return v == null ? 0.0 : v; }
}