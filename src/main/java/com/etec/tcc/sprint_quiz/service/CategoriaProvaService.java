package com.etec.tcc.sprint_quiz.service;

import java.util.List;

import com.etec.tcc.sprint_quiz.model.CategoriaProva;
import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.model.dto.CategoriaProvaComProvasDTO;
import com.etec.tcc.sprint_quiz.model.dto.CategoriaProvaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriaProvaService{



     CategoriaProva getById(Long id);


//     List<CategoriaProva> getAll();

     List<CategoriaProvaDTO> findAllDTO();
     

     Page<CategoriaProva> getAllByTitulo(String titulo, Pageable pageable);

     CategoriaProva post(CategoriaProva categoria);


     CategoriaProva put(CategoriaProva categoria);


//     CategoriaProva patchCategoriaProvaTitulo(@RequestBody CategoriaProva categoria);


     void delete(Long id);


     CategoriaProvaComProvasDTO getByIdComProvas(Long id);

}
