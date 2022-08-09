package com.etec.tcc.sprint_quiz.repository;

import com.etec.tcc.sprint_quiz.model.Prova;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvaRepository extends JpaRepository<Prova, Long> {

    List<Prova> findAllByNome(@Param("nome") String nome);

    List<Prova> findAllByDescricao(@Param("descricao") String descricao);




}
