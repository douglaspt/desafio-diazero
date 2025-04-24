package com.dpt.desafiodiazero.controller;

import com.dpt.desafiodiazero.dtos.IncidentDto;
import com.dpt.desafiodiazero.model.Incident;
import com.dpt.desafiodiazero.service.IncidentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/incident")
public class IncidentController {

    private final IncidentService service;

    public IncidentController(IncidentService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a Incident by ID", description = "Retrieve a specific incident based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "400", description = "Invalid or malformed request."),
            @ApiResponse(responseCode = "404", description = "Incident not found"),
    })
    public ResponseEntity<IncidentDto> findById(@PathVariable Long id) {
        var incident = service.findById(id);
        return ResponseEntity.ok(new IncidentDto(incident));
    }

    @GetMapping
    @Operation(summary = "Get all Incidents", description = "Retrieve a list of all registered Incidents")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    public ResponseEntity<List<IncidentDto>> findAll() {
        var incidents = service.findAll();
        var incidentDto = incidents.stream().map(IncidentDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(incidentDto);
    }

    @GetMapping("/findLastTwenty")
    @Operation(summary = "Get the last twenty incidents", description = "Retrieve a list of the last twenty incidents")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    public ResponseEntity<List<IncidentDto>> findLastTwenty() {
        var incidents = service.findLastTwenty();
        var incidentDto = incidents.stream().map(IncidentDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(incidentDto);
    }

    @PostMapping
    @Operation(summary = "Create a new Incident", description = "Create a new incident and return the created incident data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Incident created successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid incident data provided")
    })
    public ResponseEntity<IncidentDto> create(@Valid @RequestBody IncidentDto incidentDto) {
        var incident = service.create(incidentDto.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(incident.getIdIncident())
                .toUri();
        return ResponseEntity.created(location).body(new IncidentDto(incident));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Incident", description = "Update the data of an existing incident based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incident updated successfully"),
            @ApiResponse(responseCode = "404", description = "Incident not found"),
            @ApiResponse(responseCode = "422", description = "Invalid incident data provided")
    })
    public ResponseEntity<IncidentDto> update(@PathVariable Long id, @Valid @RequestBody IncidentDto incidentDto) {
        var user = service.update(id, incidentDto.toModel());
        return ResponseEntity.ok(new IncidentDto(user));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Incident", description = "Delete an existing incident based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Incident deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Incident not found")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }





}
