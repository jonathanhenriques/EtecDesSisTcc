package com.etec.tcc.sprint_quiz.model.dto;

import java.util.List;

public record CategoriaProvaComProvasDTO(Long id, String titulo, String descricao, List<ProvaDTO> provas) {
}
