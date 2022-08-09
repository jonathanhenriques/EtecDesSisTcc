package com.etec.tcc.sprint_quiz.model.dto;

import com.etec.tcc.sprint_quiz.model.QuestaoProva;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestaoProvaDTO {

    private Long id;
    private Long questao;
    private Long prova;

    public QuestaoProvaDTO(QuestaoProva questao) {
        this.id = questao.getId();
        this.questao = questao.getQuestao().getId();
//        this.prova = questao.getProva().getId();
    }

    public static List<QuestaoProvaDTO> converterListaParaDto(List<QuestaoProva> lista){
        return lista
                .stream()
                .map(QuestaoProvaDTO::new).collect(Collectors.toList());
    }



}
