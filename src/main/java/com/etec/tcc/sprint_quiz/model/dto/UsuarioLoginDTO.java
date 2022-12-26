package com.etec.tcc.sprint_quiz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioLoginDTO  {

	private static final long serialVersionUID = 1L;
	//    private Long id;
//	@NotNull(message = "{campo.nome.obrigatorio}")
//    private String nome;
    private String username; //campo de login
    private String password;
//    private String foto;
//    private String roles;
	

}
