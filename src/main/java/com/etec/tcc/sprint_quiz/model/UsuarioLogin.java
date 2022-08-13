package com.etec.tcc.sprint_quiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLogin {

    private Long id;
    private String nome;
    private String senha;
    private String email;
    private String foto;
    private String tipo;
    private String token;


}
