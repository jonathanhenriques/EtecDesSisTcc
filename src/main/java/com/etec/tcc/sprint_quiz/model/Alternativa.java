package com.etec.tcc.sprint_quiz.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade Alternativa representando tabela do banco
 * 
 * @author hsjon
 * @date 25/12/2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_alternativa")
public class Alternativa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "texto {campo.texto.notnull.obrigatorio}")
	@Size(max = 1000)
//    @Schema(name = "Onde está wally?")
	private String texto;

//    @Schema(name = "https://imgur.com/9q3tXhG")
	private String foto;

	@ManyToOne
//    @OneToOne
	@JoinColumn(name = "questao_id")
//    @Cascade(CascadeType.SAVE_UPDATE)
////    @JsonIgnoreProperties("alternativas")
	@JsonIgnoreProperties(value = { "resposta", "alternativas" }, allowSetters = true)
	private Questao questao;

	public Alternativa(AlternativaDTO dto) {
		this.texto = dto.getTexto();
		this.foto = dto.getFoto();

		this.questao = questao;
	}
	
	public Alternativa paraAlternativa(AlternativaDTO dto) {
		var alternativa = new Alternativa();
    	alternativa.setTexto(dto.getTexto());
		alternativa.setFoto(dto.getFoto());
		
		var q = new Questao();
		q.setId(dto.getId());
		alternativa.setQuestao(q);
		return alternativa;
	}

	

}
