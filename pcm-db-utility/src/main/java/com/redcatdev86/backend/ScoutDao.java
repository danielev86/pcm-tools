package com.redcatdev86.backend;

import com.redcatdev86.backend.model.Scout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ScoutDao  extends CommonDao{

    private static final String SQL_FIND_ALL = """
        SELECT
            IDscout,
            gene_sz_firstname,
            gene_sz_lastname,
            fkIDteam,
            finan_i_wage,
            gene_i_contract_end,
            gene_i_TR
        FROM DYN_scout
        ORDER BY IDscout
        """;

    public List<Scout> findAll() {
        List<Scout> out = new ArrayList<>();

        try (Connection c = DatabaseManager.getConnection();
             PreparedStatement ps = c.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Scout s = new Scout(
                        rs.getInt("IDscout"),
                        rs.getString("gene_sz_firstname"),
                        rs.getString("gene_sz_lastname"),
                        getNullableInt(rs, "fkIDteam"),
                        getNullableInt(rs, "finan_i_wage"),
                        getNullableInt(rs, "gene_i_contract_end"),
                        getNullableInt(rs, "gene_i_TR")
                );
                out.add(s);
            }

            return out;

        } catch (Exception e) {
            throw new RuntimeException("ScoutDao.findAll failed", e);
        }
    }

    public void updateWageAndContractEnd(int idScout, int wage, int contractEnd) {

        String sql = """
        UPDATE DYN_scout
        SET finan_i_wage = ?, gene_i_contract_end = ?
        WHERE IDscout = ?
        """;

        try (var c = DatabaseManager.getConnection();
             var ps = c.prepareStatement(sql)) {

            ps.setInt(1, wage);
            ps.setInt(2, contractEnd);
            ps.setInt(3, idScout);

            int updated = ps.executeUpdate();
            if (updated != 1) {
                throw new RuntimeException("Update failed: updated rows = " + updated);
            }

        } catch (Exception e) {
            throw new RuntimeException("ScoutDao.updateWageAndContractEnd failed", e);
        }
    }

}