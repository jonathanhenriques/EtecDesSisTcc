package com.etec.tcc.sprint_quiz.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * classe representando os usuarios no Banco
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // permite construir obj com mais facilidade
@Entity
@Table(name = "TB_USUARIO")
public class Usuario implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "usuario_id")
	private Long id;

	@NotBlank(message = "O atributo nome não pode ser nullo nem vazio!")
	@Size(min = 3, max = 75, message = "O nome deve ter no mínimo 3 caracteres")
	private String nome;

// necessario na antiga implementacao de security pois login é feito com email
//    @Schema(example = "email@email.com.br")
//    @NotBlank(message = "O atributo email não pode ser nullo nem vazio")
//    @Email(message = "Deve ser um email válido (email@email.com)")
//    private String usuario;

	@Schema(example = "email@email.com.br")
	@NotBlank(message = "{campo.email.obrigatorio}")
	@Column(unique = true)
	@Email(message = "Deve ser um email válido (email@email.com)")
	private String username; // campo de login

	@NotBlank(message = "O atributo senha não pode ser nullo nem vazio!")
	@Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
	private String password;

//    @Schema(name = "link da foto")
	private String foto;

//	private boolean enable;

//	@Schema(example = "admin / user")
//	private String tipo;
	@ManyToMany
	@JoinTable(name = "TB_USUARIO_ROLE", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<RolesModel> roles;

	@OneToMany(mappedBy = "criador")
	@JsonIgnoreProperties({ "instituicao", "ano", "texto", "opcao_1", "opcao_2", "opcao_3", "opcao_4", "opcao_5",
			"resposta", "categoria", "criador" })
	private List<Questao> questoes;

	@OneToMany(mappedBy = "usuario")
	@JsonIgnoreProperties(value = { "questoes", "descricao", "duracao", "usuario", "instituicao",
			"categoria" }, allowSetters = true)
	private List<Prova> provas;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
//		return this.enable;
		return true;
	}

}
