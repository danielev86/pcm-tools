package com.redcatdev86.backend;

import com.redcatdev86.backend.model.GamCareerData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GamCareerDataDao extends CommonDao{

    public List<GamCareerData> findAll() throws SQLException {
        String sql = """
            SELECT IDcareer_data, CONSTANT, value
            FROM GAM_career_data
            ORDER BY IDcareer_data, CONSTANT
        """;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            List<GamCareerData> out = new ArrayList<>();
            while (rs.next()) {
                out.add(new GamCareerData(
                        rs.getInt("IDcareer_data"),
                        rs.getString("CONSTANT"),
                        rs.getDouble("value")
                ));
            }
            return out;
        }
    }

    public void updateValuesBatch(List<GamCareerData> rows) throws SQLException {
        String sql = """
        UPDATE GAM_career_data
        SET value = ?
        WHERE UID = ? AND CONSTANT = ?
    """;

        try (Connection conn = DatabaseManager.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (GamCareerData r : rows) {
                    ps.setDouble(1, r.getValue());
                    ps.setInt(2, r.getUid());
                    ps.setString(3, r.getConstant());
                    ps.addBatch();
                }
                ps.executeBatch();
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

}
