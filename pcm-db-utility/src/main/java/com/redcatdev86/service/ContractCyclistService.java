package com.redcatdev86.service;

import com.redcatdev86.backend.ContractCyclistDao;
import com.redcatdev86.backend.model.ContractCyclist;
import com.redcatdev86.ui.model.ContractCyclistBean;

import java.util.List;

public class ContractCyclistService {

    private final ContractCyclistDao dao = new ContractCyclistDao();

    public List<ContractCyclistBean> getAll() {
        return dao.findAllWithNames().stream().map(this::toBean).toList();
    }

    public void updateCoreFields(ContractCyclistBean b) {
        if (b.getIdContractCyclist() == null) {
            throw new IllegalArgumentException("idContractCyclist is null");
        }
        dao.updateCoreFields(
                b.getIdContractCyclist(),
                b.getPeriodWage(),
                b.getYearBegin(),
                b.getYearEnd()
        );
    }

    private ContractCyclistBean toBean(ContractCyclist x) {
        ContractCyclistBean b = new ContractCyclistBean();
        b.setIdContractCyclist(x.getIdContractCyclist());
        b.setCyclistFullName(x.getCyclistFullName());
        b.setActualTeam(x.getActualTeam());
        b.setPrevTeam(x.getPrevTeam());
        b.setPeriodWage(x.getPeriodWage());
        b.setYearBegin(x.getYearBegin());
        b.setYearEnd(x.getYearEnd());
        b.setActiveContract(x.getActiveContract());
        b.setRole(x.getRole());
        return b;
    }
}