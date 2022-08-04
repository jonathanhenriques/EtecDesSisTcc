package com.etec.tcc.sprint_quiz.repository;

import com.etec.tcc.sprint_quiz.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
}
