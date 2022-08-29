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
//        @JsonIgnoreProperties({"grupo_questoes", "instituicao",
//                "ano", "texto", "opcao_1", "opcao_2", "opcao_3" ,"opcao_4" ,"opcao_5",
//                "resposta", "categoria", "criador"})
    private Questao questao;

    @ManyToOne
    @JoinColumn(name = "prova_id")
//    @JsonIgnoreProperties({"questoes", "descricao", "duracao", "usuario", "instituicao", "categoria"})
//    @JsonIgnoreProperties("");
    private Prova prova;


}
