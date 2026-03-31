package com.capg.tracking.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        ))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .addServersItem(new io.swagger.v3.oas.models.servers.Server().url("http://localhost:8080").description("API Gateway"))
                .addServersItem(new io.swagger.v3.oas.models.servers.Server().url("http://localhost:8083").description("Local Service"))
                .info(new Info()
                        .title("Tracking Service API")
                        .version("1.0")
                        .description("Tracking APIs"));
    }
}