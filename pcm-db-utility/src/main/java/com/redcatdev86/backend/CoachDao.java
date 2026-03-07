package com.redcatdev86.backend;

import com.redcatdev86.backend.model.Coach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CoachDao extends CommonDao{

    private static final String SQL_FIND_ALL = """
        SELECT
          IDcoach,
          gene_sz_firstname,
          gene_sz_lastname,
          fkIDteam,
          fkIDregion,
          fkIDfame,
          gene_i_work_amount,
          finan_i_wage,
          gene_i_contract_end,
          gene_i_training_style
        FROM DYN_coach
        ORDER BY IDcoach
        """;

    private static final String SQL_UPDATE_EDITABLE = """
        UPDATE DYN_coach
        SET
          gene_i_work_amount = ?,
          finan_i_wage = ?,
          gene_i_contract_end = ?
        WHERE IDcoach = ?
        """;

    public List<Coach> findAll() {
        List<Coach> out = new ArrayList<>();
        try (Connection c = DatabaseManager.getConnection();
             PreparedStatement ps = c.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                out.add(new Coach(
                        rs.getInt("IDcoach"),
                        rs.getString("gene_sz_firstname"),
                        rs.getString("gene_sz_lastname"),
                        getNullableInt(rs, "fkIDteam"),
                        getNullableInt(rs, "fkIDregion"),
                        getNullableInt(rs, "fkIDfame"),
                        getNullableInt(rs, "gene_i_work_amount"),
                        getNullableInt(rs, "finan_i_wage"),
                        getNullableInt(rs, "gene_i_contract_end"),
                        getNullableInt(rs, "gene_i_training_style")
                ));
            }
            return out;

        } catch (Exception e) {
            throw new RuntimeException("CoachDao.findAll failed", e);
        }
    }

    public void updateEditableFields(int idCoach, Integer workAmount, Integer wage, Integer contractEnd) {
        try (Connection c = DatabaseManager.getConnection();
             PreparedStatement ps = c.prepareStatement(SQL_UPDATE_EDITABLE)) {

            setNullableInt(ps, 1, workAmount);
            setNullableInt(ps, 2, wage);
            setNullableInt(ps, 3, contractEnd);
            ps.setInt(4, idCoach);

            int updated = ps.executeUpdate();
            if (updated != 1) throw new RuntimeException("Coach update failed. Rows updated=" + updated);

        } catch (Exception e) {
            throw new RuntimeException("CoachDao.updateEditableFields failed", e);
        }
    }
}