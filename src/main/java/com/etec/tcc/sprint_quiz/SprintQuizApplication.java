package com.etec.tcc.sprint_quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SprintQuizApplication {

//	    @Bean
//    public CommandLineRunner commandLineRunner(@Autowired ProvaRepository provaRepository) {
//        return args -> {
//			Prova prova = new Prova();
//			provaRepository.save(prova);
//        };
//
//    }

	public static void main(String[] args) {
		SpringApplication.run(SprintQuizApplication.class, args);
	}

}
