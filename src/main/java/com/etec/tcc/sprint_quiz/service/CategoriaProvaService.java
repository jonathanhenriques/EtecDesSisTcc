package com.etec.tcc.sprint_quiz.service;

import java.util.List;

import com.etec.tcc.sprint_quiz.model.dto.CategoriaProvaComProvasDTO;
import com.etec.tcc.sprint_quiz.model.dto.CategoriaProvaDTO;

public interface CategoriaProvaService{

     CategoriaProvaComProvasDTO getById(Long id);

     List<CategoriaProvaDTO> findAllDTO();
     
     CategoriaProvaDTO post(CategoriaProvaDTO categoria);

     CategoriaProvaComProvasDTO put(CategoriaProvaDTO categoria);

     void delete(Long id);

     CategoriaProvaComProvasDTO getByIdComProvas(Long id);

}
