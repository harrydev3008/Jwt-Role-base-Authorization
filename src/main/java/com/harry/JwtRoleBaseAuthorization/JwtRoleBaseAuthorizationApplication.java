package com.harry.JwtRoleBaseAuthorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableJpaRepositories(value = "com.harry.JwtRoleBaseAuthorization.repository")
@EntityScan(value = "com.harry.JwtRoleBaseAuthorization.model")
@OpenAPIDefinition(info = @Info(title = "Basic JWT Role-base authentication/authorization api docs", version = "1.0.0", description = "This project is for learning only!", contact = @Contact(name = "Harry Nguyen", email = "chihieunguyen9a2@gmail.com")))
public class JwtRoleBaseAuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtRoleBaseAuthorizationApplication.class, args);
    }

}
