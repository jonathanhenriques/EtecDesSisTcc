package com.etec.tcc.sprint_quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "categoria")
    @NotBlank(message = "O atributo titulo não pode ser nulo nem vazio!")
    @Size(max = 50, message = "No máximo 50 caracteres")
    private String titulo;

    @NotNull(message = "O atributo descricao não deve ser nullo!")
    @Size(max = 1000, message = "No máximo 1000 caracteres")
    private String descricao;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnoreProperties("categoria")
    private List<Questao> questoes;
}
