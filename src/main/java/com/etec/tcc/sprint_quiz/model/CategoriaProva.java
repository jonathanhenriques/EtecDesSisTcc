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

    @NotBlank(message = "titulo {campo.texto.notBlank.obrigatorio}")
    @Size(max = 400, message = "titulo {campo.texto.sizeMax} 400")
//    @Schema(name = "Provas de vestibulares")
    private String titulo;

    @Size(max = 1000 ,message = "descricao {campo.texto.sizeMax} 1000")
//    @Schema(name = "Vestibulares públicos")
    private String descricao;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @OneToMany(mappedBy = "categoria")
    @JsonIgnoreProperties(value = "categoria", allowSetters = true)
    private List<Prova> provas;


}
