package com.etec.tcc.sprint_quiz.service;

import java.util.List;

import javax.validation.Valid;

import com.etec.tcc.sprint_quiz.model.dto.CategoriaQuestaoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.etec.tcc.sprint_quiz.model.CategoriaQuestao;

public interface CategoriaQuestaoService{

    CategoriaQuestao getById(Long id);
    
    Page<CategoriaQuestaoDTO> getAll(Pageable pageable);

    List<CategoriaQuestao> getByTitutlo(@PathVariable("titulo") String titulo);

    List<CategoriaQuestao> getAllByDescricao(@PathVariable("descricao") String descricao);

    CategoriaQuestao post(@Valid @RequestBody CategoriaQuestao categoria);

    CategoriaQuestao put(@Valid @RequestBody CategoriaQuestao categoria);

    void delete(@PathVariable Long id);

	
}
