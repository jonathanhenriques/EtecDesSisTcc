package com.etec.tcc.sprint_quiz.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.etec.tcc.sprint_quiz.model.CategoriaProva;

public interface CategoriaProvaService {



     CategoriaProva getById(@PathVariable Long id);


     List<CategoriaProva> getAll();


     CategoriaProva postCategoriaProva(@Valid @RequestBody CategoriaProva categoria);


     CategoriaProva putCategoriaProva(@Valid @RequestBody CategoriaProva categoria);


//     CategoriaProva patchCategoriaProvaTitulo(@RequestBody CategoriaProva categoria);


     void deletetaCategoriaProva(@PathVariable Long id);


}
