package com.etec.tcc.sprint_quiz.service;

import java.util.List;

import com.etec.tcc.sprint_quiz.model.CategoriaProva;

public interface CategoriaProvaService{



     CategoriaProva getById(Long id);


     List<CategoriaProva> getAll();
     

     List<CategoriaProva> getAllByTitulo(String titulo);

     CategoriaProva post(CategoriaProva categoria);


     CategoriaProva put(CategoriaProva categoria);


//     CategoriaProva patchCategoriaProvaTitulo(@RequestBody CategoriaProva categoria);


     void delete(Long id);


}
