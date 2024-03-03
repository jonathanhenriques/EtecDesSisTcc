package com.etec.tcc.sprint_quiz.repository;

import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.model.Questao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long> {

    List<Questao> findAllByTextoContainingIgnoreCase(@Param("texto") String texto);

    List<Questao> findAllByInstituicaoContainingIgnoreCase(@Param("instituicao") String instituicao);

    List<Questao> findAllByAno(LocalDate ano);

    List<Questao> findAllByAnoBetween(LocalDate anoInicial, LocalDate anoFinal);

    List<Questao> findAllByAnoBefore(LocalDate ano);

    List<Questao> findAllByCriadorId(Long criadorId);

    @Query("From Questao q JOIN FETCH q.alternativas JOIN FETCH q.categoria JOIN FETCH q.criador where q.id = :id")
    Optional<Questao> findAllFetch(@Param("id") Long id);



}
