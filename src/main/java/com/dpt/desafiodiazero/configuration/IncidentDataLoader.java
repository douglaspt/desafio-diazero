package com.dpt.desafiodiazero.configuration;

import com.dpt.desafiodiazero.model.Incident;
import com.dpt.desafiodiazero.repository.IncidentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.IntStream;

@Configuration
public class IncidentDataLoader {

    @Bean
    CommandLineRunner loadIncidentData(IncidentRepository incidentRepository) {
        return args -> {
            Random random = new Random();

            IntStream.range(0, 25).forEach(i -> {
                String name = "Incident " + (i + 1);
                String description = "Incident Description " + (i + 1);

                LocalDateTime now = LocalDateTime.now();
                LocalDateTime createdAt = now.minusDays(random.nextInt(30));

                Incident incident = new Incident();
                incident.setName(name);
                incident.setDescription(description);
                incident.setCreatedAt(createdAt);

                incidentRepository.save(incident);
            });

            System.out.println("25 Incidents successfully entered!");
        };
    }
}
