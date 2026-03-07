package com.redcatdev86.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CyclistRepository {

    private static final String SQL = """
            select dc.gene_sz_firstname,
                   dc.gene_sz_lastname,
                   sr.CONSTANT,
                   sc.gene_sz_flag,
                   sc2.CONSTANT
            from DYN_cyclist dc
            inner join STA_region sr on dc.fkIDregion = sr.IDregion
            inner join STA_country sc on sr.fkIDcountry = sc.IDcountry
            inner join STA_continent sc2 on sc.fkIDcontinent = sc2.IDcontinent
            order by dc.gene_sz_lastname, dc.gene_sz_firstname
            """;

    public List<CyclistRecord> findAll() {
        List<CyclistRecord> result = new ArrayList<>();

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                result.add(new CyclistRecord(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException("Errore lettura Cyclist: " + e.getMessage(), e);
        }

        return result;
    }
}