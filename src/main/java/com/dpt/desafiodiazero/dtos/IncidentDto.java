package com.dpt.desafiodiazero.dtos;

import com.dpt.desafiodiazero.model.Incident;

import java.time.LocalDateTime;

public record IncidentDto(
        Long idIncident,
        String name,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime closedAt
) {

    public IncidentDto(Incident model) {
        this(
                model.getIdIncident(),
                model.getName(),
                model.getDescription(),
                model.getCreatedAt(),
                model.getUpdatedAt(),
                model.getClosedAt()
        );
    }

    public Incident toModel() {
        Incident model = new Incident();
        model.setIdIncident(this.idIncident);
        model.setName(this.name);
        model.setDescription(this.description);
        model.setCreatedAt(this.createdAt);
        model.setUpdatedAt(this.updatedAt);
        model.setClosedAt(this.closedAt);
        return model;
    }
}
