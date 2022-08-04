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
@Table(name = "tb_sprint")
public class Sprint {

    //    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo nome não pode ser nullo nem vazio!")
    @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
    private String nome;

    @NotBlank(message = "O atributo descricao não pode ser nullo nem vazio!")
    @Size(max = 400, message = "A descricao deve ter no máximo 400 caracteres")
    private String descricao;

    @NotBlank(message = "O atributo duracao não pode ser nullo")
    private Integer duracao;

    @ManyToOne
    private Usuario usuario;


    @OneToMany(mappedBy = "sprint")
//    @JsonIgnoreProperties("sprint")
    private List<ItemSprint> itens;

}
