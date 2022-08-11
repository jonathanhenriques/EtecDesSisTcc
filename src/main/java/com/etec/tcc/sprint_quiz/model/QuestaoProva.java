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
    //    @JsonIgnoreProperties("grupo_questoes")
    private Questao questao;

    @ManyToOne
    @JoinColumn(name = "prova_id")
    @JsonIgnoreProperties("questoes")
    private Prova prova;


}
