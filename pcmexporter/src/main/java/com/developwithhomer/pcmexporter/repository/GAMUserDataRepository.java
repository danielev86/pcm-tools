package com.developwithhomer.pcmexporter.repository;

import com.developwithhomer.pcmexporter.repository.model.GAMUserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GAMUserDataRepository extends JpaRepository<GAMUserData, Long> {

    GAMUserData findByUserLogin(String userLogin);

}
