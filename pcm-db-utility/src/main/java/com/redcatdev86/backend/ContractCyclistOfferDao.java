package com.redcatdev86.backend;

import com.redcatdev86.backend.model.ContractCyclistOffer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ContractCyclistOfferDao extends CommonDao{

    public List<ContractCyclistOffer> findAllWithNames() {

        String sql = """
            SELECT dcc.IDcontract_offer,
                   dc.gene_sz_firstlastname,
                   dt.gene_sz_name as actual_team,
                   dcc.finan_i_period_wage,
                   dcc.gene_i_num_years,
                   dcc.gene_b_final,
                   dcc.gene_i_date_resolve,
                   dcc.iPatienceTries,
                   dcc.iRole
            FROM DYN_contract_cyclist_offer dcc
            INNER JOIN DYN_team dt ON dcc.fkIDteam = dt.IDteam
            INNER JOIN DYN_cyclist dc ON dcc.fkIDcyclist = dc.IDcyclist
            """;

        try (Connection c = DatabaseManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            List<ContractCyclistOffer> out = new ArrayList<>();
            while (rs.next()) {
                ContractCyclistOffer x = new ContractCyclistOffer();
                x.setIdContractOffer(getIntOrNull(rs, 1));
                x.setCyclistFullName(rs.getString(2));
                x.setActualTeam(rs.getString(3));
                x.setPeriodWage(getIntOrNull(rs, 4));
                x.setNumYears(getIntOrNull(rs, 5));
                x.setFinalFlag(getIntOrNull(rs, 6));
                x.setDateResolve(getIntOrNull(rs, 7));
                x.setPatienceTries(getIntOrNull(rs, 8));
                x.setRole(getIntOrNull(rs, 9));
                out.add(x);
            }
            return out;

        } catch (Exception ex) {
            throw new RuntimeException("ContractCyclistOfferDao.findAllWithNames failed", ex);
        }
    }

    // ✅ update SOLO sui campi editabili richiesti
    public void updateEditableFields(int idContractOffer,
                                     Integer periodWage,
                                     Integer numYears,
                                     Integer finalFlag,
                                     Integer dateResolve,
                                     Integer patienceTries,
                                     Integer role) {

        String sql = """
            UPDATE DYN_contract_cyclist_offer
            SET finan_i_period_wage = ?,
                gene_i_num_years = ?,
                gene_b_final = ?,
                gene_i_date_resolve = ?,
                iPatienceTries = ?,
                iRole = ?
            WHERE IDcontract_offer = ?
            """;

        try (Connection c = DatabaseManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            setIntOrNull(ps, 1, periodWage);
            setIntOrNull(ps, 2, numYears);
            setIntOrNull(ps, 3, finalFlag);
            setIntOrNull(ps, 4, dateResolve);
            setIntOrNull(ps, 5, patienceTries);
            setIntOrNull(ps, 6, role);
            ps.setInt(7, idContractOffer);

            ps.executeUpdate();

        } catch (Exception ex) {
            throw new RuntimeException("ContractCyclistOfferDao.updateEditableFields failed", ex);
        }
    }

}