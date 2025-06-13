package com.developwithhomer.pcmexporter.repository;

import com.developwithhomer.pcmexporter.repository.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
