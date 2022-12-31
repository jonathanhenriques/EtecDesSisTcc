package com.etec.tcc.sprint_quiz.model.dto;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import com.etec.tcc.sprint_quiz.enums.DificuldadeQuestao;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestaoDTO {

//	private Long id;
//	private String instituicao;
//	@CreationTimestamp
//	private LocalDate ano;
//	private String imagem;
//	@NotBlank(message = "texto {campo.texto.notBlank.obrigatorio}")
//	@Size(min = 1, max = 1000, message = "texto {campo.texto.sizeMax} 1000")
//	private String texto;
//	private DificuldadeQuestao dificuldade;
//	private Set<AlternativaDTO> alternativas;
//	private Alternativa resposta;
//	private CategoriaQuestao categoria;
//	private Usuario criador;
	
	private Long id;
	private String instituicao;
	@CreationTimestamp
	private LocalDate ano;
	private String imagem;
	@NotBlank(message = "texto {campo.texto.notBlank.obrigatorio}")
	@Size(min = 1, max = 1000, message = "texto {campo.texto.sizeMax} 1000")
	private String texto;
	private DificuldadeQuestao dificuldade;
	private Set<AlternativaDTO> alternativas;
//	@JsonProperty("respostaId")
	private AlternativaDTO respostaId;
	private Long categoriaId;
	private Long usuarioId;

	
	

}
