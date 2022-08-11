package com.etec.tcc.sprint_quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo nome não pode ser nullo nem vazio!")
    @Size(min = 3, max = 75, message = "O nome deve ter no mínimo 3 caracteres")
    private String nome;


    @Schema(example = "email@email.com.br")
    @NotBlank(message = "O atributo email não pode ser nullo nem vazio")
    @Email(message = "Deve ser um email válido (email@email.com)")
    private String email;

    @NotBlank(message = "O atributo senha não pode ser nullo nem vazio!")
    @Size(min = 8, max = 30, message = "A senha deve ter no mínimo 8 caracteres")
    private String senha;

//    @Schema(name = "link da foto")
    private String foto;

    @Schema(example = "admin / usuario")
    private String tipo;

    @OneToMany(mappedBy = "criador")
    @JsonIgnoreProperties("criador")
    private List<Questao> questoes;

    @OneToMany(mappedBy = "usuario")
    private List<Prova> provas;
}
