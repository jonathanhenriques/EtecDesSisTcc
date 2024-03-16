package com.etec.tcc.sprint_quiz.model.dto;

import com.etec.tcc.sprint_quiz.model.Alternativa;

import java.util.List;
import java.util.Set;

public record QuestaoComAlternativaDTO(Long id, List<Alternativa> alternativas) {
}
