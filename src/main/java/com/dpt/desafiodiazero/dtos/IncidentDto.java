package com.dpt.desafiodiazero.dtos;

import com.dpt.desafiodiazero.model.Incident;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record IncidentDto(
        Long idIncident,
        @NotBlank(message = "value cannot be null or empty")
        @Size(min = 6, message = "minimum value equal to 6 characters")
        String name,
        @NotBlank(message = "value cannot be null or empty")
        @Size(min = 10, message = "minimum value equal to 10 characters")
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
