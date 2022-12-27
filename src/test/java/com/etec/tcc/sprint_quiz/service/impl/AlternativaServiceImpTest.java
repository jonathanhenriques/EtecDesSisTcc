package com.etec.tcc.sprint_quiz.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.CALLS_REAL_METHODS;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.etec.tcc.sprint_quiz.model.Alternativa;
import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;
import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;
import com.etec.tcc.sprint_quiz.repository.QuestaoRepository;
import java.util.Optional;


/**
 * Classe de teste do Servico AlternativaService
 * @author hsjon
 *@date 26/12/2022
 */
@SpringBootTest //anotacao para sinalizar ao spring que e uma classe de test
class AlternativaServiceImpTest {
	
	private static final long ID = 1L;


	private static final long QUESTAO_ID = 1L;


	private static final String TEXTO_ALTERNATIVA = "texto alternativa a";


	/**
	 * dependencia da classe que sera testada
	 */
	@InjectMocks //cria uma instancia real, ja que é a classe que está sendo testada
	private AlternativaServiceImp alternativaServiceImpl;
	
	
	/**
	 * dependencia do servico
	 * aqui ela sera mockada
	 */
	@Mock //cria um mock e nao a classe real, para personalizar as respostas dessa classe
	private AlternativaRepository alternativaRepository;
	
	
	/**
	 * dependencia do servico
	 * aqui ela sera mockada
	 */
	@Mock //cria um mock e nao a classe real, para personalizar as respostas dessa classe
	private QuestaoRepository questaoRepository;
	

	/**
	 * dependencia do servico
	 * aqui ela sera mockada
	 */
	@Mock //cria um mock e nao a classe real, para personalizar as respostas dessa classe
	private ModelMapper modelMapper;
	
	
	
	/**
	 * Classes consumidas no service
	 * aqui elas seram mockadas
	 */
	@Mock //cria um mock e nao a classe real, para personalizar as respostas dessa classe
	private Alternativa alternativa;
	private AlternativaDTO alternativaDTO;
	private Optional<Alternativa> alternativaOptional;
	
	

	/**
	 * metodo utilizado para inicializar recursos antes de cada teste ser rodado
	 */
	@BeforeEach //indica que este metodo rodara antes de cada um dos testes
	void setUp() {
		MockitoAnnotations.openMocks(this); //inicializa os Mocks dessa classe,passando a própria classe, ele já inicializa todos os mocks necessários
		startCarga(); //cria os objetos na memória para os testes
	}
	
	@Test
	void testGetAll() {
		fail("Not yet implemented");
	}

	@Test
	@DisplayName("Busca uma alternativa pelo id") //define um nome personalizado para o método
	void testGetByIdBuscaUmaAlternativaPorId() {
		
		/**
		 * Quando o método findById() de AlternativaRepository for chamado
		 * passando qualquer Long,
		 * então retorne o que eu passar
		 */
		//cenário
		Mockito.when(alternativaRepository.findById(Mockito.anyLong())).thenReturn(alternativaOptional);
		
		
		/**
		 * Como o serviço já foi mockado
		 * é feita a chamada, como se fosse a classe real
		 */
		//execução
		Alternativa response = alternativaServiceImpl.getById(ID);
		
		
		/**
		 * É feita a verificação
		 * comparando o que é esperado
		 * com a resposta
		 */
		//verificação
		Assertions.assertNotNull(response); //sem import static
		assertEquals(alternativa.getClass(), response.getClass()); //compara dois objs, valores...
		assertEquals(ID, response.getId());
		assertEquals(QUESTAO_ID, response.getQuestao().getId());
		assertEquals(TEXTO_ALTERNATIVA, response.getTexto());
	}

	@Test
	void testPostListaAlternativa() {
		fail("Not yet implemented");
	}

	@Test
	void testPostListaAlternativasComQuestaoSalva() {
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
	
	
	/**
	 * Método criado para inicializar
	 * os objetos para os testes
	 */
	private void startCarga() {
//		Usuario usuario1 = new Usuario(null, "Jonathan", "jonathan@email.com", "12345678", "", null, null, null);
//		CategoriaQuestao cq1 = new CategoriaQuestao(null, "Categoria questao teste 2", "Descrição categoria questao 2",
//				null);
//		Questao questao1 = new Questao(null, "instituicao ", LocalDate.now(), "imagem", "Texto questão teste",
//				DificuldadeQuestao.FACIL, null, null, null, null);
		Questao questao = new Questao();
		questao.setId(QUESTAO_ID);
		alternativa = new Alternativa(ID, TEXTO_ALTERNATIVA, "", questao);
		alternativaDTO = new AlternativaDTO(ID, TEXTO_ALTERNATIVA, "", QUESTAO_ID);
		alternativaOptional = Optional.of(new Alternativa(ID, TEXTO_ALTERNATIVA, "", questao));
	}

}
