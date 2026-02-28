package com.redcatdev86.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MiscDao {

    public int resetFatigueForTeam(int idTeam) {
        String sql = """
            UPDATE DYN_cyclist_fitness
            SET value_f_fat_phy = 0, value_f_freshness = 60000
            WHERE IDcyclist IN (
                SELECT IDcyclist
                FROM DYN_cyclist
                WHERE fkIDteam = ?
            )
            """;

        try (Connection c = DatabaseManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, idTeam);
            return ps.executeUpdate();

        } catch (Exception ex) {
            throw new RuntimeException("MiscDao.resetFatigueForTeam failed", ex);
        }
    }

    public int setAllCyclistsAvailableToTransfer() {
        String sql = "UPDATE DYN_transfer_available_cyclist SET gene_f_interest = 100";

        try (Connection c = DatabaseManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            return ps.executeUpdate();

        } catch (Exception ex) {
            throw new RuntimeException("MiscDao.setAllCyclistsAvailableToTransfer failed", ex);
        }
    }
}