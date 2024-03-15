package com.etec.tcc.sprint_quiz.model.dto;

import com.etec.tcc.sprint_quiz.enums.DificuldadeQuestao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public record QuestaoDTOComProvas(Long id,
                                  String instituicao,
                                  String imagem,
                                  String texto,
                                  DificuldadeQuestao dificuldade,
                                  Set<AlternativaDTO> alternativas,
                                  AlternativaDTO resposta,
                                  Long CategoriaQuestaoId,
                                  Long criadorId,
                                  Set<ProvaDTO> provas) {
}
