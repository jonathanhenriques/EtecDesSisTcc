package com.etec.tcc.sprint_quiz.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.etec.tcc.sprint_quiz.exception.CargoJaCadastradoException;
import com.etec.tcc.sprint_quiz.exception.CargoNotFoundException;
import com.etec.tcc.sprint_quiz.exception.SenhaInvalidaException;
import com.etec.tcc.sprint_quiz.exception.UsuarioJaCadastradoException;
import com.etec.tcc.sprint_quiz.exception.UsuarioNotFoundException;
import com.etec.tcc.sprint_quiz.model.Role;
import com.etec.tcc.sprint_quiz.model.Usuario;
import com.etec.tcc.sprint_quiz.model.dto.UsuarioLoginDTO;
import com.etec.tcc.sprint_quiz.repository.RolesRepository;
import com.etec.tcc.sprint_quiz.repository.UsuarioRepository;
import com.etec.tcc.sprint_quiz.service.UsuarioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Classe de implementação de UsuarioService e UserDetailsService Responsável
 * pela autenticação, cadastro e validação de login e senha
 * 
 * @author hsjon
 * @since 11/12/2022
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional // aumenta o acesso da classe ao banco de dados, as vezes resolve 401,403
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

	@Autowired
	private RolesRepository rolesRepository;
	@Autowired
	@Lazy
	private PasswordEncoder encoder;
	@Autowired
	private UsuarioRepository usuarioRepository;

	/**
	 * Busca o usuário no banco e verifica se a senha passada e a do banco são
	 * iguais ou não
	 * 
	 * @param usuario
	 * @return usuarioDetails
	 */
	public UserDetails autenticar(UsuarioLoginDTO usuario) {
		UserDetails user = loadUserByUsername(usuario.getUsername());
//		UsuarioDetails user = loadUserByUsername(usuario.getUsername());
		boolean senhasBatem = encoder.matches(usuario.getPassword(), user.getPassword());
		if (senhasBatem) {
			log.info("Usuário autenticado | '{}'", usuario.getUsername());
			return user;
		}
		throw new SenhaInvalidaException();
	}

	/**
	 * carrega e retorna o usuario do banco de dados atraves do login
	 * 
	 * @param username
	 * @return usuarioDetails
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepository.findByUsernameFetchRoles(username)
				.orElseThrow(() -> new UsernameNotFoundException("Email de usuário não encontrado na base de dados!"));

//		return new Usuario(null, "Jonathan", "jonathan@email.com", "12345678", "", 0, usuario.getPermissions(), usuario.getRoles(), new ArrayList<Questao>(), new ArrayList<Prova>());
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getRoles());

	}

	@Override
	public Optional<Role> cadastrarRole(Role cargo) {
		log.info("Cadastrando novo cargo! | {}", cargo.getCargo());
		if (rolesRepository.findByCargo(cargo.getCargo()).isPresent())
			throw new CargoJaCadastradoException(cargo.getCargo());
		return Optional.of(rolesRepository.save(cargo));
	}

	@Override
	public void addRoleUsuario(String username, String cargo) {

		Usuario usuario = usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(username));
		Role roles = rolesRepository.findByCargo(cargo).orElseThrow(() -> new CargoNotFoundException(cargo));
		usuario.getRoles().add(roles);
	}

	// métodos do usuario com regra de negócio

	/**
	 * Busca o usuário específico no banco através de um id
	 * 
	 * @param id
	 * @return usuario
	 */
	public Usuario findById(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id.toString()));
	}

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	/**
	 * Busca o usuário específico no banco através de um email
	 * 
	 * @param id
	 * @return Optional<usuario>
	 */
	public Optional<Usuario> findByUsername(String email) {
		return usuarioRepository.findByUsername(email);
	}

	/**
	 * Busca uma lista de usuários com o nome passado
	 * 
	 * @param nome do usuários
	 * @return Lista com usuários
	 */
	public List<Usuario> findAllByNomeContainingIgnoreCase(@PathVariable String nome) {
		return usuarioRepository.findAllByNomeContainingIgnoreCase(nome);//
	}

	/**
	 * Cadastra um novo usuário no banco
	 * 
	 * @param usuario
	 * @return Optional<usuario>
	 */
	@Transactional
	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
		log.info("Cadastrando novo usuário | {}", usuario.getUsername());
		if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent())
//			return Optional.empty();
			throw new UsuarioJaCadastradoException(usuario.getUsername());

		if (usuario.getId() != null)
			usuario.setRoles(null);/// rever nao funciona

		String senhaCriptografada = encoder.encode(usuario.getPassword()); // solicitamos a criptografia da senha
		usuario.setPassword(senhaCriptografada); // salvamos o usuario já com a senha criptografada
		return Optional.of(usuarioRepository.save(usuario));
	}

	// Alterar implementação de usuário
//	/**
//	 * Desativa o usuário, mas não exclui
//	 * @param id
//	 */
//	public void deleteUsuarioLogicamente(@PathVariable Long id) {
//		usuarioRepository.findById(id).map(u -> {
//			usuarioRepository.delete(u);
//			return void.class;
//		}).orElseThrow(() -> new UsuarioNotFoundException("com id | " + id));
//	}

}
