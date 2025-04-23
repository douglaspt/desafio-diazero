package com.dpt.desafiodiazero.controller;

import com.dpt.desafiodiazero.dtos.IncidentDto;
import com.dpt.desafiodiazero.model.Incident;
import com.dpt.desafiodiazero.service.IncidentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Incident")
public class IncidentController {

    private final IncidentService service;

    public IncidentController(IncidentService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidentDto> findById(@PathVariable Long id) {
        var incident = service.findById(id);
        return ResponseEntity.ok(new IncidentDto(incident));
    }

    @GetMapping
    public ResponseEntity<List<IncidentDto>> findAll() {
        var incidents = service.findAll();
        var incidentDto = incidents.stream().map(IncidentDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(incidentDto);
    }

    @GetMapping("/findLastTwenty")
    public ResponseEntity<List<IncidentDto>> findLastTwenty() {
        var incidents = service.findLastTwenty();
        var incidentDto = incidents.stream().map(IncidentDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(incidentDto);
    }

    @PostMapping
    public ResponseEntity<IncidentDto> create(@RequestBody IncidentDto incidentDto) {
        var incident = service.create(incidentDto.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(incident.getIdIncident())
                .toUri();
        return ResponseEntity.created(location).body(new IncidentDto(incident));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncidentDto> update(@PathVariable Long id, @RequestBody IncidentDto incidentDto) {
        var user = service.update(id, incidentDto.toModel());
        return ResponseEntity.ok(new IncidentDto(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }





}
