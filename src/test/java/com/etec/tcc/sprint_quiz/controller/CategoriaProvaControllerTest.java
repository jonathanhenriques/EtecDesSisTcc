package com.etec.tcc.sprint_quiz.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.etec.tcc.sprint_quiz.model.CategoriaProva;
import com.etec.tcc.sprint_quiz.repository.CategoriaProvaRepository;
import com.etec.tcc.sprint_quiz.service.CategoriaProvaServiceImp;

@SpringBootTest
class CategoriaProvaControllerTest {

	@InjectMocks // injeta uma instacia real da classe
	private CategoriaProvaController controller;

	@Mock
	private CategoriaProvaRepository repository;

	@Mock
	private CategoriaProvaServiceImp service;

	private CategoriaProva categoriaProva;

	private Optional<CategoriaProva> optionalCategoriaProva;

	private static final int INDEX = 0;
	private static final String TITULO = "teste";
	private static final String DESCRICAO = "teste";
	private static final String CATEGORIA_DE_PROVA_NAO_ENCONTRADA = "Categoria de Prova não encontrada!";
	private static final long ID = 1L;

	@BeforeEach
	void setUp() throws Exception {
//		MockitoAnnotations.openMocks(this); // inicializa as anotações dessa classe //corrigir depois da mudança para o java 8 erro
		startCategoriaProva(); // cria as instancias de categoria que são usadas nos testes
	}

	@Test
	void testGetByIdDeveriaRetornarUmaCategoriaProva() {
		Mockito.when(service.getById(Mockito.anyLong())).thenReturn(categoriaProva); // mockando resposta para chamada
																						// do service

		ResponseEntity<CategoriaProva> response = controller.getById(ID); // efetuando a chamada

		// verificações de tipos
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(CategoriaProva.class, response.getBody().getClass());
		// verificações de atributos
		assertEquals(ID, response.getBody().getId());
		assertEquals(TITULO, response.getBody().getTitulo());
		assertEquals(DESCRICAO, response.getBody().getDescricao());
		assertEquals(null, response.getBody().getProvas());

	}

	@Test
	void testGetAllDeveriaRetornarUmaListaDeCategoriaProva() {
		List<CategoriaProva> lista = new ArrayList<>();
		lista.add(categoriaProva);
		Mockito.when(service.getAll()).thenReturn(lista);

		ResponseEntity<List<CategoriaProva>> response = controller.getAll();

		// verificações de tipo
		assertNotNull(response); // verificando se a resposta não é null
		assertNotNull(response.getBody()); // verificando se o corpo da resposta não é null
		assertEquals(HttpStatus.OK, response.getStatusCode()); // verificando se o status da resposta é OK(200)
		assertEquals(ResponseEntity.class, response.getClass()); // verificando se a resposta é do tipo esperado
		assertEquals(ArrayList.class, response.getBody().getClass()); // verificando se o corpo da resposta é do tipo
																		// esperado
		assertEquals(CategoriaProva.class, response.getBody().get(INDEX).getClass()); // verificando se o obj do corpo
																						// da resposta é do tipo
																						// categoria

		// verificações de atributos
		assertEquals(ID, response.getBody().get(INDEX).getId()); // verificando se o atributo id é o que esperados
		assertEquals(TITULO, response.getBody().get(INDEX).getTitulo()); // verificando se o atributo titulo é o que
																			// esperados
		assertEquals(DESCRICAO, response.getBody().get(INDEX).getDescricao()); // verificando se o atributo descricao é
																				// o que esperados
		assertEquals(null, response.getBody().get(INDEX).getProvas()); // verificando se o atributo provas é o que
																		// esperados
	}

	@Test
	void testPostCategoriaProvaDeveriaRetornarUmaCategoriaProvaCriada() {
		Mockito.when(service.post(Mockito.any())).thenReturn(categoriaProva);

		ResponseEntity<CategoriaProva> response = controller.postCategoriaProva(categoriaProva);

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(CategoriaProva.class, response.getBody().getClass());

		// verificações de atributos
		assertEquals(ID, response.getBody().getId()); // verificando se o atributo id é o que esperados
		assertEquals(TITULO, response.getBody().getTitulo()); // verificando se o atributo titulo é o que
		assertEquals(DESCRICAO, response.getBody().getDescricao()); // verificando se o atributo descricao é
		assertEquals(null, response.getBody().getProvas()); // verificando se o atributo provas é o que esperados
	}

	@Test
	void testPutCategoriaProvaDeveriaRetornarUmCategoriaProvaAtualizado() {
		Mockito.when(service.put(Mockito.any())).thenReturn(categoriaProva);
		
		ResponseEntity<CategoriaProva> response = controller.putCategoriaProva(categoriaProva);
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(CategoriaProva.class, response.getBody().getClass());

		// verificações de atributos
		assertEquals(ID, response.getBody().getId()); // verificando se o atributo id é o que esperados
		assertEquals(TITULO, response.getBody().getTitulo()); // verificando se o atributo titulo é o que
		assertEquals(DESCRICAO, response.getBody().getDescricao()); // verificando se o atributo descricao é
		assertEquals(null, response.getBody().getProvas()); // verificando se o atributo provas é o que esperados
		
		
	}

	@Test
	void testDeletetaoCategoriaProvaDeveriaDeletarUmaCategoriaProva() {
		Mockito.doNothing().when(service).delete(Mockito.anyLong());
		ResponseEntity<?> response =  controller.deletetaCategoriaProva(ID);
		
		assertNotNull(response);
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		Mockito.verify(service, Mockito.times(1)).delete(Mockito.anyLong());
		
	}

	private void startCategoriaProva() {
		categoriaProva = new CategoriaProva(ID, TITULO, DESCRICAO, null);
		optionalCategoriaProva = Optional.of(new CategoriaProva(ID, TITULO, DESCRICAO, null)); 

	}

}
