package com.etec.tcc.sprint_quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
//		System.out.println(new BCryptPasswordEncoder().encode("12345678")); //gerar senhas para salvar no banco usuários iniciais
	}

}
