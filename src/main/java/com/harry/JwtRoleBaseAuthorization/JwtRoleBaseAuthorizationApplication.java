package com.harry.JwtRoleBaseAuthorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(value = "com.harry.JwtRoleBaseAuthorization.repository")
@EntityScan(value = "com.harry.JwtRoleBaseAuthorization.model")
public class JwtRoleBaseAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtRoleBaseAuthorizationApplication.class, args);
	}

}
