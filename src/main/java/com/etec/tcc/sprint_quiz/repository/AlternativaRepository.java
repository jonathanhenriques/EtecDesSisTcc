package com.etec.tcc.sprint_quiz.repository;

import com.etec.tcc.sprint_quiz.model.Alternativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlternativaRepository extends JpaRepository<Alternativa, Long> {

    List<Alternativa> findAllByTextoContainingIgnoreCase(@Param("texto") String texto);

}
