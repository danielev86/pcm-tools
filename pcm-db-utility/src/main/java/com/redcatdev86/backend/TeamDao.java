package com.redcatdev86.backend;

import com.redcatdev86.backend.model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeamDao extends CommonDao{

    private static final String SQL_FIND_ALL = """
        SELECT
          IDteam,
          gene_sz_shortname,
          gene_sz_name,
          jersey_sz_abbreviation,
          abbreviation,
          gene_b_licensed,
          fkIDcountry,
          gene_sz_suffixeMail,
          gene_sz_manager_general,
          fkIDdivision,
          fkIDnextdivision,
          fkIDprevdivision,
          fkIDrace,
          prerace_i_team,
          gene_b_selected,
          CONSTANT,
          fkIDcalendar1,
          fkIDcalendar2,
          fkIDcalendar3,
          value_f_current_evaluation,
          value_f_prevyear_evaluation,
          value_f_nextyear_evaluation,
          value_i_sponsor_future,
          gene_b_default_picking,
          gene_i_transfer_evo_budget_min_year,
          gene_i_transfer_evo_ridertype_min_year,
          fkIDteam_ridertype_distrib,
          value_ilist_race_like,
          value_ilist_race_dislike,
          value_i_budget,
          gene_sz_color,
          value_f_ridertype_importance,
          gene_sz_secondary_color,
          gene_i_YearBudgetUpdate
        FROM DYN_team
        ORDER BY IDteam
        """;

    private static final String SQL_UPDATE = """
        UPDATE DYN_team SET
          gene_sz_shortname = ?,
          gene_sz_name = ?,
          jersey_sz_abbreviation = ?,
          abbreviation = ?,
          gene_b_licensed = ?,
          fkIDcountry = ?,
          gene_sz_suffixeMail = ?,
          gene_sz_manager_general = ?,
          fkIDdivision = ?,
          fkIDnextdivision = ?,
          fkIDprevdivision = ?,
          fkIDrace = ?,
          prerace_i_team = ?,
          gene_b_selected = ?,
          CONSTANT = ?,
          fkIDcalendar1 = ?,
          fkIDcalendar2 = ?,
          fkIDcalendar3 = ?,
          value_f_current_evaluation = ?,
          value_f_prevyear_evaluation = ?,
          value_f_nextyear_evaluation = ?,
          value_i_sponsor_future = ?,
          gene_b_default_picking = ?,
          gene_i_transfer_evo_budget_min_year = ?,
          gene_i_transfer_evo_ridertype_min_year = ?,
          fkIDteam_ridertype_distrib = ?,
          value_ilist_race_like = ?,
          value_ilist_race_dislike = ?,
          value_i_budget = ?,
          gene_sz_color = ?,
          value_f_ridertype_importance = ?,
          gene_sz_secondary_color = ?,
          gene_i_YearBudgetUpdate = ?
        WHERE IDteam = ?
        """;

    public List<Team> findAll() {
        List<Team> out = new ArrayList<>();
        try (Connection c = DatabaseManager.getConnection();
             PreparedStatement ps = c.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                out.add(new Team(
                        rs.getInt("IDteam"),
                        rs.getString("gene_sz_shortname"),
                        rs.getString("gene_sz_name"),
                        rs.getString("jersey_sz_abbreviation"),
                        rs.getString("abbreviation"),
                        getNullableInt(rs, "gene_b_licensed"),
                        getNullableInt(rs, "fkIDcountry"),
                        rs.getString("gene_sz_suffixeMail"),
                        rs.getString("gene_sz_manager_general"),
                        getNullableInt(rs, "fkIDdivision"),
                        getNullableInt(rs, "fkIDnextdivision"),
                        getNullableInt(rs, "fkIDprevdivision"),
                        getNullableInt(rs, "fkIDrace"),
                        getNullableInt(rs, "prerace_i_team"),
                        getNullableInt(rs, "gene_b_selected"),
                        rs.getString("CONSTANT"),
                        getNullableInt(rs, "fkIDcalendar1"),
                        getNullableInt(rs, "fkIDcalendar2"),
                        getNullableInt(rs, "fkIDcalendar3"),
                        getNullableDouble(rs, "value_f_current_evaluation"),
                        getNullableDouble(rs, "value_f_prevyear_evaluation"),
                        getNullableDouble(rs, "value_f_nextyear_evaluation"),
                        getNullableInt(rs, "value_i_sponsor_future"),
                        getNullableInt(rs, "gene_b_default_picking"),
                        getNullableInt(rs, "gene_i_transfer_evo_budget_min_year"),
                        getNullableInt(rs, "gene_i_transfer_evo_ridertype_min_year"),
                        getNullableInt(rs, "fkIDteam_ridertype_distrib"),
                        rs.getString("value_ilist_race_like"),
                        rs.getString("value_ilist_race_dislike"),
                        getNullableInt(rs, "value_i_budget"),
                        rs.getString("gene_sz_color"),
                        getNullableDouble(rs, "value_f_ridertype_importance"),
                        rs.getString("gene_sz_secondary_color"),
                        getNullableInt(rs, "gene_i_YearBudgetUpdate")
                ));
            }
            return out;

        } catch (Exception e) {
            throw new RuntimeException("TeamDao.findAll failed", e);
        }
    }

    public void update(Team t) {
        try (Connection c = DatabaseManager.getConnection();
             PreparedStatement ps = c.prepareStatement(SQL_UPDATE)) {

            int i = 1;
            ps.setString(i++, t.getShortName());
            ps.setString(i++, t.getName());
            ps.setString(i++, t.getJerseyAbbreviation());
            ps.setString(i++, t.getAbbreviation());
            setNullableInt(ps, i++, t.getLicensed());
            setNullableInt(ps, i++, t.getFkIdCountry());
            ps.setString(i++, t.getSuffixeMail());
            ps.setString(i++, t.getManagerGeneral());
            setNullableInt(ps, i++, t.getFkIdDivision());
            setNullableInt(ps, i++, t.getFkIdNextDivision());
            setNullableInt(ps, i++, t.getFkIdPrevDivision());
            setNullableInt(ps, i++, t.getFkIdRace());
            setNullableInt(ps, i++, t.getPreraceTeam());
            setNullableInt(ps, i++, t.getSelected());
            ps.setString(i++, t.getConstant());
            setNullableInt(ps, i++, t.getFkIdCalendar1());
            setNullableInt(ps, i++, t.getFkIdCalendar2());
            setNullableInt(ps, i++, t.getFkIdCalendar3());
            setNullableDouble(ps, i++, t.getCurrentEvaluation());
            setNullableDouble(ps, i++, t.getPrevYearEvaluation());
            setNullableDouble(ps, i++, t.getNextYearEvaluation());
            setNullableInt(ps, i++, t.getSponsorFuture());
            setNullableInt(ps, i++, t.getDefaultPicking());
            setNullableInt(ps, i++, t.getTransferEvoBudgetMinYear());
            setNullableInt(ps, i++, t.getTransferEvoRiderTypeMinYear());
            setNullableInt(ps, i++, t.getFkIdTeamRiderTypeDistrib());
            ps.setString(i++, t.getRaceLike());
            ps.setString(i++, t.getRaceDislike());
            setNullableInt(ps, i++, t.getBudget());
            ps.setString(i++, t.getColor());
            setNullableDouble(ps, i++, t.getRiderTypeImportance());
            ps.setString(i++, t.getSecondaryColor());
            setNullableInt(ps, i++, t.getYearBudgetUpdate());

            ps.setInt(i, t.getIdTeam());

            int updated = ps.executeUpdate();
            if (updated != 1) throw new RuntimeException("Team update failed. Rows updated=" + updated);

        } catch (Exception e) {
            throw new RuntimeException("TeamDao.update failed", e);
        }
    }
}