package com.etec.tcc.sprint_quiz;

import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.model.Usuario;
import com.etec.tcc.sprint_quiz.repository.ProvaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
