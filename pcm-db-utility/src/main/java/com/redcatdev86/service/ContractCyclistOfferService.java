package com.redcatdev86.service;

import com.redcatdev86.backend.ContractCyclistOfferDao;
import com.redcatdev86.backend.model.ContractCyclistOffer;
import com.redcatdev86.ui.model.ContractCyclistOfferBean;

import java.util.List;

public class ContractCyclistOfferService {

    private final ContractCyclistOfferDao dao = new ContractCyclistOfferDao();

    public List<ContractCyclistOfferBean> getAll() {
        return dao.findAllWithNames().stream().map(this::toBean).toList();
    }

    public void updateEditableFields(ContractCyclistOfferBean b) {
        if (b.getIdContractOffer() == null) {
            throw new IllegalArgumentException("idContractOffer is null");
        }

        dao.updateEditableFields(
                b.getIdContractOffer(),
                b.getPeriodWage(),
                b.getNumYears(),
                b.getFinalFlag(),
                b.getDateResolve(),
                b.getPatienceTries(),
                b.getRole()
        );
    }

    private ContractCyclistOfferBean toBean(ContractCyclistOffer x) {
        ContractCyclistOfferBean b = new ContractCyclistOfferBean();
        b.setIdContractOffer(x.getIdContractOffer());
        b.setCyclistFullName(x.getCyclistFullName());
        b.setActualTeam(x.getActualTeam());
        b.setPeriodWage(x.getPeriodWage());
        b.setNumYears(x.getNumYears());
        b.setFinalFlag(x.getFinalFlag());
        b.setDateResolve(x.getDateResolve());
        b.setPatienceTries(x.getPatienceTries());
        b.setRole(x.getRole());
        return b;
    }
}