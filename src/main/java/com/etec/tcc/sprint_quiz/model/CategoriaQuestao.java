package com.etec.tcc.sprint_quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_categoria_questao")
public class CategoriaQuestao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "titulo")
    @NotBlank(message = "O atributo titulo não pode ser nulo nem vazio!")
    @Size(max = 50, message = "No máximo 50 caracteres")
//    @Schema(name = "Questões de Matemática")
    private String titulo;


    //    @Column(name = "titulo")
    @NotBlank(message = "O atributo descricao não deve ser nullo!")
    @Size(max = 1000, message = "No máximo 1000 caracteres")
//    @Schema(name = "Questões de Matemática")
    private String descritivos;


//    @NotNull(message = "O atributo descricao não deve ser nullo!")
//    @Size(max = 1000, message = "No máximo 1000 caracteres")
////    @Schema(name = "Questões de Cálculo 1")
//    private String descricao;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnoreProperties("categoria")
    private List<Questao> questoes;

}
