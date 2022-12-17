//package com.etec.tcc.sprint_quiz.security;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//
///**
// * filtro para Interceptar as requisições, obter o token do Header da requisição
// * e mandar um usuário autenticado caso o token esteja válido, para a sessão
// * 
// * @author hsjon 
// */
//public class JwtAuthFilterOld extends OncePerRequestFilter {
//
//	private JwtService jwtService;
//
//	private UsuarioServiceImpl usuarioService;
//
//	public JwtAuthFilterOld(JwtService jwtService, UsuarioServiceImpl usuarioService) {
//		this.jwtService = jwtService;
//		this.usuarioService = usuarioService;
//	}
//
//	/**
//	 * 
//	 * 
//	 */
//	@Override
//	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
//			FilterChain filterChain) throws ServletException, IOException {
//
//		String authorization = httpServletRequest.getHeader("Authorization");
//
//		/**
//		 * If verificando se o token foi passado no Header da requisição e se ele é do
//		 * tipo Bearer
//		 */
//		if (authorization != null && authorization.startsWith("Bearer")) {
//			String token = authorization.split(" ")[1];// Pega só o Token sem Bearer
//			boolean isValid = jwtService.tokenValido(token);
//
//			/**
//			 * Se o token for válido, obtém login(email) do usuário busca o usuário do banco
//			 * Cria um usuário para o contexto do psring security com login e senha passadas
//			 * pelo login E coloca no contexto do spring security (na sessão) por fim, a
//			 * requisição é despachada
//			 */
//			if (isValid) {
//				String loginUsuario = jwtService.obterLoginUsuario(token); // obtendo dados do usuario
//				UserDetails usuario = usuarioService.loadUserByUsername(loginUsuario);
//				UsernamePasswordAuthenticationToken user = new // Tipo utilizado e aceito para se colocar no contexto do
//																// spring security
//				UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
//				user.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));// sinalizando
//																										// que é uma
//																										// autenticação
//																										// web
//				SecurityContextHolder.getContext().setAuthentication(user); // pegando contexto do spring security para
//																			// colocar usuário no contexto
//			}
//		}
//
//		filterChain.doFilter(httpServletRequest, httpServletResponse);
//
//	}
//
//}
