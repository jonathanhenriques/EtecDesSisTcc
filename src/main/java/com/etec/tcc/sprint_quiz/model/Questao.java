package com.etec.tcc.sprint_quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.lang.reflect.Type;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_questao")
public class Questao {

    //    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "O atributo instituicao não pode ser nullo")
    private String instituicao;

    //    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @CreationTimestamp
    private LocalDate ano;

    @NotBlank(message = "O atributo texto não pode ser nullo nem vazio!")
    @Size(min = 1, max = 1000)
    private String texto;

    @NotNull(message = "O atributo opcao_1 não pode ser nullo")
    @Size(max = 1000)
    private String opcao_1;

    @NotNull(message = "O atributo opcao_2 não pode ser nullo")
    @Size(max = 1000)
    private String opcao_2;

    @NotNull(message = "O atributo opcao_3 não pode ser nullo")
    @Size(max = 1000)
    private String opcao_3;

    @NotNull(message = "O atributo opcao_4 não pode ser nullo")
    @Size(max = 1000)
    private String opcao_4;

    @NotNull(message = "O atributo opcao_5 não pode ser nullo")
    @Size(max = 1000)
    private String opcao_5;

        @NotNull(message = "O atributo resposta não pode ser nullo nem vazio!")
//    @Size(max = 1)
    @Positive
    private int resposta;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonIgnoreProperties("questoes")
    private CategoriaQuestao categoria;


    @ManyToOne
    @JoinColumn(name = "criador_id")
    @JsonIgnoreProperties("questoes")
    private Usuario criador;

}
