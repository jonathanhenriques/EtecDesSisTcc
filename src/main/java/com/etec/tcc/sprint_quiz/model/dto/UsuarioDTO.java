package com.etec.tcc.sprint_quiz.model.dto;

import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String username;
//    private String tipo;
    private List<QuestaoDTO> questoes;
    private List<ProvaDTO> provas;

//    public UsuarioDTO(Usuario usuario) {
//        this.id = usuario.getId();
//        this.nome = usuario.getNome();
////        this.email = usuario.getEmail();
////        this.tipo = usuario.getTipo();
//        this.provas = usuario.getProvas();
//    }



}
