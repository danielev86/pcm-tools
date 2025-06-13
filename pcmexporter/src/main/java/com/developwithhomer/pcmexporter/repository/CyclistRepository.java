package com.developwithhomer.pcmexporter.repository;

import com.developwithhomer.pcmexporter.repository.model.Cyclist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CyclistRepository extends JpaRepository<Cyclist, Long> {

    @Query("SELECT c FROM Cyclist c INNER JOIN FETCH c.team ORDER BY  c.lastName")
    List<Cyclist> findAll();

    @Query("SELECT c FROM Cyclist c INNER JOIN FETCH c.team WHERE c.team.id = :idTeam ORDER BY  c.lastName")
    List<Cyclist> findCyclistByTeam(@Param("idTeam") Long idTeam);

}
