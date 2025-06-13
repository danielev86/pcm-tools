package com.developwithhomer.pcmexporter.repository;

import com.developwithhomer.pcmexporter.repository.model.GAMCareerData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GAMCareerDataRepository extends JpaRepository<GAMCareerData, Long> {

    public List<GAMCareerData> findGAMCareerDataByParameterCodeIn(List<String> parameterCodes);

}
