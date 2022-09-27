package com.etec.tcc.sprint_quiz.model;

//import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_alternativa")
public class Alternativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "O atributo opcao_1 não pode ser nullo")
    @Size(max = 1000)
//    @Schema(name = "Onde está wally?")
    private String texto;

//    @Schema(name = "https://imgur.com/9q3tXhG")
    private String foto;

    @ManyToOne
//    @JoinColumn(name = "questao_id")
//    @JsonIgnoreProperties("alternativas")
    @JsonIgnoreProperties({"resposta", "alternativas"})
    private Questao questao;


}
