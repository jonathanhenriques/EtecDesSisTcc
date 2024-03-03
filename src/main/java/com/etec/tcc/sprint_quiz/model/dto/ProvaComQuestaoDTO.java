package com.etec.tcc.sprint_quiz.model.dto;

import com.etec.tcc.sprint_quiz.model.Questao;

import java.util.Set;

public record ProvaComQuestaoDTO(Long id, Set<Questao> questoes) {
}
