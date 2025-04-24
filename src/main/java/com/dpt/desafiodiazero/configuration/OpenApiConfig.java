package com.dpt.desafiodiazero.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Rest API for incident registration",
                version = "1.0.0",
                description = "Incident Management API Documentation",
                contact = @Contact(
                        name = "Douglas Pimentel Teixeira",
                        email = "douglaspt@gmail.com",
                        url = "https://dpt.com"
                )
        )
)
public class OpenApiConfig {
}
