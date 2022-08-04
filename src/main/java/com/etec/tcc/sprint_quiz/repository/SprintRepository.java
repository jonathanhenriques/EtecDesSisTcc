package com.etec.tcc.sprint_quiz.repository;

import com.etec.tcc.sprint_quiz.model.Sprint;
import com.etec.tcc.sprint_quiz.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintRepository extends JpaRepository<Sprint,Long> {

    List<Sprint> findByUsuario(Usuario usuario);

    List<Sprint> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

    List<Sprint> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);

    List<Sprint> findAllByDuracaoGreaterThan(Integer duracao);

    List<Sprint> findAllByDuracaoLessThan(Integer duracao);








}
