package com.etec.tcc.sprint_quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.etec.tcc.sprint_quiz.model.CategoriaQuestao;

@Repository
public interface CategoriaQuestaoRepository extends JpaRepository<CategoriaQuestao, Long> {

//    Optional<CategoriaQuestao> findByTitulo(@Param("titulo") String titulo);
	

    List<CategoriaQuestao> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);

    List<CategoriaQuestao> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);
}
