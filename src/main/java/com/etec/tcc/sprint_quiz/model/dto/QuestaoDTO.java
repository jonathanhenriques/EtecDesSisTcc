package com.etec.tcc.sprint_quiz.model.dto;

import com.etec.tcc.sprint_quiz.model.CategoriaQuestao;
import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestaoDTO {

    private Long id;
    private String instituicao;
    private String ano;
    private String texto;
    private String opcao_1;
    private String opcao_2;
    private String opcao_3;
    private String opcao_4;
    private String opcao_5;
    private int resposta;
    private Long categoria;
    private Long criador;

    public QuestaoDTO(@RequestBody Questao questao){
        this.id = questao.getId();
        this.instituicao = questao.getInstituicao();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String lds = questao.getAno().format(formatter);
        this.ano = lds;

        this.texto = questao.getTexto();
        this.opcao_1 = questao.getOpcao_1();
        this.opcao_2 = questao.getOpcao_2();
        this.opcao_3 = questao.getOpcao_3();
        this.opcao_4 = questao.getOpcao_4();
        this.opcao_5 = questao.getOpcao_5();
        this.resposta = questao.getResposta();

        this.categoria = questao.getCategoria().getId();
        this.criador = questao.getCriador().getId();

    }

}
