package com.etec.tcc.sprint_quiz.repository;

import com.etec.tcc.sprint_quiz.model.CategoriaProva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Repository
public interface CategoriaProvaRepository extends JpaRepository<CategoriaProva,Long> {

    Optional<CategoriaProva> findByTitulo(@Param("titulo") String titulo);
}
