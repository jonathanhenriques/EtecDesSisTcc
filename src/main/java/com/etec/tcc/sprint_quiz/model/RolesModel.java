package com.etec.tcc.sprint_quiz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

import com.etec.tcc.sprint_quiz.enums.RoleTipo;

import lombok.Getter;
import lombok.Setter;

@Entity
(name = "TB_USUARIO_ROLES")
@Getter
@Setter
public class RolesModel implements GrantedAuthority{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Long id;
	@Enumerated(EnumType.STRING) //define que no lugar do enum, vai ser salvo uma string equivalente no banco
	@Column(nullable = false, unique = true)
	private RoleTipo roleTipo;
	@Override
	
	
	public String getAuthority() {
		return this.roleTipo.toString();
	}
	

}
