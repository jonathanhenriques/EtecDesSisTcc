package com.etec.tcc.sprint_quiz.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.etec.tcc.sprint_quiz.model.CategoriaProva;

public interface CategoriaProvaService extends IDAO<CategoriaProva>{



     CategoriaProva getById(Long id);


     List<CategoriaProva> getAll();
     

     List<CategoriaProva> getAllByTitulo(String titulo);

     CategoriaProva post(CategoriaProva categoria);


     CategoriaProva put(CategoriaProva categoria);


//     CategoriaProva patchCategoriaProvaTitulo(@RequestBody CategoriaProva categoria);


     void delete(Long id);


}
