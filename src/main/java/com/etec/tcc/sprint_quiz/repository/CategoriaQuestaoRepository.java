package com.etec.tcc.sprint_quiz.repository;

import com.etec.tcc.sprint_quiz.model.CategoriaQuestao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaQuestaoRepository extends JpaRepository<CategoriaQuestao, Long> {

//    Optional<CategoriaQuestao> findByTitulo(@Param("titulo") String titulo);
	
	List<CategoriaQuestao> getAll();

    List<CategoriaQuestao> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);

    List<CategoriaQuestao> findByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);
}
