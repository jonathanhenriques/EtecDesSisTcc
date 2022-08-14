package com.etec.tcc.sprint_quiz.repository;

import com.etec.tcc.sprint_quiz.model.Questao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long> {

    List<Questao> findAllByTextoContainingIgnoreCase(@Param("texto") String texto);

    List<Questao> findAllByInstituicaoContainingIgnoreCase(@Param("instituicao") String instituicao);

    List<Questao> findAllByAno(LocalDate ano);

    List<Questao> findAllByAnoBetween(LocalDate anoInicial, LocalDate anoFinal);

    List<Questao> findAllByAnoBefore(LocalDate ano);

}
