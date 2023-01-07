package com.etec.tcc.sprint_quiz.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.etec.tcc.sprint_quiz.model.Usuario;
import com.etec.tcc.sprint_quiz.repository.UsuarioRepository;
import com.etec.tcc.sprint_quiz.security.JwtAuthFilter;

import lombok.RequiredArgsConstructor;

/**
 * Clase para configurar o Spring security camada de segurança da aplicação
 * 
 * @author hsjon
 * @since 15/12/2022
 *
 */

@Desenvolvimento
@EnableWebSecurity // indica para o spring considerar essa classe para configurar a segurança
@RequiredArgsConstructor
public class DesSecurityConfig {

	@Autowired
	@Lazy
	private JwtAuthFilter jwtAuthFilter;
	@Autowired
	private UsuarioRepository usuarioRepository;

	/**
	 * Método será um Bean e substituirá o securityFilterChain, o filtro de
	 * segurança padrão, do spring
	 * 
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/**").permitAll();
		http.authorizeRequests().antMatchers("**/**").permitAll()
		// *************
		.and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				/**
				 * Informando qual provedor de autenticacao usar
				 */
				.and()
				.cors().and().csrf()
				.disable();// talvez tenha que ser retirada caso front end nao funcione
//		).build();

		return http.build();
		

	}

	/**
	 * Indica quais classes usar para autenticação Substitui a chamada padrão do
	 * userDetailsService pela implementação criada de userDetailsService
	 * 
	 * @return
	 */
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	/**
	 * injeta o gerenciador de autenticação em qualquer ponto da aplicação que
	 * precise dele
	 * 
	 * @param config
	 * @return
	 * @throws Exception
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * carrega o usuario do banco de dados atraves do login implementação da
	 * interface UserDetailsService e seu método loadUserByUsername
	 * 
	 * @param username
	 * @return o usuario encontrado convertido em usuarioDetails
	 */
	@Bean //verificar se posso excluir esse método, possível duplicata
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				Usuario usuario = usuarioRepository.findByUsernameFetchRoles(username).orElseThrow(
						() -> new UsernameNotFoundException("Email de usuário não encontrado na base de dados!"));

				return new User(usuario.getUsername(), usuario.getPassword(), usuario.getRoles());
			}
		};
	}
	
	
	
//	 @Bean
//	 UserDetailsService allUsers() {
//	 InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//	 manager
//	 .createUser(User.builder()
//	 .passwordEncoder(password -> password)
//	 .username("madruguinha")
//	 .password("12345678")
//	 .authorities("USER")
//	 .roles("USER").build());
//	 manager
//	 .createUser(User.builder()
//	 .passwordEncoder(password -> password)
//	 .username("jonas")
//	 .password("12345678")
//	 .authorities("USER")
//	 .roles("USER").build());
//	 return manager;
//	 }
	
	

}
