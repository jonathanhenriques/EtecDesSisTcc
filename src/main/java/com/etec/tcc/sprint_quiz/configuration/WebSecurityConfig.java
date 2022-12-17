//package com.etec.tcc.sprint_quiz.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.etec.tcc.sprint_quiz.security.JwtAuthFilterOld;
//import com.etec.tcc.sprint_quiz.security.JwtService;
//import com.etec.tcc.sprint_quiz.security.UsuarioServiceImpl;
//
///**
// * Classe SecurityConfig que contêm toda a configuração
// *         do spring security
// * @author hsjon 01/12/2022 
// */
//
//@EnableWebSecurity // permite configurar o security
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	private UsuarioServiceImpl usuarioService;
//
//	@Autowired
//	private JwtService jwtService;
//
//	
//	/**
//	 * faz a criptografia da senha usando a lógica do BCryptPasswordEncoder
//	 * 
//	 * @return
//	 */
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	/**
//	 * Responsavel por aplicar o filtro criado para interceptar as requisições
//	 * 
//	 * @return
//	 */
//	@Bean
//	public OncePerRequestFilter jwtFilter() {
//		return new JwtAuthFilterOld(jwtService, usuarioService);
//	}
//
//	/**
//	 * método responsavel pela autenticacao do usuario
//	 * 
//	 * @param AuthenticationManagerBuilder faz a autenticação do usuário e o coloca
//	 *                                     dentro do contexto do spring security
//	 */
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception { // método acaba sendo substituído
//																					// pelo jwtAuthFilter
//
//		auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder());
//
////		//criacao de uma autenticacao em memória apenas para entendimento
////		auth.inMemoryAuthentication()//sinalizando tipo de autenticacao em memoria
////		.passwordEncoder(passwordEncoder())//sinalizando metodo de criptografia
////		.withUser("fulano")//login
////		.password(passwordEncoder().encode("123"))//sinalizando a senha e ja criptografando
////		.roles("USER");//perfil do usuario
//////		.roles("ADMIN");
//	}
//
//	/**
//	 * método responsavel pela autorizacao das urls
//	 * 
//	 */
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		// habilitando o h2 console no navegador*********************
//		http.headers().frameOptions().disable();
//		http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
//		// *************
//
//		http.authorizeRequests().antMatchers("/usuarios/logar").permitAll().antMatchers("/usuarios/cadastrar")
//				.permitAll()
//
////		.antMatchers(HttpMethod.POST, "/usuarios/*").permitAll()
//
////		.antMatchers(HttpMethod.GET, "/usuarios/*").permitAll()
//
////		.antMatchers("/usuarios/*").permitAll()
////		.antMatchers("/usuarios/**").permitAll()
//
////		.antMatchers("/questoes/**").authenticated() //para acessar esse endpoint e necessario estar logado
////		.antMatchers("/questoes/**").hasRole("USER")//para acessar esse endpoint e necessario ter o perfil USER
////		.antMatchers("/questoes/**").hasAnyRole("USER","ADMIN")//para acessar esse endpoint e necessario ter os perfis indicados
////		.antMatchers("/questoes/**").hasAuthority("MANTER_USUARIO")//para acessar esse endpoint e necessario ter A AUTHORITY MANTER_USUARIO
////		.antMatchers("/questoes/**").permitAll()//esse endpoint e de livre acesso para qualquer usuario
////		.antMatchers("/provas/**").authenticated()
////		.antMatchers("/categoriaQuestao/**").authenticated()
////        .antMatchers("/categoriaProva").permitAll()
////		.antMatchers("/categoriaProva/**").authenticated()
////		.antMatchers("/categoriaProva/**").permitAll()
////		.antMatchers("/alternativas/**").authenticated()
//
////		.antMatchers("/usuarios/cadastrar").permitAll()
////		.antMatchers("/usuarios/logar").permitAll()
////		.antMatchers(HttpMethod.GET,"/usuarios/*").permitAll()
//
////		.antMatchers("/questaoProva/**").authenticated()
////		.antMatchers(HttpMethod.OPTIONS).permitAll()
//				.anyRequest().authenticated()
////        .anyRequest().permitAll()
//				// metodo and() volta para a raiz do objeto, no caso >> http << ,sempre depois
//				// de termos feito alguma configuracao
////		.formLogin();//habilita a tela de login do spring security
////		.formLogin("/meu-login-customizado")//indicamos onde esta a tela de login customizada, normalmente fica em resources/public ou templates
//				.and()
//
//				/**
//				 * Mantêm um usuário, após o login, na sessão para todas as requisições feitas,
//				 * desativamos o formLogin ao usar
//				 */
////		.httpBasic() 
////        .and()
//				/**
//				 * definimos que o token será verificado em cada requisição, e se expirar, será
//				 * negado
//				 */
//				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//				/**
//				 * Adiciona um filtro que foi criado (jwtFilter, que coloca o usuário no
//				 * contexto "sessão") e depois executa o filtro padrão do security
//				 * (UsernamePasswordAuthenticationFilter)
//				 */
//				.and().addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
////        .and()
//				.cors().and().csrf().disable();// talvez tenha que ser retirada caso front end nao funcione
//
//	}
//
//}