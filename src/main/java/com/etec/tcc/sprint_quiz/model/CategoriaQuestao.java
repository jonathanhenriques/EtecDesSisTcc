package com.etec.tcc.sprint_quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


/**
 * Representa a categoria das questões
 * 
 * @author hsjon
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_categoria_questao")
public class CategoriaQuestao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "titulo {campo.texto.notBlank.obrigatorio}")
    @Size(min = 1, max = 50, message = "titulo {campo.texto.sizeMax} 50")
//    @Schema(name = "Questões de Matemática")
    private String titulo;


    @NotBlank(message = "descricao {campo.texto.notBlank.obrigatorio}")
    @Size(min = 1, max = 1000, message = "No máximo 1000 caracteres")
//    @Schema(name = "Questões de Matemática")
    private String descricao;


    @OneToMany(mappedBy = "categoria")
    @JsonIgnoreProperties(value = {"categoria"})
    private List<Questao> questoes;

}
