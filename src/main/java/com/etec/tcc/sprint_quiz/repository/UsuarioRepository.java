package com.etec.tcc.sprint_quiz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.etec.tcc.sprint_quiz.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


	Optional<Usuario> findByUsername(String username);

	List<Usuario> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

	Optional<Usuario> findByNome(@Param("nome") String nome);
}
