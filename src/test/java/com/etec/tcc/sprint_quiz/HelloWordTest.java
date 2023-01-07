package com.etec.tcc.sprint_quiz;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.etec.tcc.sprint_quiz.model.CategoriaProva;
import com.etec.tcc.sprint_quiz.repository.CategoriaProvaRepository;

public class HelloWordTest {
	
	@Test
	void hello() {
		CategoriaProvaRepository mock =  Mockito.mock(CategoriaProvaRepository.class );
		List<CategoriaProva> todos = mock.findAll();
		
		assertTrue(todos.isEmpty());
	}

}
