package com.example.user_game_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Service : Gestion utilisateurs",
                version = "1.0",
                description = "Documentation de l'API du service de gestion de mes utilisateurs"
        )
)
public class UserGameApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserGameApiApplication.class, args);
	}

}
