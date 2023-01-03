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
		

//		modelMapper.createTypeMap(QuestaoDTO.class, Questao.class)
////				.addMappings(mapper -> mapper.using(alternativaDTOParaAlternativa).map(QuestaoDTO::getResposta, Questao::setResposta))
//				.addMappings((mapper -> mapper.using(ListAlternativaParaSetAlternativaDTO).map(QuestaoDTO::getAlternativas, Questao::setAlternativas))
//						);
		
		
		
		
		
		Converter<HashSet<AlternativaDTO>, HashSet<Alternativa>> setAlternativaDTOParaSetAlternativaConverter = obj -> {
			return obj.getSource().stream().map(dto -> {

				Alternativa alternativa = alternativaRepository.findById(dto.getId())
						.orElseThrow(() -> new AlternativaNotFoundException(obj.toString()));
				return alternativa;
//				

			}).collect(Collectors.toCollection(HashSet::new));
//			}).collect(Collectors.toSet());

		};
		
		
		
		
		modelMapper.createTypeMap(QuestaoDTO.class, Questao.class)
		.addMappings(mapper -> mapper.using(setAlternativaDTOParaSetAlternativaConverter)
				.map(QuestaoDTO::getAlternativas, Questao::setAlternativas));

		return modelMapper;

	}
	
	private Set<AlternativaDTO> converteSetAlternativaParaSetAlternativaDTO(Set<Alternativa> alternativas) {
		return alternativas.stream().map(a ->  converteParaAlternativaDTO(a)).collect(Collectors.toSet());
	}
	
	private Set<Alternativa> converteSetAlternativaDTOParaSetAlternativa(Set<AlternativaDTO> dtos) {
		return dtos.stream().map(a ->  converteParaAlternativa(a)).collect(Collectors.toSet());
	}
	
	private Alternativa converteParaAlternativa(AlternativaDTO dto) {
		Alternativa a = new Alternativa();
		a.setId(dto.getId());
		a.setFoto(dto.getFoto());
		a.setTexto(dto.getTexto());
		return a;
	}
	
	private AlternativaDTO converteParaAlternativaDTO(Alternativa alternativa) {
		AlternativaDTO dto = new AlternativaDTO();
		dto.setId(alternativa.getId());
		dto.setFoto(alternativa.getFoto());
		dto.setTexto(alternativa.getTexto());
		return dto;
	}
	
	

}
