package com.etec.tcc.sprint_quiz.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;
import com.etec.tcc.sprint_quiz.service.AlternativaService;

class AlternativaControllerTest {

	@Autowired
	private AlternativaRepository alternativaRepository;

	@Autowired
	private AlternativaService alternativaService;

	@Test
	void deveriaRetornarUmaAlternativaBuscadaPorId() {
		fail("Not yet implemented");
	}
	
	
	@Test
	void deveriaListarTodasAsAlternativas() {
		fail("Not yet implemented");
	}

}
