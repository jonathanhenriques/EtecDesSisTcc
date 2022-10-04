package com.etec.tcc.sprint_quiz.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.etec.tcc.sprint_quiz.model.CategoriaProva;

public interface CategoriaProvaService extends IDAO<CategoriaProva>{



     CategoriaProva getById(@PathVariable Long id);


     List<CategoriaProva> getAll();


     CategoriaProva post(@Valid @RequestBody CategoriaProva categoria);


     CategoriaProva put(@Valid @RequestBody CategoriaProva categoria);


//     CategoriaProva patchCategoriaProvaTitulo(@RequestBody CategoriaProva categoria);


     void delete(@PathVariable Long id);


}
