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

/**
 * Representa prova no banco
 * 
 * @author hsjon
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_prova")
public class Prova {

    //    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "nome {campo.texto.notBlank.obrigatorio}")
    @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
//    @Schema(name = "Vestibulinho etec 1° SEM/2020")
    private String nome;

    @NotBlank(message = "descricao {campo.texto.notBlank.obrigatorio}")
    @Size(max = 400, message = "descricao {campo.texto.sizeMax} 400")
//    @Schema(name = "etec - Vestibulinho 1° SEM/2020")
    private String descricao;

//    @NotNull(message = "O atributo duracao não pode ser nullo")
    private Integer duracao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties(value = {"email", "senha","foto", "tipo", "provas" ,"questoes", "usuario"}, allowSetters = true)
    private Usuario usuario;


    @OneToMany(mappedBy = "prova")
    @JsonIgnoreProperties("prova")
    private List<QuestaoProva> questoes;

//    @Schema(name = "Etec JK")
    private String instituicao;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
//    @JsonIgnoreProperties({"descritivo", "provas"})
    @JsonIgnoreProperties(value = "provas", allowSetters = true, allowGetters = true)
    private CategoriaProva categoria;


}
