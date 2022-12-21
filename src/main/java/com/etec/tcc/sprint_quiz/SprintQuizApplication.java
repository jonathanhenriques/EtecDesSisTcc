package com.etec.tcc.sprint_quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityScheme;


@SpringBootApplication
public class SprintQuizApplication {



	public static void main(String[] args) {
		SpringApplication.run(SprintQuizApplication.class, args);
//		System.out.println(new BCryptPasswordEncoder().encode("12345678")); //gerar senhas para salvar no banco usuários iniciais
	}

}
