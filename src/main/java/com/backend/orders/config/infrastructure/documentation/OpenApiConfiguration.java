package com.backend.orders.config.infrastructure.documentation;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Order Management System")
                        .description("API for order management in an e-commerce system.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Joseph Llacchua")
                                .email("josephllacchua123@gmail.com"))
                        .license(new License().name("Apache 2.0")
                                .url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("External API Documentation")
                        .url("https://github.com/JosephLlacchua/keola-backend-orders.git"));
    }
}