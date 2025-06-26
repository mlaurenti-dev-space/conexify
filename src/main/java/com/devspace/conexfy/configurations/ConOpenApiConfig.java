package com.devspace.conexfy.configurations;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Connection Service DevSpace API ",
        version = "v1",
        description = "API for managing HTTP connections (CRUD and execution)"
    )
)
public class ConOpenApiConfig {

}
