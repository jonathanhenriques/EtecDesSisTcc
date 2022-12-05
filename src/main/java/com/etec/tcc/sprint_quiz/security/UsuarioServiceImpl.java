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

import com.etec.tcc.sprint_quiz.exception.ProvaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.RegraNegocioException;
import com.etec.tcc.sprint_quiz.exception.SenhaInvalidaException;
import com.etec.tcc.sprint_quiz.exception.UsuarioJaCadastradoException;
import com.etec.tcc.sprint_quiz.exception.UsuarioNotFoundException;
import com.etec.tcc.sprint_quiz.model.Usuario;
import com.etec.tcc.sprint_quiz.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UserDetailsService{

	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	
	
	public UserDetails autenticar (Usuario usuario) {
		UserDetails user = loadUserByUsername(usuario.getEmail());
		boolean senhasBatem = encoder.matches(usuario.getSenha(), user.getPassword());
		if(senhasBatem) {
			return user;
		}
		throw new SenhaInvalidaException();
	}
	
	
	
	/**
	 * carrega o usuario do banco de dados
	 * atraves do login
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByEmail(username)
		.orElseThrow(() -> new UsernameNotFoundException("Email de usuário não encontrado na base de dados!"));
		
		
		String[] roles = usuario.getTipo().equals("admin") ?  new String[] {"ADMIN", "USER"} :  new String[] {"USER"};
		
		
		return User
				.builder()
				.username(usuario.getEmail())
				.password(usuario.getSenha()) //como a senha vem do banco, já estará criptografada
				.roles(roles)
				.build();
		
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
	
	
	
	
	
	//métodos do usuario com regra de negócio
	public Usuario findById(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuarioNotFoundException(id.toString()));
	}
	
	public Optional<Usuario> findByEmail(String email){
		return usuarioRepository.findByEmail(email);
	}
	


	
	
	@Transactional
	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
		
		if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
//			return Optional.empty();
			 throw new UsuarioJaCadastradoException(usuario.getEmail());
		
		if(usuario.getTipo() != null)
			usuario.setTipo("user");
		
		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha()); //solicitamos a criptografia da senha
		usuario.setSenha(senhaCriptografada); //salvamos o usuario já com a senha criptografada
		return Optional.of(usuarioRepository.save(usuario));
	}
	

}
