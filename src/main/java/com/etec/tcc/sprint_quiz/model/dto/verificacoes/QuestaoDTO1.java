package com.etec.tcc.sprint_quiz.model.dto.verificacoes;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.etec.tcc.sprint_quiz.enums.DificuldadeQuestao;
import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestaoDTO1 {

	private Long id;
	private String instituicao;
	private String imagem;
	@NotBlank(message = "texto {campo.texto.notBlank.obrigatorio}")
	@Size(min = 1, max = 1000, message = "texto {campo.texto.sizeMax} 1000")
	private String texto;
	private DificuldadeQuestao dificuldade;
	private Set<AlternativaDTO> alternativas;
	private AlternativaDTO resposta;
	private Long idCategoriaQuestao;
	private Long idCriador;

	
	

}
