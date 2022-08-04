package com.etec.tcc.sprint_quiz.repository;

import com.etec.tcc.sprint_quiz.model.ItemSprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemSprintRepository extends JpaRepository<ItemSprint, Long> {


}
