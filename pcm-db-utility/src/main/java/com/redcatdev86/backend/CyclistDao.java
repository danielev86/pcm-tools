package com.redcatdev86.backend;

import com.redcatdev86.backend.model.Cyclist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CyclistDao extends CommonDao{

    private static final String SQL_FIND_ALL = """
        SELECT
          IDcyclist,
          gene_sz_firstname,
          gene_sz_lastname,
          gene_sz_firstlastname,
          fkIDteam,
          gene_i_birthdate,
          gene_f_popularity,
          gene_f_popularity_max,
          value_f_potentiel,
          value_f_current_ability,
          gene_i_size,
          gene_i_weight,

          charac_i_plain, limit_i_plain,
          charac_i_mountain, limit_i_mountain,
          charac_i_medium_mountain, limit_i_medium_mountain,
          charac_i_downhilling, limit_i_downhilling,
          charac_i_cobble, limit_i_cobble,
          charac_i_timetrial, limit_i_timetrial,
          charac_i_prologue, limit_i_prologue,
          charac_i_sprint, limit_i_sprint,
          charac_i_acceleration, limit_i_acceleration,
          charac_i_endurance, limit_i_endurance,
          charac_i_resistance, limit_i_resistance,
          charac_i_recuperation, limit_i_recuperation,
          charac_i_hill, limit_i_hill,
          charac_i_baroudeur, limit_i_baroudeur
        FROM DYN_cyclist
        ORDER BY IDcyclist
        """;

    public List<Cyclist> findAll() {
        List<Cyclist> out = new ArrayList<>();

        try (Connection c = DatabaseManager.getConnection();
             PreparedStatement ps = c.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                out.add(new Cyclist(
                        rs.getInt("IDcyclist"),
                        rs.getString("gene_sz_firstname"),
                        rs.getString("gene_sz_lastname"),
                        rs.getString("gene_sz_firstlastname"),
                        getNullableInt(rs, "fkIDteam"),
                        getNullableInt(rs, "gene_i_birthdate"),
                        getNullableDouble(rs, "gene_f_popularity"),
                        getNullableDouble(rs, "gene_f_popularity_max"),
                        getNullableDouble(rs, "value_f_potentiel"),
                        getNullableDouble(rs, "value_f_current_ability"),
                        getNullableInt(rs, "gene_i_size"),
                        getNullableInt(rs, "gene_i_weight"),

                        getNullableInt(rs, "charac_i_plain"), getNullableInt(rs, "limit_i_plain"),
                        getNullableInt(rs, "charac_i_mountain"), getNullableInt(rs, "limit_i_mountain"),
                        getNullableInt(rs, "charac_i_medium_mountain"), getNullableInt(rs, "limit_i_medium_mountain"),
                        getNullableInt(rs, "charac_i_downhilling"), getNullableInt(rs, "limit_i_downhilling"),
                        getNullableInt(rs, "charac_i_cobble"), getNullableInt(rs, "limit_i_cobble"),
                        getNullableInt(rs, "charac_i_timetrial"), getNullableInt(rs, "limit_i_timetrial"),
                        getNullableInt(rs, "charac_i_prologue"), getNullableInt(rs, "limit_i_prologue"),
                        getNullableInt(rs, "charac_i_sprint"), getNullableInt(rs, "limit_i_sprint"),
                        getNullableInt(rs, "charac_i_acceleration"), getNullableInt(rs, "limit_i_acceleration"),
                        getNullableInt(rs, "charac_i_endurance"), getNullableInt(rs, "limit_i_endurance"),
                        getNullableInt(rs, "charac_i_resistance"), getNullableInt(rs, "limit_i_resistance"),
                        getNullableInt(rs, "charac_i_recuperation"), getNullableInt(rs, "limit_i_recuperation"),
                        getNullableInt(rs, "charac_i_hill"), getNullableInt(rs, "limit_i_hill"),
                        getNullableInt(rs, "charac_i_baroudeur"), getNullableInt(rs, "limit_i_baroudeur")
                ));
            }

            return out;

        } catch (Exception e) {
            throw new RuntimeException("CyclistDao.findAll failed", e);
        }
    }

    public void updateSkills(int idCyclist,
                             int plain, int mountain, int mediumMountain, int downhilling,
                             int cobble, int timetrial, int prologue, int sprint,
                             int acceleration, int endurance, int resistance, int recuperation,
                             int hill, int baroudeur) {

        String sql = """
        UPDATE DYN_cyclist SET
          charac_i_plain = ?,
          charac_i_mountain = ?,
          charac_i_medium_mountain = ?,
          charac_i_downhilling = ?,
          charac_i_cobble = ?,
          charac_i_timetrial = ?,
          charac_i_prologue = ?,
          charac_i_sprint = ?,
          charac_i_acceleration = ?,
          charac_i_endurance = ?,
          charac_i_resistance = ?,
          charac_i_recuperation = ?,
          charac_i_hill = ?,
          charac_i_baroudeur = ?
        WHERE IDcyclist = ?
        """;

        try (var c = DatabaseManager.getConnection();
             var ps = c.prepareStatement(sql)) {

            int i = 1;
            ps.setInt(i++, plain);
            ps.setInt(i++, mountain);
            ps.setInt(i++, mediumMountain);
            ps.setInt(i++, downhilling);
            ps.setInt(i++, cobble);
            ps.setInt(i++, timetrial);
            ps.setInt(i++, prologue);
            ps.setInt(i++, sprint);
            ps.setInt(i++, acceleration);
            ps.setInt(i++, endurance);
            ps.setInt(i++, resistance);
            ps.setInt(i++, recuperation);
            ps.setInt(i++, hill);
            ps.setInt(i++, baroudeur);
            ps.setInt(i, idCyclist);

            int updated = ps.executeUpdate();
            if (updated != 1) {
                throw new RuntimeException("Update failed: updated rows = " + updated);
            }

        } catch (Exception e) {
            throw new RuntimeException("CyclistDao.updateSkills failed", e);
        }
    }


}