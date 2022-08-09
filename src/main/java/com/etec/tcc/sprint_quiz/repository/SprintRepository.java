package com.etec.tcc.sprint_quiz.repository;

import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintRepository extends JpaRepository<Prova,Long> {

    List<Prova> findByUsuario(Usuario usuario);

    List<Prova> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

    List<Prova> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);

    List<Prova> findAllByDuracaoGreaterThan(Integer duracao);

    List<Prova> findAllByDuracaoLessThan(Integer duracao);








}
