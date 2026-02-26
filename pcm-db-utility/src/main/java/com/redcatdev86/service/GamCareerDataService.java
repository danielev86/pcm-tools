package com.redcatdev86.service;

import com.redcatdev86.backend.GamCareerDataDao;
import com.redcatdev86.ui.model.GamCareerDataBean;

import java.util.List;

public class GamCareerDataService {

    private final GamCareerDataDao dao = new GamCareerDataDao();

    public List<GamCareerDataBean> loadAllBeans() throws Exception {
        return dao.findAll().stream().map(GamCareerDataBean::fromModel).toList();
    }

    public void saveAllDirty(List<GamCareerDataBean> beans) throws Exception {
        var dirtyModels = beans.stream()
                .filter(GamCareerDataBean::isDirty)
                .map(GamCareerDataBean::toModel)
                .toList();

        if (dirtyModels.isEmpty()) return;

        dao.updateValuesBatch(dirtyModels);

        // segna clean in memoria (dopo commit)
        for (GamCareerDataBean b : beans) {
            if (b.isDirty()) b.markClean();
        }
    }

}
