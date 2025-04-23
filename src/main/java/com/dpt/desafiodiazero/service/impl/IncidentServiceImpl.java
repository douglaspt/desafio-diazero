package com.dpt.desafiodiazero.service.impl;

import com.dpt.desafiodiazero.model.Incident;
import com.dpt.desafiodiazero.repository.IncidentRepository;
import com.dpt.desafiodiazero.service.IncidentService;
import com.dpt.desafiodiazero.service.exception.BusinessException;
import com.dpt.desafiodiazero.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class IncidentServiceImpl implements IncidentService {

    private final IncidentRepository repository;

    public IncidentServiceImpl(IncidentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Incident findById(Long id) {
        return this.repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Incident> findAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Incident> findLastTwenty() {
        return this.repository.findTop20ByOrderByCreatedAtDesc();
    }

    @Override
    public Incident create(Incident incident) {
        ofNullable(incident).orElseThrow(() -> new BusinessException("Incident to create must not be null."));
        return this.repository.save(incident);
    }

    @Override
    public Incident update(Long id, Incident incident) {
        Incident dbIncident = this.findById(id);
        return this.repository.save(incident);
    }

    @Override
    public void delete(Long id) {
        Incident dbIncident = this.findById(id);
        this.repository.delete(dbIncident);
    }
}
