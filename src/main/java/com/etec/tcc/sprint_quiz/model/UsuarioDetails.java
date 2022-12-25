//package com.etec.tcc.sprint_quiz.model;
//
//import java.util.Collection;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
///**
// * Classe criada para fornecer um UserDetails quando necessário
// * 
// * @author hsjon
// * date 20/12/2022
// */
//@Getter
//@Setter
////@AllArgsConstructor
//@NoArgsConstructor
//public class UsuarioDetails implements UserDetails {
//
//	/**
//	 * para implementação do pattern Decorator Foram copiados todos os métodos dessa
//	 * classe para a classe Usuario
//	 */
////	@Autowired
//	private Usuario usuario;
//	
//	public UsuarioDetails(Usuario usuario) {
//		this.usuario = usuario;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return usuario.getAuthorities();
////		return usuario.getRoles();
//	}
//
//	@Override
//	public String getPassword() {
//		return this.usuario.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		return this.usuario.getUsername();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return this.usuario.getAtivo() == 1;
//	}
//
//}
