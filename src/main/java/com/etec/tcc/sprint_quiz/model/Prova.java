package com.etec.tcc.sprint_quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_prova")
public class Prova {

    //    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "O atributo nome não pode ser nullo nem vazio!")
    @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
    private String nome;

    @NotBlank(message = "O atributo descricao não pode ser nullo nem vazio!")
    @Size(max = 400, message = "A descricao deve ter no máximo 400 caracteres")
    private String descricao;

//    @NotBlank(message = "O atributo duracao não pode ser nullo")
    private Integer duracao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties("provas")
    private Usuario usuario;


    @OneToMany(mappedBy = "prova")
    @JsonIgnoreProperties("prova")
    private List<QuestaoProva> questoes;

    private String instituicao;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonIgnoreProperties("provas")
    private CategoriaProva categoria;

}
