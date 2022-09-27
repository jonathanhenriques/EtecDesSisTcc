package com.etec.tcc.sprint_quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import io.swagger.v3.oas.annotations.media.Schema;
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
@Table( name = "tb_categoria_prova")
public class CategoriaProva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo titulo não pode ser vazio nem nulo!")
    @Size(max = 400, message = "O atributo titulo deve ter no máximo 400 caracteres")
//    @Schema(name = "Provas de vestibulares")
    private String titulo;

    @Size(max = 1000 ,message = "O atributo descricao deve ter no máximo 1000 caracteres")
//    @Schema(name = "Vestibulares públicos")
    private String descricao;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnoreProperties("categoria")
    private List<Prova> provas;


}
