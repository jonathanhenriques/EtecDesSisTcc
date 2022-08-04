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
@Table(name = "tb_item_sprint")
public class ItemSprint {

    //    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "questoes_id")
    //    @JsonIgnoreProperties("grupo_questoes")
    private Questao questoes;

    @ManyToOne
    @JoinColumn(name = "sprint_id")
    //    @JsonIgnoreProperties("sprint_id")
    private Sprint sprint;


}
