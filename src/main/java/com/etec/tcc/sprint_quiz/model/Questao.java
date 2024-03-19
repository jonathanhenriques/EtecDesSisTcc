package com.etec.tcc.sprint_quiz.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;

import com.etec.tcc.sprint_quiz.enums.DificuldadeQuestao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa a Questão no Banco
 * 
 * @author hsjon
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_questao")
public class Questao {

	// @EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// @NotNull(message = "O atributo instituicao não pode ser nullo")
	private String instituicao;

	// @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "dd-MM-yyyy")
	@CreationTimestamp
//    @UpdateTimestamp
	private LocalDate ano;

	private String imagem;

	@NotBlank(message = "texto {campo.texto.notBlank.obrigatorio}")
	@Size(min = 1, max = 1000, message = "texto {campo.texto.sizeMax} 1000")
	private String texto;

	private DificuldadeQuestao dificuldade;

	@OneToMany
	private List<Alternativa> alternativas;

	private Long resposta;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	@JsonIgnoreProperties(value = { "questoes" })
	private CategoriaQuestao categoria;

	@ManyToOne
	@JoinColumn(name = "criador_id")
	private Usuario criador;

	@JsonIgnoreProperties({"questoes"})
	@ManyToMany(mappedBy = "questoes")
	private Set<Prova> provas = new HashSet<>();


}
