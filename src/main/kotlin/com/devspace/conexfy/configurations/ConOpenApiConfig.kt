package com.devspace.conexfy.configurations

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
@OpenAPIDefinition(
    info = Info(
        title = "Connection Service DevSpace API ",
        version = "v1",
        description = "API for managing HTTP connections (CRUD and execution)"
    )
)
class ConOpenApiConfig
