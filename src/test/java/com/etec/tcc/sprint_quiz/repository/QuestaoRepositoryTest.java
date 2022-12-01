package com.etec.tcc.sprint_quiz.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.etec.tcc.sprint_quiz.model.DificuldadeQuestao;
import com.etec.tcc.sprint_quiz.model.Questao;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QuestaoRepositoryTest {
	
	private static final String INSTITUICAO = "isntituicao";
	private static final int INDEX = 0;
	private static final String TEXTO = "Qual a cor da neve?";
	@Autowired
	private QuestaoRepository repository;

	@BeforeAll
	 void start() throws Exception {
		repository.save(new Questao(1L,INSTITUICAO,LocalDate.now(),"https://i.imgur.com/r98IEMu.png",TEXTO,DificuldadeQuestao.FACIL, null,null,null,null));
		
	}

	@Test
	@DisplayName("retorna lista filtrada por texto")
	void testFindAllByTextoContainingIgnoreCase() {
		List<Questao> lista = new ArrayList<>();
		repository.findAllByTextoContainingIgnoreCase(TEXTO);
		
		assertEquals(TEXTO, lista.get(INDEX).getTexto());
	}

	@Test
	@DisplayName("retorna lista filtrada por instituicao")
	void testFindAllByInstituicaoContainingIgnoreCase() {
		List<Questao> lista = new ArrayList<>();
		repository.findAllByInstituicaoContainingIgnoreCase(INSTITUICAO);
		
		assertEquals(INSTITUICAO,lista.get(INDEX).getInstituicao());
	}
//
//	@Test
//	void testFindAllByAno() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindAllByAnoBetween() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindAllByAnoBefore() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindAllByCriadorId() {
//		fail("Not yet implemented");
//	}

}
