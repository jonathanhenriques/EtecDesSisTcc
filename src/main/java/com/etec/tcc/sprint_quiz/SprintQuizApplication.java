package com.etec.tcc.sprint_quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.etec.tcc.sprint_quiz.model.Usuario;
import com.etec.tcc.sprint_quiz.repository.ProvaRepository;
import com.etec.tcc.sprint_quiz.security.JwtService;

@SpringBootApplication
public class SprintQuizApplication {

//	@Bean
//	public CommandLineRunner commandLineRunner(@Autowired ProvaRepository provaRepository) {
//		return args -> {
//			JwtService jwt = new JwtService();
//			Usuario usuario = Usuario.builder().email("fulano@gmail.com").build();
//			String token = jwt.gerarToken(usuario);
//			System.out.println(token);
//			
//			boolean isTokenValido = jwt.tokenValido(token);
//			System.out.println("O token está válido?: " + isTokenValido);
//			
//			System.out.println(jwt.obterLoginUsuario(token));
//		};
//
//	}

	public static void main(String[] args) {
		SpringApplication.run(SprintQuizApplication.class, args);
	}

}
