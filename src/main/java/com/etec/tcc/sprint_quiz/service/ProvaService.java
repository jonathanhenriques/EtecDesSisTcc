package com.etec.tcc.sprint_quiz.service;

import java.util.List;

import com.etec.tcc.sprint_quiz.model.dto.ProvaComQuestaoDTO;
import com.etec.tcc.sprint_quiz.model.dto.ProvaDTO;
import com.etec.tcc.sprint_quiz.model.dto.ProvaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import com.etec.tcc.sprint_quiz.model.Prova;

public interface ProvaService{

    Page<ProvaResponse> getAll(Pageable pageable);

    Prova getById(@PathVariable Long id);

    List<Prova> getAllByNome(String nome);

    List<Prova> getAllByDescricao(String descricao);

    List<Prova> getByCriadorId(Long id);

    List<Prova> getAllByCategoriaProva(String categoria);

    List<Prova> findAllByCategoriaId(Long id);

    ProvaDTO post( ProvaDTO prova);

    Prova put(Prova prova);

    void delete(Long id);
    
    
}
