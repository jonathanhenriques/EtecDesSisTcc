package com.etec.tcc.sprint_quiz.repository;

import com.etec.tcc.sprint_quiz.model.QuestaoProva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestaoProvaRepository extends JpaRepository<QuestaoProva, Long> {


}
