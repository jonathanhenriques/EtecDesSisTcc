package com.etec.tcc.sprint_quiz.repository;

import java.util.List;
import java.util.Optional;

import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.model.dto.UsuarioDTO;
import com.etec.tcc.sprint_quiz.model.dto.UsuarioSimplificadoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.etec.tcc.sprint_quiz.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


	Optional<Usuario> findByUsername(String username);

	List<Usuario> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

	Optional<Usuario> findByNome(@Param("nome") String nome);
	
	@Query("SELECT u FROM Usuario u JOIN FETCH u.roles WHERE u.username = :username")
	Optional<Usuario> findByUsernameFetchRoles(@Param("username") String username);


	@Query("SELECT u FROM Usuario u JOIN FETCH u.questoes WHERE u.id = :id")
	Optional<Usuario> findByIdWithQuestoes(@Param("id") Long id);

	@Query("SELECT u FROM Usuario u JOIN FETCH u.provas WHERE u.id = :id")
	Optional<Usuario> findByIdWithProvas(@Param("id") Long id);

//	@Query("SELECT u FROM Usuario u JOIN FETCH u.questoes JOIN FETCH u.provas where u.id = :id")
//	Optional<UsuarioDTO> findByIdFetchAll(@Param("id") Long id);

//	@Query("FROM Prova p JOIN FETCH p.usuario JOIN FETCH p.questoes JOIN FETCH p.categoria where p.id = :id ")
//	Optional<Prova> findAllFetch(@Param("id") Long id);


}
