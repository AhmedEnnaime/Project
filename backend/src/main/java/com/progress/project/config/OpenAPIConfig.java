package com.progress.project.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

    @Value("${ahmed.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");


        Contact contact = new Contact();
        contact.setEmail("ahmedennaime20@gmail.com");
        contact.setName("Ahmed Ennaime");

        Info info = new Info()
                .title("Participant Management System")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage participants.");

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
