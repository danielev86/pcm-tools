package com.redcatdev86.service;

import com.redcatdev86.backend.TeamDao;
import com.redcatdev86.backend.model.Team;
import com.redcatdev86.ui.model.TeamBean;

import java.util.List;
import java.util.stream.Collectors;

public class TeamService {

    private final TeamDao teamDao;

    public TeamService() {
        this.teamDao = new TeamDao();
    }

    public List<TeamBean> getAllTeams() {
        List<Team> teams = teamDao.findAll();
        return teams.stream()
                .map(this::toBean)
                .collect(Collectors.toList());
    }

    private TeamBean toBean(Team t) {
        return new TeamBean(
                t.getIdTeam(),
                t.getShortName(),
                t.getName(),
                t.getJerseyAbbreviation(),
                t.getAbbreviation(),
                t.getLicensed(),
                t.getFkIdCountry(),
                t.getSuffixEmail(),
                t.getManagerGeneral(),
                t.getFkIdDivision(),
                t.getFkIdNextDivision(),
                t.getFkIdPrevDivision(),
                t.getFkIdRace(),
                t.getPreRaceTeam(),
                t.getSelected(),
                t.getConstant(),
                t.getFkIdCalendar1(),
                t.getFkIdCalendar2(),
                t.getFkIdCalendar3(),
                t.getCurrentEvaluation(),
                t.getPrevYearEvaluation(),
                t.getNextYearEvaluation(),
                t.getSponsorFuture(),
                t.getDefaultPicking(),
                t.getTransferEvoBudgetMinYear(),
                t.getTransferEvoRiderTypeMinYear(),
                t.getFkIdTeamRiderTypeDistrib(),
                t.getRaceLikeList(),
                t.getRaceDislikeList(),
                t.getBudget(),
                t.getColor(),
                t.getRiderTypeImportance(),
                t.getSecondaryColor(),
                t.getYearBudgetUpdate()
        );
    }
}