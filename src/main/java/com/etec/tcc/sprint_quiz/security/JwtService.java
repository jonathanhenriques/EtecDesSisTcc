package com.etec.tcc.sprint_quiz.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.stereotype.Service;

import com.etec.tcc.sprint_quiz.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/**
 * 
 * @author hsjon
 *Classe responsável por fazer a
 *Codificação e a
 *Decodificação do token
 */
@Service
public class JwtService {

//	@Value("${security.jwt.expiracao}") // valor vindo do application.properties
//	private String expiracao;
	private String expiracao = "30";

//	@Value("${security.jwt.expiracao}")
//	private String chaveAssinatura;
	private String chaveAssinatura = "bWV1IGdhdG8gc2UgY2hhbWEgbWFkcnVndWluaGE=";

	public String gerarToken(Usuario usuario) {
		long expiracaoString = Long.valueOf(expiracao);
		LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expiracaoString); // pega a data de agora e
																							// acrescenta o tempo da
																							// variavel "expiracao"
		Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant(); // aqui pegamos o momento atual
		Date data = Date.from(instant); // aqui temos a data gerada
		
		
		//		quebrando, arrumar depois
//		HashMap<String, Object> claims = new HashMap<>();
//		claims.put("nome", usuario.getNome());
//		if(usuario.getTipo() != null)
//			claims.put("tipo de usuario", usuario.getTipo());
		
		return Jwts
				.builder()
				.setSubject(usuario.getEmail())// identificacao do usuario
				.setExpiration(data)
//				.setClaims(claims) //informacoes diversas
				.signWith(SignatureAlgorithm.HS512, "bWV1IGdhdG8gc2UgY2hhbWEgbWFkcnVndWluaGE=")
				.compact();

	}
	
	private Claims obterClaims(String token) throws ExpiredJwtException{
		return Jwts.parser()
				.setSigningKey(chaveAssinatura)
				.parseClaimsJws(token)
				.getBody();
	}
	
	public boolean tokenValido(String token) {
		try {
			System.out.println("tok - " + token);
			Claims claims = obterClaims(token);
			Date dataExpiracao = claims.getExpiration();
			LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(); //convertemos de Date para LocalDateTime
			return !LocalDateTime.now().isAfter(data); //verificamos se a hora atual NÃO é depois da hora de expiracao do token
		}catch(Exception e) {
			System.out.println("excecao...");
			return false;
		}
	}
	
	public String obterLoginUsuario(String token) throws ExpiredJwtException {
		return (String) obterClaims(token).getSubject();
	}

}
