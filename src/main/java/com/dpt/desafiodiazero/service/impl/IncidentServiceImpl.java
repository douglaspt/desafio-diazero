package com.dpt.desafiodiazero.service.impl;

import com.dpt.desafiodiazero.model.Incident;
import com.dpt.desafiodiazero.repository.IncidentRepository;
import com.dpt.desafiodiazero.service.IncidentService;
import com.dpt.desafiodiazero.service.exception.BadRequestException;
import com.dpt.desafiodiazero.service.exception.BusinessException;
import com.dpt.desafiodiazero.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        if (incident == null) {
            throw new BusinessException("Incident to create must not be null.");
        }
        if (incident.getIdIncident() != null) {
            throw new BadRequestException("ID must be null when creating a new incident.");
        }
        if (incident.getCreatedAt() == null){
            incident.setCreatedAt(LocalDateTime.now());
        }
        return this.repository.save(incident);
    }

    @Override
    public Incident update(Long id, Incident incident) {
        Incident dbIncident = this.findById(id);
        if (!dbIncident.getIdIncident().equals(incident.getIdIncident())) {
            throw new BusinessException("Update IDs must be the same.");
        }
        return this.repository.save(incident);
    }

    @Override
    public void delete(Long id) {
        Incident dbIncident = this.findById(id);
        this.repository.delete(dbIncident);
    }
}
