package com.etec.tcc.sprint_quiz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioLogin2 {


    private Long id;
    private String nome;
    private String email; //campo de login
    private String senha;
    private String foto;
    private String tipo;


}
