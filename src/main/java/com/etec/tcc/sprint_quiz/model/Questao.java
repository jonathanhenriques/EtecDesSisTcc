package com.etec.tcc.sprint_quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_questao")
public class Questao {

    //    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @NotNull(message = "O atributo instituicao não pode ser nullo")
    private String instituicao;

    //    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @CreationTimestamp
//    @UpdateTimestamp
    private LocalDate ano;

    private String imagem;

    @NotBlank(message = "O atributo texto não pode ser nullo nem vazio!")
    @Size(min = 1, max = 1000)
    private String texto;

    @OneToMany(mappedBy = "questao", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = {"questao"}, allowSetters = true)
    private List<Alternativa> alternativas;


    //    @NotBlank(message = "O atributo resposta não pode ser nullo nem vazio!")
//    @Size(max = 1)
    @ManyToOne()
    @JoinColumn(name = "resposta_alternativa_id")
    private Alternativa resposta;

//    private String resposta;


    @ManyToOne
    @JoinColumn(name = "categoria_id")
//    @JsonIgnoreProperties({"descritivo", "questoes"})
    @JsonIgnoreProperties("questoes")
    private CategoriaQuestao categoria;


    @ManyToOne
    @JoinColumn(name = "criador_id")
//    @JsonIgnoreProperties({"email", "senha", "foto", "tipo", "provas", "questoes"})
    @JsonIgnoreProperties("questoes")
    private Usuario criador;


}
