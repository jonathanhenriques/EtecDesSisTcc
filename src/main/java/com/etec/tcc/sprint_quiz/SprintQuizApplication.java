package com.etec.tcc.sprint_quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SprintQuizApplication {

	

	public static void main(String[] args) {
		SpringApplication.run(SprintQuizApplication.class, args);
//		System.out.println(new BCryptPasswordEncoder().encode("12345678")); //gerar senhas para salvar no banco usuários iniciais
	}

}
