package com.etec.tcc.sprint_quiz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_alternativa")
public class Alternativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "O atributo texto não pode ser nullo")
    @Size(max = 1000)
//    @Schema(name = "Onde está wally?")
    private String texto;

//    @Schema(name = "https://imgur.com/9q3tXhG")
    private String foto;

    @ManyToOne
//    @OneToOne
    @JoinColumn(name = "questao_id")
//    @Cascade(CascadeType.SAVE_UPDATE)
////    @JsonIgnoreProperties("alternativas")
    @JsonIgnoreProperties(value = {"resposta", "alternativas"}, allowSetters = true)
    private Questao questao;
    
    


}
