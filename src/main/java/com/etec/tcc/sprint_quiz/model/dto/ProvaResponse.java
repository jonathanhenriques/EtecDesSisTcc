package com.etec.tcc.sprint_quiz.model.dto;

import com.etec.tcc.sprint_quiz.model.Questao;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

//@NoArgsConstructor(force = true)
//@AllArgsConstructor
public record ProvaResponse(Long id, String nome, String descricao, Integer duracao, String instituicao, UsuarioSimplificadoDTO usuario, Set<QuestaoDTO> questao, Long categoriaProva ) {
}
