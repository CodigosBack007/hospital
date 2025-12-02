package com.senai.backend.Hospital.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = " Hospital Com Vida",
        version = "1.0",
        description = "email para contato: joaogabrielfaria080b@gmail.com"
    )
)

public class Swagger {
    
}
