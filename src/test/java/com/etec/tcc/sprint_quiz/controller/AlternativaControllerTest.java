package com.etec.tcc.sprint_quiz.controller;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;
import com.etec.tcc.sprint_quiz.service.AlternativaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AlternativaControllerTest {

	/**
	 * Injeta um objeto da Classe TestRestTemplate, responsável por fazer
	 * requisições HTTP (semelhante ao Postman)
	 */
	@Autowired
	private TestRestTemplate testRestTemplate;

	/**
	 * dependencia da classe que sera testada
	 */
	@InjectMocks // cria uma instancia real, ja que é a classe que está sendo testada
	private AlternativaController alternativaController;

	/**
	 * dependencia do servico aqui ela sera mockada
	 */
	@Mock // cria um mock e nao a classe real, para personalizar as respostas dessa classe
	private AlternativaRepository alternativaRepository;

	/**
	 * dependencia do servico aqui ela sera mockada
	 */
	@Mock // cria um mock e nao a classe real, para personalizar as respostas dessa classe
	private AlternativaService alternativaService;

	@BeforeEach
	void setUp() throws Exception {
		
		/**
		 * Apaga todos os registros do banco de dados antes de iniciar os testes
		 */
		alternativaRepository.deleteAll();
		
		
	}

	@Test
	void testGetAll() {
		fail("Not yet implemented");
	}

	@Test
	void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllByTexto() {
		fail("Not yet implemented");
	}

	@Test
	void testPost() {
		fail("Not yet implemented");
	}

	@Test
	void testPut() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}
	
	void startdb() {
		
		
		/**
		 * Criar o usuário root para autenticar nos endpoints protegidos
		 */
//		usuarioService.cadastrarUsuario(new Usuario(0L, 
//			"Root", "root@root.com", "rootroot", " "));
	}
	

}
