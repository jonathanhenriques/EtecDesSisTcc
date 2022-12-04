package com.etec.tcc.sprint_quiz.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //permite construir obj com mais facilidade
@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo nome não pode ser nullo nem vazio!")
    @Size(min = 3, max = 75, message = "O nome deve ter no mínimo 3 caracteres")
    private String nome;

// necessario na antiga implementacao de security pois login é feito com email
//    @Schema(example = "email@email.com.br")
//    @NotBlank(message = "O atributo email não pode ser nullo nem vazio")
//    @Email(message = "Deve ser um email válido (email@email.com)")
//    private String usuario;
    
    @Schema(example = "email@email.com.br")
    @NotBlank(message = "{campo.email.obrigatorio}")
    @Email(message = "Deve ser um email válido (email@email.com)")
    private String email; //campo de login

    @NotBlank(message = "O atributo senha não pode ser nullo nem vazio!")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String senha;

//    @Schema(name = "link da foto")
    private String foto;

    @Schema(example = "admin / user")
    private String tipo;

    @OneToMany(mappedBy = "criador")
    @JsonIgnoreProperties({"instituicao",
            "ano", "texto", "opcao_1", "opcao_2", "opcao_3" ,"opcao_4" ,"opcao_5",
            "resposta", "categoria", "criador"})
    private List<Questao> questoes;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnoreProperties(value = {"questoes", "descricao", "duracao", "usuario", "instituicao", "categoria"}, allowSetters = true)
    private List<Prova> provas;


}
