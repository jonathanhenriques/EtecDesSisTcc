package com.etec.tcc.sprint_quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_questao_prova")
public class QuestaoProva {

    //    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "questao_id")
//    @JsonIgnoreProperties({"ano", "texto", "resposta", "categoria", "criador", "alternativas", "imagem"})
//    @JsonIgnoreProperties("questao")
    private Questao questao;

    @ManyToOne
    @JoinColumn(name = "prova_id")
    @JsonIgnoreProperties({"questoes", "descricao", "duracao", "usuario", "instituicao", "categoria"})
//    @JsonIgnoreProperties("prova")
    private Prova prova;


}
