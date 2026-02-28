package com.redcatdev86.backend;

import com.redcatdev86.backend.model.ContractCyclist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ContractCyclistDao {

    public List<ContractCyclist> findAllWithNames() {
        // Refuso corretto: dt2 joina su dt2.IDteam
        String sql = """
            SELECT dcc.IDcontract_cyclist,
                   dc.gene_sz_firstlastname,
                   dt.gene_sz_name  AS actual_team,
                   dt2.gene_sz_name AS prev_team,
                   dcc.finan_i_period_wage,
                   dcc.iYearBegin,
                   dcc.iYearEnd,
                   dcc.gene_b_active_contract,
                   dcc.iRole
            FROM DYN_contract_cyclist dcc
            INNER JOIN DYN_team dt  ON dcc.fkIDteam     = dt.IDteam
            INNER JOIN DYN_team dt2 ON dcc.fkIDprevteam = dt2.IDteam
            INNER JOIN DYN_cyclist dc ON dcc.fkIDcyclist = dc.IDcyclist
            """;

        try (Connection c = DatabaseManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            List<ContractCyclist> out = new ArrayList<>();
            while (rs.next()) {
                ContractCyclist x = new ContractCyclist();
                x.setIdContractCyclist(getIntOrNull(rs, 1));
                x.setCyclistFullName(rs.getString(2));
                x.setActualTeam(rs.getString(3));
                x.setPrevTeam(rs.getString(4));
                x.setPeriodWage(getIntOrNull(rs, 5));
                x.setYearBegin(getIntOrNull(rs, 6));
                x.setYearEnd(getIntOrNull(rs, 7));
                x.setActiveContract(getIntOrNull(rs, 8));
                x.setRole(getIntOrNull(rs, 9));
                out.add(x);
            }
            return out;

        } catch (Exception ex) {
            throw new RuntimeException("ContractCyclistDao.findAllWithNames failed", ex);
        }
    }

    // update SOLO sui 3 campi richiesti
    public void updateCoreFields(int idContractCyclist, Integer wage, Integer yearBegin, Integer yearEnd) {
        String sql = """
            UPDATE DYN_contract_cyclist
            SET finan_i_period_wage = ?,
                iYearBegin = ?,
                iYearEnd = ?
            WHERE IDcontract_cyclist = ?
            """;

        try (Connection c = DatabaseManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            setIntOrNull(ps, 1, wage);
            setIntOrNull(ps, 2, yearBegin);
            setIntOrNull(ps, 3, yearEnd);
            ps.setInt(4, idContractCyclist);

            ps.executeUpdate();

        } catch (Exception ex) {
            throw new RuntimeException("ContractCyclistDao.updateCoreFields failed", ex);
        }
    }

    private static Integer getIntOrNull(ResultSet rs, int idx) throws Exception {
        int v = rs.getInt(idx);
        return rs.wasNull() ? null : v;
    }

    private static void setIntOrNull(PreparedStatement ps, int idx, Integer v) throws Exception {
        if (v == null) ps.setNull(idx, Types.INTEGER);
        else ps.setInt(idx, v);
    }
}