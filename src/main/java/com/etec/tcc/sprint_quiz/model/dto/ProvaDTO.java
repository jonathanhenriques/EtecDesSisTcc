package com.etec.tcc.sprint_quiz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvaDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Integer duracao;
    private Long idUsuario;
    private String instituicao;
    private Long idCategoria;

}
