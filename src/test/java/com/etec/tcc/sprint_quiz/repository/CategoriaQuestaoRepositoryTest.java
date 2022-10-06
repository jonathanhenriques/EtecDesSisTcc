package com.etec.tcc.sprint_quiz.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.etec.tcc.sprint_quiz.model.CategoriaQuestao;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CategoriaQuestaoRepositoryTest {

	private static final String DESCRICAO = "Questões de nível fácil";
	private static final String TITULO = "Fácil";
	
	@Autowired
	private CategoriaQuestaoRepository repository;

	@BeforeAll
	void start() throws Exception {
		repository.save(new CategoriaQuestao(0L, TITULO, DESCRICAO, null));
	}

	@Test
	void testFindAllByTituloContainingIgnoreCase() {
		List<CategoriaQuestao> lista = repository.findAllByTituloContainingIgnoreCase(TITULO);

		assertEquals(TITULO, lista.get(0).getTitulo());
	}

//	@Test
//	void testFindByDescricaoContainingIgnoreCase() {
//		List<CategoriaQuestao> lista = repository.findByAllDescricaoContainingIgnoreCase(DESCRICAO);
//
//		assertEquals(DESCRICAO, lista.get(0).getDescricao());
//	}

}
