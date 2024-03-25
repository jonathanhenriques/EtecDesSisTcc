package com.etec.tcc.sprint_quiz.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;
@Setter
@Getter
@RequiredArgsConstructor
public class ProvaResponse{
        Long id;
        String nome;
        String descricao;
        Integer duracao;
        String instituicao;
        UsuarioSimplificadoDTO usuario;
        @JsonIgnoreProperties({"criadorId","idCategoriaQuestao","resposta","alternativas","dificuldade","imagem","texto","instituicao"})
        List<QuestaoDTO> questoes;
        Long categoriaProva;
}
