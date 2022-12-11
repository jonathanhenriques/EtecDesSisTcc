package com.etec.tcc.sprint_quiz.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.etec.tcc.sprint_quiz.security.JwtAuthFilter;
import com.etec.tcc.sprint_quiz.security.JwtService;
import com.etec.tcc.sprint_quiz.security.UsuarioServiceImpl;

/**
 * Configura o Spring Security com recomendações atuais
 * 
 * @author hsjon 10/12/2022
 *
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) //permite a verificação de bloqueios a nível de endPoint nos Controllers
public class WebSecurityConfig {

	@Autowired
	@Lazy
	private UsuarioServiceImpl usuarioService;

	@Autowired
	private JwtService jwtService;

	/**
	 * faz a criptografia da senha usando a lógica do BCryptPasswordEncoder
	 * 
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Responsavel por aplicar o filtro criado para interceptar as requisições
	 * 
	 * @return
	 */
	@Bean
	public OncePerRequestFilter jwtFilter() {
		return new JwtAuthFilter(jwtService, usuarioService);
	}

	/**
	 * método responsavel pela autorizacao das urls utilizando componentes do spring
	 * fazendo a verificação de bloqueio nos Controllers
	 * 
	 */
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		/**
		 * habilitando e permite o h2 console no navegador
		 */
		http.headers().frameOptions().disable();
		http.authorizeRequests().antMatchers("/h2-console/**").permitAll();

		http.authorizeRequests()
		.antMatchers("/usuarios/logar").permitAll()
		.antMatchers("/usuarios/cadastrar").permitAll()

				/**
				 * Opções de permissão:<br>
				 * .authenticated() - para acessar esse endpoint e necessario estar logado. <br>
				 * .hasRole("USER") - para acessar esse endpoint e necessario ter o perfil USER.
				 * .hasAnyRole("USER","ADMIN") - para acessar esse endpoint e necessario ter os
				 * perfis indicados. <br>
				 * .hasAuthority("MANTER_USUARIO") - para acessar esse endpoint e necessario ter
				 * A AUTHORITY MANTER_USUARIO.<br>
				 * .permitAll() - esse endpoint é de livre acesso para qualquer usuário.
				 */
				.anyRequest().authenticated()
//		        .anyRequest().permitAll()

				/**
				 * .formLogin();//habilita a tela de login do spring security
				 */
//				.formLogin();
//				.formLogin("/meu-login-customizado")//indicamos onde esta a tela de login customizada caso haja, normalmente fica em resources/public ou templates

				/**
				 * metodo and() volta para a raiz do objeto, no caso >> http << ,sempre depois
				 * de termos feito alguma configuracao
				 */
				.and()

				/**
				 * Mantêm um usuário, após o login, na sessão para todas as requisições feitas,
				 * desativamos o formLogin ao usar
				 */
//				.httpBasic() 
//		        .and()
				/**
				 * definimos que o token será verificado em cada requisição, e se expirar, será
				 * negado
				 */
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

				/**
				 * Adiciona um filtro que foi criado (jwtFilter, que coloca o usuário no
				 * contexto "sessão") e depois executa o filtro padrão do security
				 * (UsernamePasswordAuthenticationFilter)
				 */
				.and().addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
				/**
				 * Relacionado a permissões de requisição entre navegadores, servidores...cors,
				 * csrf
				 */
				.cors().and().csrf().disable();

		return http.build();
	}

}
