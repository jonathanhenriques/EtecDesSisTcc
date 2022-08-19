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

//@Data
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

//    @NotNull(message = "O atributo opcao_1 não pode ser nullo")
//    @Size(max = 1000)
    private String opcao_1;

//    @NotNull(message = "O atributo opcao_2 não pode ser nullo")
//    @Size(max = 1000)
    private String opcao_2;

//    @NotNull(message = "O atributo opcao_3 não pode ser nullo")
//    @Size(max = 1000)
    private String opcao_3;

//    @NotNull(message = "O atributo opcao_4 não pode ser nullo")
//    @Size(max = 1000)
    private String opcao_4;

//    @NotNull(message = "O atributo opcao_5 não pode ser nullo")
//    @Size(max = 1000)
    private String opcao_5;

//    private Alternativas alternativas;

    @OneToMany
    @JsonIgnoreProperties()
    private List<Alternativa> alternativas;


//    @NotBlank(message = "O atributo resposta não pode ser nullo nem vazio!")
//    @Size(max = 1)
    private String resposta;


//    @NotNull(message = "O atributo resposta não pode ser nullo!")
//    @Positive
//    private int resposta;


    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonIgnoreProperties({"descritivo", "questoes"})
    private CategoriaQuestao categoria;


    @ManyToOne
    @JoinColumn(name = "criador_id")
    @JsonIgnoreProperties({"email", "senha", "foto", "tipo", "provas", "questoes"})
    private Usuario criador;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public LocalDate getAno() {
        return ano;
    }

    public void setAno(LocalDate ano) {
        this.ano = ano;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getOpcao_1() {
        return opcao_1;
    }

    public void setOpcao_1(String opcao_1) {
        this.opcao_1 = opcao_1;
    }

    public String getOpcao_2() {
        return opcao_2;
    }

    public void setOpcao_2(String opcao_2) {
        this.opcao_2 = opcao_2;
    }

    public String getOpcao_3() {
        return opcao_3;
    }

    public void setOpcao_3(String opcao_3) {
        this.opcao_3 = opcao_3;
    }

    public String getOpcao_4() {
        return opcao_4;
    }

    public void setOpcao_4(String opcao_4) {
        this.opcao_4 = opcao_4;
    }

    public String getOpcao_5() {
        return opcao_5;
    }

    public void setOpcao_5(String opcao_5) {
        this.opcao_5 = opcao_5;
    }

    public List<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public CategoriaQuestao getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaQuestao categoria) {
        this.categoria = categoria;
    }

    public Usuario getCriador() {
        return criador;
    }

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }
}
