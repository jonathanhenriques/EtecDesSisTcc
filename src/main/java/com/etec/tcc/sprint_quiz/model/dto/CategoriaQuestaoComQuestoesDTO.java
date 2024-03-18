package com.etec.tcc.sprint_quiz.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.OneToMany;
import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
public class CategoriaQuestaoComQuestoesDTO {


    private Long id;

//    @Schema(name = "Questões de Matemática")
    private String titulo;


//    @Schema(name = "Questões de Matemática")
    private String descricao;


    @OneToMany(mappedBy = "categoria")
    @JsonIgnoreProperties(value = {"categoria", "criador", "provas"})
    private List<QuestaoSemAlternativasDTO> questoes;




}
