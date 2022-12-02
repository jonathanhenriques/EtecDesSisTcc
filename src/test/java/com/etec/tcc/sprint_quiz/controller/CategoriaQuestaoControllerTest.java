package com.etec.tcc.sprint_quiz.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.etec.tcc.sprint_quiz.model.CategoriaQuestao;
import com.etec.tcc.sprint_quiz.repository.CategoriaQuestaoRepository;
import com.etec.tcc.sprint_quiz.service.CategoriaQuestaoServiceImp;

class CategoriaQuestaoControllerTest {

	@InjectMocks
	private CategoriaQuestaoController controller;

	@Mock
	private CategoriaQuestaoServiceImp service;

	@Mock
	private CategoriaQuestaoRepository repository;

	private static final Long ID = 1L;
	private static final String TITULO = "titulo";
	private static final String DESCRICAO = "descricao";
	private static final int INDEX = 0;
	private static final String CATEGORIA_DA_QUESTAO_NAO_ENCONTRADA = "Categoria da Questão não encontrada!";

	private CategoriaQuestao categoria;
	private Optional optionalCategoria;

//	private TestRestTemplate testeRestTemplate;

	@BeforeEach
	void setUp() throws Exception {
//		MockitoAnnotations.openMocks(this); ////corrigir depois da mudança para o java 8 erro
		startCategoriaQuestao();
	}

	@Test
	void testGetByIdDeveriaRetornarUmaCaegoriaQuestao() {
		Mockito.when(service.getById(Mockito.anyLong())).thenReturn(categoria);

		ResponseEntity<CategoriaQuestao> response = controller.getById(ID);

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(CategoriaQuestao.class, response.getBody().getClass());

		assertEquals(ID, response.getBody().getId());
		assertEquals(TITULO, response.getBody().getTitulo());
		assertEquals(DESCRICAO, response.getBody().getDescricao());
		assertEquals(null, response.getBody().getQuestoes());

	}

	@Test
	void testGetAllByDescricao() {

//		List<CategoriaQuestao> obj = new ArrayList<CategoriaQuestao>() {
//			{
//				add(new CategoriaQuestao());
//			}
//		};
//		
//		List<String> myList = new ArrayList<String>() {
//			{
//				add("AAA");
//				add("BBB");
//			}
//
//		};

//		List lista = Arrays.asList(categoria)
		List<CategoriaQuestao> lista = new ArrayList<>();
		lista.add(categoria);
		Mockito.when(service.getAllByDescricao(Mockito.anyString())).thenReturn(lista);

		ResponseEntity<List<CategoriaQuestao>> response = controller.getAllByDescricao(DESCRICAO);

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ArrayList.class, response.getBody().getClass());
		assertEquals(CategoriaQuestao.class, response.getBody().get(INDEX).getClass());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(DESCRICAO, response.getBody().get(INDEX).getDescricao());

		assertEquals(ID, response.getBody().get(INDEX).getId());
		assertEquals(TITULO, response.getBody().get(INDEX).getTitulo());
		assertEquals(DESCRICAO, response.getBody().get(INDEX).getDescricao());
		assertEquals(null, response.getBody().get(INDEX).getQuestoes());
	}

	@Test
	void testGetAllDeveriaRetornarUmaListaDeCategoriaQuestao() {
		List<CategoriaQuestao> lista = new ArrayList<>();
		lista.add(categoria);
		Mockito.when(service.getAll()).thenReturn(lista);

		ResponseEntity<List<CategoriaQuestao>> response = controller.getAll();

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ArrayList.class, response.getBody().getClass());
		assertEquals(CategoriaQuestao.class, response.getBody().get(INDEX).getClass());
		assertEquals(HttpStatus.OK, response.getStatusCode());

		assertEquals(1, response.getBody().size());
		assertEquals(ID, response.getBody().get(INDEX).getId());
		assertEquals(TITULO, response.getBody().get(INDEX).getTitulo());
		assertEquals(DESCRICAO, response.getBody().get(INDEX).getDescricao());
		assertEquals(null, response.getBody().get(INDEX).getQuestoes());
	}

	@Test
	void testPostCategoriaQuestaoDeveriaRetornarUmaCategoriaQuestao() {
		Mockito.when(service.post(Mockito.any())).thenReturn(categoria);
		ResponseEntity<CategoriaQuestao> response = controller.postCategoriaQuestao(categoria);

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(CategoriaQuestao.class, response.getBody().getClass());

		// verificações de atributos
		assertEquals(ID, response.getBody().getId()); // verificando se o atributo id é o que esperados
		assertEquals(TITULO, response.getBody().getTitulo()); // verificando se o atributo titulo é o que
		assertEquals(DESCRICAO, response.getBody().getDescricao()); // verificando se o atributo descricao é
		assertEquals(null, response.getBody().getQuestoes()); // verificando se o atributo provas é o que esperados
	}

	@Test
	void testPutCategoriaQuestaoDeveriaRetornarUmaCategoriaQuestaoAtualizada() {
		Mockito.when(service.put(Mockito.any())).thenReturn(categoria);

		ResponseEntity<CategoriaQuestao> response = controller.putCategoriaQuestao(categoria);

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(CategoriaQuestao.class, response.getBody().getClass());

		// verificações de atributos
		assertEquals(ID, response.getBody().getId()); // verificando se o atributo id é o que esperados
		assertEquals(TITULO, response.getBody().getTitulo()); // verificando se o atributo titulo é o que
		assertEquals(DESCRICAO, response.getBody().getDescricao()); // verificando se o atributo descricao é
		assertEquals(null, response.getBody().getQuestoes()); // verificando se o atributo provas é o que esperados

	}

//	@Test
//	void testPatchCategoriaQuestaoTitulo() {
//		fail("Not yet implemented");
//	}

	@Test
	void testDeleteCategoriaQuestao() {
		Mockito.doNothing().when(service).delete(Mockito.anyLong());
		ResponseEntity<?> response =  controller.deleteCategoriaQuestao(ID);
		
		assertNotNull(response);
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		Mockito.verify(service, Mockito.times(1)).delete(Mockito.anyLong());
	}

	private void startCategoriaQuestao() {
		categoria = new CategoriaQuestao(ID, TITULO, DESCRICAO, null);
		optionalCategoria = Optional.of(new CategoriaQuestao(ID, TITULO, DESCRICAO, null));
	}

}
