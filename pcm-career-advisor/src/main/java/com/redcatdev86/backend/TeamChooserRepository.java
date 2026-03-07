package com.redcatdev86.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeamChooserRepository {

    private static final String SQL = """
            select dt.gene_sz_name,
                   sd.IDdivision,
                   sd.CONSTANT,
                   sc1.IDcountry,
                   sc1.gene_sz_flag,
                   sc2.IDcontinent,
                   sc2.CONSTANT
            from DYN_team dt
            inner join STA_division sd ON dt.fkIDdivision = sd.IDdivision
            inner join STA_country sc1 ON dt.fkIDcountry = sc1.IDcountry
            inner join STA_continent sc2 ON sc1.fkIDcontinent = sc2.IDcontinent
            order by dt.gene_sz_name
            """;

    public List<TeamChooserRecord> findAll() {
        List<TeamChooserRecord> result = new ArrayList<>();

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                TeamChooserRecord record = new TeamChooserRecord(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7)
                );

                result.add(record);
            }

        } catch (Exception e) {
            throw new RuntimeException("Errore lettura TeamChooser: " + e.getMessage(), e);
        }

        return result;
    }
}