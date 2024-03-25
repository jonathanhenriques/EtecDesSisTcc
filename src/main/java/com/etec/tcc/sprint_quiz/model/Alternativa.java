package com.etec.tcc.sprint_quiz.model;

import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;
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


	@Column(name = "isResposta", columnDefinition = "BOOLEAN DEFAULT FALSE")
	private Boolean isResposta;

//	public Alternativa(AlternativaDTO dto) {
//		this.id = Objects.isNull(dto.getId()) ?  null :  dto.getId();
//		this.texto = dto.getTexto();
//		this.foto = dto.getFoto();
//	}

}
