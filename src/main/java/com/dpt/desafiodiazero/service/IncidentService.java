package com.dpt.desafiodiazero.service;

import com.dpt.desafiodiazero.model.Incident;

import java.util.List;

public interface IncidentService {

    Incident findById(Long id);

    List<Incident> findAll();

    List<Incident> findLastTwenty();

    Incident create(Incident incident);

    Incident update(Long id, Incident incident);

    void delete(Long id);

}
