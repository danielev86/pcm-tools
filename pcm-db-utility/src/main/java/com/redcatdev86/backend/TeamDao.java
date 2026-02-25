package com.redcatdev86.backend;

import com.redcatdev86.backend.model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeamDao {

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
        ORDER BY gene_sz_name
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

    private static Integer getNullableInt(ResultSet rs, String col) throws Exception {
        int v = rs.getInt(col);
        return rs.wasNull() ? null : v;
    }

    private static Double getNullableDouble(ResultSet rs, String col) throws Exception {
        double v = rs.getDouble(col);
        return rs.wasNull() ? null : v;
    }
}