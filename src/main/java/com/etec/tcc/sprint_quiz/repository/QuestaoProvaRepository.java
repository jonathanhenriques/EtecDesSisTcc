package com.etec.tcc.sprint_quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etec.tcc.sprint_quiz.model.QuestaoProva;

@Repository
public interface QuestaoProvaRepository extends JpaRepository<QuestaoProva, Long> {

	List<QuestaoProva> findAllByProvaId(Long id);

}
