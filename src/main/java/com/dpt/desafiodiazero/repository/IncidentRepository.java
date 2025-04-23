package com.dpt.desafiodiazero.repository;

import com.dpt.desafiodiazero.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, Long> {

    List<Incident> findTop20ByOrderByCreatedAtDesc();
}
