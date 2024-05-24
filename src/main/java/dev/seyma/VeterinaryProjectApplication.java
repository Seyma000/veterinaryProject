package dev.seyma;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Veterinary Management System", version = "1.0", description = "V"))
public class VeterinaryProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(VeterinaryProjectApplication.class, args);
	}

}
