package com.etec.tcc.sprint_quiz.model.dto;

import com.etec.tcc.sprint_quiz.enums.DificuldadeQuestao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestaoSemAlternativasDTO {

    private Long id;
    private String instituicao;
    private String imagem;
    @NotBlank(message = "texto {campo.texto.notBlank.obrigatorio}")
    @Size(min = 1, max = 1000, message = "texto {campo.texto.sizeMax} 1000")
    private String texto;
    private DificuldadeQuestao dificuldade;
    private AlternativaDTO resposta;
    private Long idCategoriaQuestao;
    private Long criadorId;
    @Override
    public String toString() {
        return "QuestaoDTO [id=" + id + ", instituicao=" + instituicao + ", imagem=" + imagem + ", texto=" + texto
                + ", dificuldade=" + dificuldade +  ", resposta=" + resposta
                + ", idCategoriaQuestao=" + idCategoriaQuestao + ", criadorId=" + criadorId + "]";
    }




}
