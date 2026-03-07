package com.redcatdev86.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeamBuilderRepository {

    private static final String SQL = """
            select ds.gene_sz_name,
                   sr.IDregion,
                   sr.CONSTANT,
                   sc.IDcountry,
                   sc.CONSTANT,
                   sc.gene_sz_flag,
                   sc2.IDcontinent,
                   sc2.CONSTANT
            from DYN_sponsor ds
            inner join STA_region sr on ds.fkIDregion = sr.IDregion
            inner join STA_country sc on sr.fkIDcountry = sc.IDcountry
            inner join STA_continent sc2 on sc.fkIDcontinent = sc2.IDcontinent
            order by ds.gene_sz_name
            """;

    public List<TeamBuilderRecord> findAll() {
        List<TeamBuilderRecord> result = new ArrayList<>();

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                TeamBuilderRecord record = new TeamBuilderRecord(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8)
                );

                result.add(record);
            }

        } catch (Exception e) {
            throw new RuntimeException("Errore lettura TeamBuilder: " + e.getMessage(), e);
        }

        return result;
    }
}