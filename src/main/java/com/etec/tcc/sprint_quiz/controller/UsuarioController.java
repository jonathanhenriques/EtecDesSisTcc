package com.etec.tcc.sprint_quiz.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.etec.tcc.sprint_quiz.exception.SenhaInvalidaException;
import com.etec.tcc.sprint_quiz.exception.UsuarioNotFoundException;
import com.etec.tcc.sprint_quiz.model.Role;
import com.etec.tcc.sprint_quiz.model.Usuario;
import com.etec.tcc.sprint_quiz.model.dto.RoleDTO;
import com.etec.tcc.sprint_quiz.model.dto.TokenDTO;
import com.etec.tcc.sprint_quiz.model.dto.UsuarioLoginDTO;
import com.etec.tcc.sprint_quiz.service.impl.UsuarioServiceImpl;
import com.etec.tcc.sprint_quiz.util.JwtUtils;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController()
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl usuarioService;

//    @Autowired
//    private JwtService jwtService;

	@Autowired // para método autenticar() que vai autenticar o usuário automaticamente
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtUtils jwtUtils;

	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);

	/**
	 * Ao chamar authenticationManager.authenticate estamos delegando o processo de
	 * autenticação para o spring realizá-lo no contexto da aplicação
	 * 
	 * @param usuarioLoginDTO com email e senha
	 * @return uma string contendo o Token
	 */
	// youtube
	@PostMapping("/autenticar")
	public ResponseEntity<String> autentiar(@RequestBody UsuarioLoginDTO usuario) {
		Authentication authenticationManager2 = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword()));
		UserDetails user = usuarioService.loadUserByUsername(usuario.getUsername());
//				.orElseThrow(() -> new UsuarioNotFoundException(usuario.getUsername()));///////// orElseThrow
		if (user != null) {
			return ResponseEntity.ok(jwtUtils.generateToken(user));
		}
		throw new UsuarioNotFoundException("Usuário não cadastrado");

	}

	@Operation(summary = "Obtem usuario pelo id")
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {
		LOGGER.info("Chamando endpoint findById({})  em usuarioController", id);
		return ResponseEntity.ok(usuarioService.findById(id));

	}

	@Operation(summary = "Obtem usuario pelo email")
	@GetMapping("/email/{usuario}")
	@ResponseStatus
	public ResponseEntity<Usuario> findByEmail(@PathVariable String usuario) {
		return usuarioService.findByUsername(usuario).map(u -> ResponseEntity.ok(u))
				.orElse(ResponseEntity.notFound().build());
	}

	@Operation(summary = "Obtem usuario pelo nome exato")
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Usuario>> findAllByNome(@PathVariable String nome) {
		return ResponseEntity.ok(usuarioService.findAllByNomeContainingIgnoreCase(nome));//
	}

	@Operation(summary = "Obtem todos os usuario")
	@GetMapping("/todos")
	public ResponseEntity<List<Usuario>> findAll() {
		return ResponseEntity.ok(usuarioService.findAll());
	}

	// udemy
	@Operation(summary = "Logar um usuario")
	@PostMapping("/logar")
	public ResponseEntity<TokenDTO> autenticar(@RequestBody UsuarioLoginDTO usuarioLogin) {

		Optional<Usuario> usuarioBD = Optional.ofNullable(usuarioService.findByUsername(usuarioLogin.getUsername())
				.orElseThrow(() -> new UsuarioNotFoundException(usuarioLogin.getUsername())));

		try {
//        	Usuario usuario = Usuario.builder()
//        			.username(usuarioLogin.getUsername())
//        			.password(usuarioLogin.getPassword()).build();

        	UserDetails usuarioAutenticado = 
			usuarioService.autenticar(usuarioLogin);

			String token = jwtUtils.generateToken(usuarioAutenticado);
			return ResponseEntity.ok(new TokenDTO(usuarioBD.get().getId(), usuarioBD.get().getUsername(), token));

		} catch (UsernameNotFoundException | SenhaInvalidaException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}

	}

	@Operation(summary = "Cadastrar um usuario")
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> cadastrarUsuario(@Valid @RequestBody Usuario usuario) {
		URI uri = URI.create(
				ServletUriComponentsBuilder.fromCurrentContextPath().path("/usuario/cadastraUsuario").toUriString());
		return usuarioService.cadastrarUsuario(usuario).map(u -> ResponseEntity.created(uri).body(u))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

//    @Operation(summary = "Atualizar um usuario")
//    @PutMapping("/atualizar")
//    public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
//        return usuarioService.atualizarUsuario(usuario)
//                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
//                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//    }

//    @Operation(summary = "deleta um usuário")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
//        return usuarioRepository.findById(id)
//                .map(u -> {
//                    usuarioRepository.delete(u);
//                    return ResponseEntity.noContent().build();
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }

//	@Operation(summary = "deleta um usuário")
//	@DeleteMapping("/{id}")
//	public void deleteUsuario(@PathVariable Long id) {
//		usuarioService.findById(id).map(u -> {
//			usuarioRepository.delete(u);
//			return void.class;
//		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado! | " + id));
//	}

	@PostMapping("role/cargo")
	public ResponseEntity<Role> cadastrarRole(@Valid @RequestBody Role role) {
		return usuarioService.cadastrarRole(role).map(r -> ResponseEntity.status(HttpStatus.CREATED).body(r))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

	}

	@PostMapping("role/roleUsuario")
	public ResponseEntity<?> adicionarRoleAoUsuario(@Valid @RequestBody RoleDTO role) {
		usuarioService.addRoleUsuario(role.getUsername(), role.getCargo());
		return ResponseEntity.ok().build();

	}

}
