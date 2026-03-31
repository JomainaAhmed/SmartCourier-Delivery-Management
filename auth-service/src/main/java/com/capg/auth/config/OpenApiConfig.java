package com.capg.auth.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new io.swagger.v3.oas.models.servers.Server().url("http://localhost:8080").description("API Gateway"))
                .addServersItem(new io.swagger.v3.oas.models.servers.Server().url("http://localhost:8081").description("Local Service"))
                .info(new Info()
                        .title("Auth Service API")
                        .version("1.0")
                        .description("Authentication APIs"));
    }
}