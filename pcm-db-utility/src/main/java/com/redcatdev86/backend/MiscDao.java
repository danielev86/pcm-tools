package com.redcatdev86.backend;

import com.redcatdev86.backend.model.CyclistMisc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MiscDao extends CommonDao{

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

    public int resetFatigueForCyclist(int idCyclist) {
        String sql = """
            UPDATE DYN_cyclist_fitness
            SET value_f_fat_phy = 0,
                value_f_freshness = 60000
            WHERE IDcyclist = ?
            """;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCyclist);
            return ps.executeUpdate();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<CyclistMisc> findAllCyclistsMiscOrderedByLastName() {

        String sql = """
            SELECT IDcyclist, gene_sz_firstname, gene_sz_lastname
            FROM DYN_cyclist
            ORDER BY gene_sz_lastname, gene_sz_firstname
            """;

        List<CyclistMisc> result = new ArrayList<>();

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CyclistMisc c = new CyclistMisc();
                c.setIdCyclist(rs.getInt("IDcyclist"));
                c.setFirstName(rs.getString("gene_sz_firstname"));
                c.setLastName(rs.getString("gene_sz_lastname"));
                result.add(c);
            }

            return result;

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}