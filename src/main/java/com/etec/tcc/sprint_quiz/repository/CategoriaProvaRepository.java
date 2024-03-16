package com.etec.tcc.sprint_quiz.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.etec.tcc.sprint_quiz.model.CategoriaProva;

@Repository
public interface CategoriaProvaRepository extends JpaRepository<CategoriaProva,Long> {

    Page<CategoriaProva> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo, Pageable pageable);
}
