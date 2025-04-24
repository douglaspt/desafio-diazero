package com.dpt.desafiodiazero;

import com.dpt.desafiodiazero.model.Incident;
import com.dpt.desafiodiazero.repository.IncidentRepository;
import com.dpt.desafiodiazero.service.exception.NotFoundException;
import com.dpt.desafiodiazero.service.impl.IncidentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IncidentServiceImplTest {

    @Mock
    private IncidentRepository incidentRepository;

    @InjectMocks
    private IncidentServiceImpl incidentService;

    @Test
    void findById_existingId_returnsIncident() {
        // scenario
        Long incidentId = 1L;
        Incident incident = new Incident();
        incident.setIdIncident(incidentId);
        when(incidentRepository.findById(incidentId)).thenReturn(Optional.of(incident));

        // action
        Incident foundIncident = incidentService.findById(incidentId);

        // verification
        assertNotNull(foundIncident);
        assertEquals(incidentId, foundIncident.getIdIncident());
        verify(incidentRepository, times(1)).findById(incidentId);

    }

    @Test
    void findById_nonExistingId_throwsNotFoundException() {
        // scenario
        Long incidentId = 1L;
        when(incidentRepository.findById(incidentId)).thenReturn(Optional.empty());

        // action and verification
        assertThrows(NotFoundException.class, () -> incidentService.findById(incidentId));
        verify(incidentRepository, times(1)).findById(incidentId);
    }

    @Test
    void findAll_returnsListOfIncidents() {
        // scenario
        Incident incident1 = new Incident();
        Incident incident2 = new Incident();
        List<Incident> incidents = Arrays.asList(incident1, incident2);
        when(incidentRepository.findAll()).thenReturn(incidents);

        // action
        List<Incident> foundIncidents = incidentService.findAll();

        // verification
        assertNotNull(foundIncidents);
        assertEquals(2, foundIncidents.size());
        verify(incidentRepository, times(1)).findAll();
    }

    @Test
    void create_validIncident_returnsSavedIncidentWithCreatedAt() {
        // scenario
        Incident incidentToCreate = new Incident();
        when(incidentRepository.save(any(Incident.class))).thenAnswer(invocation -> {
            Incident saved = invocation.getArgument(0);
            saved.setIdIncident(1L); // Simula o ID sendo gerado
            return saved;
        });

        // action
        Incident createdIncident = incidentService.create(incidentToCreate);

        // verification
        assertNotNull(createdIncident);
        assertNotNull(createdIncident.getIdIncident());
        assertNotNull(createdIncident.getCreatedAt());
        verify(incidentRepository, times(1)).save(any(Incident.class));
    }

}
