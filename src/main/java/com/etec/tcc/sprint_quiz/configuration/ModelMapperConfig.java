package com.etec.tcc.sprint_quiz.configuration;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.etec.tcc.sprint_quiz.exception.AlternativaNotFoundException;
import com.etec.tcc.sprint_quiz.model.Alternativa;
import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;
import com.etec.tcc.sprint_quiz.model.dto.QuestaoDTO;
import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;

import lombok.Data;


@Data
@Configuration
public class ModelMapperConfig {
	
//	@Bean
//	public ModelMapper modelMapper() {
//		return new ModelMapper();
//	}

	@Autowired
	@Lazy
	private AlternativaRepository alternativaRepository;
	
	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		
		
//		modelMapper.getConfiguration()
//		  .setFieldMatchingEnabled(true);
//		  .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE); //verificar por que não funciona
		
		

//		/**
//		 * Converter do ModelMapper
//		 * para converter AlternativaDTO
//		 * para Alternativa
//		 */
//		Converter<AlternativaDTO, Alternativa> alternativaDTOParaAlternativa = obj -> {
//			Alternativa a = null; 
//			a = alternativaRepository.findById(obj.getSource().getId())
//					.orElseThrow(() -> new AlternativaNotFoundException(obj.getSource().toString()));
//			 
//			 return a;
//		};
		
		
		
		/**
		 * Converter do ModelMapper
		 * para converter List<Alternativa>
		 * para Set<AlternativaDTO>
		 * 
		 */
		Converter<List<Alternativa>, Set<AlternativaDTO>> ListAlternativaParaSetAlternativaDTO = lista -> {
			
			Set<Alternativa> listaSet = new HashSet<>(lista.getSource());
//			Set<AlternativaDTO> setDTO = new HashSet<>();
//			set = lista.getSource().stream().collect(Collectors.toSet());
			
			return converteSetAlternativaParaSetAlternativaDTO(listaSet);
			
		};
		

		modelMapper.createTypeMap(QuestaoDTO.class, Questao.class)
//				.addMappings(mapper -> mapper.using(alternativaDTOParaAlternativa).map(QuestaoDTO::getResposta, Questao::setResposta))
				.addMappings((mapper -> mapper.using(ListAlternativaParaSetAlternativaDTO).map(QuestaoDTO::getAlternativas, Questao::setAlternativas))
						);

		return modelMapper;

	}
	
	private Set<AlternativaDTO> converteSetAlternativaParaSetAlternativaDTO(Set<Alternativa> alternativas) {
		return alternativas.stream().map(a -> new AlternativaDTO(a)).collect(Collectors.toSet());
	}
	
	

}
