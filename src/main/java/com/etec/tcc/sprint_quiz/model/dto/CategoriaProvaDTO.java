package com.etec.tcc.sprint_quiz.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaProvaDTO {

	    private Long id;
	    @NotBlank(message = "titulo {campo.texto.notBlank.obrigatorio}")
	    @Size(max = 400, message = "titulo {campo.texto.sizeMax} 400")
//	    @Schema(name = "Provas de vestibulares")
	    private String titulo;

	    @Size(max = 1000 ,message = "descricao {campo.texto.sizeMax} 1000")
//	    @Schema(name = "Vestibulares públicos")
	    private String descricao;


}
