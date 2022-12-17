package com.etec.tcc.sprint_quiz.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author internet
 * @since 15/12/2022
 */
@Slf4j
@Service
public class JwtUtils {

	@Value("${security.jwt.chave-assinatura}") // valor vindo do application.properties
	private String SECRET_KEY;
//    private String SECRET_KEY = "secret";
//	@Value("${}")
	private int expiracao = 1000 * 60 * 30;

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		boolean isExpired = extractExpiration(token).before(new Date());
		log.info("token expirado: " + isExpired);
		return isExpired;
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		String token = createToken(claims, userDetails);
		log.info("Token gerado: " + token);
		return token;
	}

	private String createToken(Map<String, Object> claims, UserDetails userDetails) {
		log.info("Token sendo criado... ");
		return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiracao))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	public Boolean isTokenValid(String token, UserDetails userDetails) {
		String username = extractUsername(token);
		boolean isValid = username.equals(userDetails.getUsername()) && !isTokenExpired(token);
		log.info("token é válido: {}", isValid);
		return isValid;
	}
}