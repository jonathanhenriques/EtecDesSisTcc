package com.etec.tcc.sprint_quiz.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoriaProvaComProvasDTO {
    Long id;
    String titulo;
    String descricao;
    List<ProvaDTO> provas;

}
