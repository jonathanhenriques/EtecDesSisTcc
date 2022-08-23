package com.etec.tcc.sprint_quiz.repository;

import com.etec.tcc.sprint_quiz.model.Prova;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvaRepository extends JpaRepository<Prova, Long> {

    List<Prova> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

    List<Prova> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);

    List<Prova> findAllByUsuarioId(Long id);




}
