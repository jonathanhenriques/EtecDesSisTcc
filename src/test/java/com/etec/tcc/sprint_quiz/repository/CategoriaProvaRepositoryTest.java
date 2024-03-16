package com.etec.tcc.sprint_quiz.repository;



import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.etec.tcc.sprint_quiz.model.CategoriaProva;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CategoriaProvaRepositoryTest {

	@Autowired
	private CategoriaProvaRepository repository;
	
	
	@BeforeAll
	void setUp() throws Exception {
//		repository.deleteAll();
		
//		Prova prova = new Prova(0L, "nome teste", "descricao teste", 2, null, null, "instituicao teste", null);
//		List<Prova> lista = new ArrayList<>();
//		lista.add(prova);
		CategoriaProva categoriaTeste = new CategoriaProva(0L, "titulo teste", "descricao teste", null);
//		lista.get(0).setCategoria(categoriaTeste);
		repository.save(categoriaTeste);
	}
	
//	@Test
//	@DisplayName("Retorna 1 categoriaProva")
//	 void getByIdDeveriaRetornarUmaCategoriaprova() {
//		List<CategoriaProva> lista = repository.findAllByTituloContainingIgnoreCase("titulo teste");
//
//		assertEquals(1, lista.size());
//		assertEquals("titulo teste", lista.get(0).getTitulo());
//	}


	
     
	
	
	@AfterAll
	public void end() {
//		repository.deleteAll();
	}

}
