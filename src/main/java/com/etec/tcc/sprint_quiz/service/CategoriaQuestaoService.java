package com.etec.tcc.sprint_quiz.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.etec.tcc.sprint_quiz.model.CategoriaQuestao;

public interface CategoriaQuestaoService extends IDAO<CategoriaQuestao>{

//    CategoriaQuestao getById(@PathVariable Long id);
    
//    List<CategoriaQuestao> getAll();

    List<CategoriaQuestao> getByTitutlo(@PathVariable("titulo") String titulo);

    List<CategoriaQuestao> getByDescricao(@PathVariable("descricao") String descricao);

//    CategoriaQuestao postCategoriaQuestao(@Valid @RequestBody CategoriaQuestao categoria);

//    CategoriaQuestao putCategoriaQuestao(@Valid @RequestBody CategoriaQuestao categoria);

//    void deleteCategoriaQuestao(@PathVariable Long id);

	
}
