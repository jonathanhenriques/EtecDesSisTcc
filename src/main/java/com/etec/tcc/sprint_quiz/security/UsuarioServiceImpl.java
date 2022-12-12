package com.etec.tcc.sprint_quiz.security;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.etec.tcc.sprint_quiz.exception.CargoJaCadastradoException;
import com.etec.tcc.sprint_quiz.exception.CargoNotFoundException;
import com.etec.tcc.sprint_quiz.exception.SenhaInvalidaException;
import com.etec.tcc.sprint_quiz.exception.UsuarioJaCadastradoException;
import com.etec.tcc.sprint_quiz.exception.UsuarioNotFoundException;
import com.etec.tcc.sprint_quiz.model.Role;
import com.etec.tcc.sprint_quiz.model.Usuario;
import com.etec.tcc.sprint_quiz.model.UsuarioLoginDTO;
import com.etec.tcc.sprint_quiz.model.dto.RoleDTO;
import com.etec.tcc.sprint_quiz.repository.RolesRepository;
import com.etec.tcc.sprint_quiz.repository.UsuarioRepository;
import com.etec.tcc.sprint_quiz.service.UsuarioService;

import lombok.extern.slf4j.Slf4j;



/**
 * Classe de implementação de UsuarioService e UserDetailsService
 * Responsável pela autenticação, cadastro e validação de login e senha
 * 
 * @author hsjon
 *@since 11/12/2022
 */
@Slf4j
@Service
@Transactional //aumenta o acesso da classe ao banco de dados, as vezes resolve 401,403
public class UsuarioServiceImpl implements UsuarioService ,UserDetailsService{

	@Autowired
	private RolesRepository rolesRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	
	/**
	 * Busca o usuário no banco
	 * e verifica se a senha passada e a do banco
	 * são iguais ou não
	 * 
	 * @param usuario
	 * @return usuarioDetails
	 */
	public UserDetails autenticar (UsuarioLoginDTO usuario) {
		UserDetails user = loadUserByUsername(usuario.getUsername());
		boolean senhasBatem = encoder.matches(usuario.getPassword(), user.getPassword());
		if(senhasBatem) {
			return user;
		}
		throw new SenhaInvalidaException();
	}
	
	
	
	/**
	 * carrega o usuario do banco de dados
	 * atraves do login
	 * @param username
	 * @return usuarioDetails
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByUsername(username)
		.orElseThrow(() -> new UsernameNotFoundException("Email de usuário não encontrado na base de dados!"));
		
		
//		String[] roles = usuario.getRoles().equals("admin") ?  new String[] {"ADMIN", "USER"} :  new String[] {"USER"};//rever nao funciona
		
		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());	
//		return User
//				.builder()
//				.username(usuario.getUsername())
//				.password(usuario.getPassword()) //como a senha vem do banco, já estará criptografada
//				.authorities(usuario.getAuthorities())
//		
//				.build();
		
		//outro usuario em memoria
//		if(!username.equals("ciclano")) {
//			throw new UsernameNotFoundException("Usuario nao encontrado na base.");
//		}
//		
//		return User
//				.builder()
//				.username("ciclano")
//				.password(encoder.encode("123"))
//				.roles("USER", "ADMIN")
//				.build();
	}
	
	
	@Override
	public Optional<Role> cadastrarRole(Role cargo) {
		log.info("Cadastrando novo cargo! | {}", cargo.getCargo());
		if(rolesRepository.findByCargo(cargo.getCargo()).isPresent())
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

	
	
	
	
	
	//métodos do usuario com regra de negócio
	
	/**
	 * Busca o usuário específico no banco
	 * através de um id
	 * 
	 * @param id
	 * @return usuario
	 */
	public Usuario findById(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuarioNotFoundException(id.toString()));
	}
	
	
	/**
	 * Busca o usuário específico no banco
	 * através de um email
	 * 
	 * @param id
	 * @return Optional<usuario>
	 */
	public Optional<Usuario> findByUsername(String email){
		return usuarioRepository.findByUsername(email);
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
		if(usuarioRepository.findByUsername(usuario.getUsername()).isPresent())
//			return Optional.empty();
			 throw new UsuarioJaCadastradoException(usuario.getUsername());
		
		if(usuario.getId() != null)
			usuario.setRoles(null);///rever nao funciona
		
		String senhaCriptografada = passwordEncoder.encode(usuario.getPassword()); //solicitamos a criptografia da senha
		usuario.setPassword(senhaCriptografada); //salvamos o usuario já com a senha criptografada
		return Optional.of(usuarioRepository.save(usuario));
	}



	







	



	










	
	
	


	



	



	






	



	

}
