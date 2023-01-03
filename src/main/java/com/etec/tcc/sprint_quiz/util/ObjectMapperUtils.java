package com.etec.tcc.sprint_quiz.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.etec.tcc.sprint_quiz.exception.AlternativaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.CategoriaQuestaoNotFoundException;
import com.etec.tcc.sprint_quiz.model.Alternativa;
import com.etec.tcc.sprint_quiz.model.CategoriaQuestao;
import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;
import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;
import com.etec.tcc.sprint_quiz.repository.CategoriaQuestaoRepository;

public class ObjectMapperUtils {

	@Autowired
	private static CategoriaQuestaoRepository categoriaQuestaoRepository;

	@Autowired
	private static AlternativaRepository alternativaRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Bean
	public ObjectMapperUtils ot() {
		return new ObjectMapperUtils();
	}
	
	

	/**
	 * Converter do ModelMapper para converter AlternativaDTO para Alternativa
	 */
	Converter<Long, CategoriaQuestao> longCategoriaParaCategoriaQuestao = obj -> {
		CategoriaQuestao categoria = null;
		categoria = categoriaQuestaoRepository.findById(obj.getSource())
				.orElseThrow(() -> new CategoriaQuestaoNotFoundException(obj.toString()));

		return categoria;
	};

	/**
	 * Model mapper property setting are specified in the following block. Default
	 * property matching strategy is set to Strict see {@link MatchingStrategies}
	 * Custom mappings are added using {@link ModelMapper#addMappings(PropertyMap)}
	 */
//	static {

//		modelMapper = new ModelMapper();

		/**
		 * Conversor que ensina o ModelMapper a mapear de um atributo para outro. Long
		 * resposta(contêm id de uma alternativa), para Alternativa resposta. De
		 * QuestaoDTO para Questao
		 */
		Converter<Long, Alternativa> LongParaAlternativaConverter = obj -> {
			Alternativa alternativa = null;
			alternativa = alternativaRepository.findById(obj.getSource())
					.orElseThrow(() -> new AlternativaNotFoundException(obj.toString()));

			return alternativa;
		};

		/**
		 * Conversor que ensina o ModelMapper a mapear de um atributo para outro.
		 * Set<AlternativaDTO> alternativas, para Set<Alternativa> alternativas. De
		 * QuestaoDTO para Questao
		 */
		Converter<HashSet<AlternativaDTO>, HashSet<Alternativa>> setAlternativaDTOParaSetAlternativaConverter = obj -> {
			return obj.getSource().stream().map(dto -> {

//				Alternativa alternativa = alternativaRepository.findById(dto.getId())
//						.orElseThrow(() -> new AlternativaNotFoundException(obj.toString()));
//				return alternativa;
				Alternativa a = new Alternativa();
				a.setFoto("");
				a.setTexto("teer");
				a.setId(2L);
				return a;

			}).collect(Collectors.toCollection(HashSet::new));
//			}).collect(Collectors.toSet());

		};

		
		
//		modelMapper.createTypeMap(QuestaoDTO.class, Questao.class)
//				.addMappings(mapper -> mapper.using(setAlternativaDTOParaSetAlternativaConverter)
//						.map(QuestaoDTO::getAlternativas, Questao::setAlternativas));
		
		
		
		
		
		
      
//    	modelMapper.createTypeMap(QuestaoDTO.class, Questao.class)
//    	.<Alternativa>addMapping(src ->LongParaAlternativaConverter , (dest, value) -> dest.setResposta(value));
//      

//		modelMapper.createTypeMap(QuestaoDTO.class, Questao.class)
//				.addMappings(mapper -> mapper.using(longCategoriaParaCategoriaQuestao).map(QuestaoDTO::getIdCategoriaQuestao, Questao::setCategoria));
//				.addMappings((mapper -> mapper.using(ListAlternativaParaSetAlternativaDTO).map(QuestaoDTO::getAlternativas, Questao::setAlternativas)));

//	}

	/**
	 * Hide from public usage.
	 */
//	private ObjectMapperUtils() {
//	}

	/**
	 * <p>
	 * Note: outClass object must have default constructor with no arguments
	 * </p>
	 *
	 * @param <D>      type of result object.
	 * @param <T>      type of source object to map from.
	 * @param entity   entity that needs to be mapped.
	 * @param outClass class of result object.
	 * @return new object of <code>outClass</code> type.
	 */
	public <D, T> D map(final T entity, Class<D> outClass) {
		return modelMapper.map(entity, outClass);
	}

	/**
	 * <p>
	 * Note: outClass object must have default constructor with no arguments
	 * </p>
	 *
	 * @param entityList list of entities that needs to be mapped
	 * @param outCLass   class of result list element
	 * @param <D>        type of objects in result list
	 * @param <T>        type of entity in <code>entityList</code>
	 * @return list of mapped object with <code><D></code> type.
	 */
	public <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
		return entityList.stream().map(entity -> map(entity, outCLass)).collect(Collectors.toList());
	}

	/**
	 * Maps {@code source} to {@code destination}.
	 *
	 * @param source      object to map from
	 * @param destination object to map to
	 */
	public <S, D> D map(final S source, D destination) {
		modelMapper.map(source, destination);
		return destination;
	}
}
