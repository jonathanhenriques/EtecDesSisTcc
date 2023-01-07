package com.etec.tcc.sprint_quiz.model.dto.dtoRequest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Classe DTO de Requisicao
 * usada no envio das requisicoes aos endpoints
 * @author hsjon
 *@date 26/12/2022
 */
public class AlternativaDTOReq {

	@NotNull(message = "texto {campo.texto.notnull.obrigatorio}")
	@Size(max = 1000)
	private String texto;
	private String foto;
	private Long questaoId;

}
