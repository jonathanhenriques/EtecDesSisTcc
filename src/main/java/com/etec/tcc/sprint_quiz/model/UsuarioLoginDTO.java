package com.etec.tcc.sprint_quiz.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioLoginDTO {


//    private Long id;
//    private String nome;
    private String username; //campo de login
    private String password;
//    private String foto;
//    private String roles;


}
