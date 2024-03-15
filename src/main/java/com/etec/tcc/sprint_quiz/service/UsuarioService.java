package com.etec.tcc.sprint_quiz.service;

import java.util.Optional;

import javax.validation.Valid;

import com.etec.tcc.sprint_quiz.model.dto.UsuarioDTO;
import com.etec.tcc.sprint_quiz.model.dto.UsuarioSimplificadoDTO;
import org.springframework.web.bind.annotation.RequestBody;

import com.etec.tcc.sprint_quiz.model.Role;
import com.etec.tcc.sprint_quiz.model.Usuario;
import com.etec.tcc.sprint_quiz.model.dto.RoleDTO;

public interface UsuarioService{


	 Optional<Usuario> cadastrarUsuario(Usuario usuario);
	 
	 Optional<Role> cadastrarRole(Role role); //verificar se devo passar DTO ou a classe 
	 
	 void addRoleUsuario(String username, String cargo);


	Usuario findById(Long id);
	Optional<Usuario> findByIdWithQuestoes(Long id);

	Optional<Usuario> findByIdWithProvas( Long id);

//	UsuarioSimplificadoDTO converteUsuarioParaUsuarioSimplificadoDTO(Usuario usuario);

//	UsuarioDTO converteUsuarioParaUsuarioDTO(Usuario usuario);

//	Optional<UsuarioDTO> findByIdWithQuestoesEProvas(Long id);

}
