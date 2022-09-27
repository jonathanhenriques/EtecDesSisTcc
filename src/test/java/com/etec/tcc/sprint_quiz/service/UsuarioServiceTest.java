package com.etec.tcc.sprint_quiz.service;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.Usuario;
import com.etec.tcc.sprint_quiz.repository.UsuarioRepository;

@SpringBootTest
class UsuarioServiceTest {

	private static final Long ID = null;

	private static final String NAME = "Jonathan";

	private static final String EMAIL = "jonathan@email.com";

	private static final String SENHA = "12345678";

	private static final String FOTO = "";

	private static final String TIPO = "";

	private static final List<Questao> LISTA_QUESTOES = new ArrayList<Questao>();

	private static final List<Prova> LISTA_PROVAS = new ArrayList<Prova>();

	@InjectMocks //injeta uma instancia real
	private UsuarioService service;
	
	@Mock //injeta instancias mock
	private UsuarioRepository usuarioRepository;
	
	private Usuario usuario;
	private Optional<Usuario> optionalUsuario;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this); //indicamos de onde serão os mocks
		startUsuario();
	}

	@Test
	void testCadastrarUsuario() {
		fail("Not yet implemented");
	}

	@Test
	void testAtualizarUsuario() {
		fail("Not yet implemented");
	}

	@Test
	void testAutenticarUsuario() {
		fail("Not yet implemented");
	}
	
	private void startUsuario() {
		usuario = new Usuario(
	        		ID, NAME, EMAIL, SENHA, FOTO, TIPO, LISTA_QUESTOES , LISTA_PROVAS);
		 
		 optionalUsuario = Optional.of(new Usuario(
				 ID, NAME, EMAIL, SENHA, FOTO, TIPO, LISTA_QUESTOES , LISTA_PROVAS));
	}

}
