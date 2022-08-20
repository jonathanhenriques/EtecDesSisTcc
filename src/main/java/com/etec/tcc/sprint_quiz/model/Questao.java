package com.etec.tcc.sprint_quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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

    @NotNull(message = "O atributo instituicao não pode ser nullo")
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

    @OneToMany
    @JsonIgnoreProperties("questao")
    private List<Alternativa> alternativas;


//    @NotBlank(message = "O atributo resposta não pode ser nullo nem vazio!")
//    @Size(max = 1)
    private String resposta;


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


//    public Questao(Long id, String instituicao, LocalDate ano, String imagem, String texto,
//                   List<Alternativa> alternativas, String resposta,
//                   CategoriaQuestao categoria, Usuario criador) {
//        this.id = id;
//        this.instituicao = instituicao;
//        this.ano = ano;
//        this.imagem = imagem;
//        this.texto = texto;
//        this.alternativas = alternativas;
//        this.resposta = resposta;
//        this.categoria = categoria;
//        this.criador = criador;
//    }
//
//    public Questao() {
//
//    }




}
