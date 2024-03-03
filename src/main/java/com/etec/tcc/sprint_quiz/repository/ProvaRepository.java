package com.etec.tcc.sprint_quiz.repository;

import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProvaRepository extends JpaRepository<Prova, Long> {

    List<Prova> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

    List<Prova> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);

    List<Prova> findAllByUsuarioId(Long id);

    @Query("FROM Prova p JOIN FETCH p.usuario JOIN FETCH p.questoes JOIN FETCH p.categoria where p.id = :id ")
    Optional<Prova> findAllFetch(@Param("id") Long id);


}
