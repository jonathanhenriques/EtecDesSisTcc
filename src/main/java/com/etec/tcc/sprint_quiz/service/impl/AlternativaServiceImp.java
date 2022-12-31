package com.etec.tcc.sprint_quiz.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etec.tcc.sprint_quiz.configuration.TesteConfigBd;
import com.etec.tcc.sprint_quiz.exception.AlternativaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.QuestaoNotFoundException;
import com.etec.tcc.sprint_quiz.model.Alternativa;
import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;
import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;
import com.etec.tcc.sprint_quiz.repository.QuestaoRepository;
import com.etec.tcc.sprint_quiz.service.AlternativaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AlternativaServiceImp implements AlternativaService {

	@Autowired
	private AlternativaRepository alternativaRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private QuestaoRepository questaoRepository;

//	@Autowired
//	private QuestaoService questaoService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TesteConfigBd.class);

	@Override
	public List<Alternativa> getAll() {
		return alternativaRepository.findAll();
	}

	@Override
	public Alternativa getById(Long id) {
		return alternativaRepository.findById(id).orElseThrow(() -> new AlternativaNotFoundException(id.toString()));
	}

	@Override
	public AlternativaDTO post(AlternativaDTO alternativaDto) {
//		questaoRepository.findById(alternativaDto.getQuestaoId()).orElseThrow(
//				() -> new QuestaoNotFoundException("Questão não encontrada | " + alternativaDto.getQuestaoId()));

		Alternativa alternativa = modelMapper.map(alternativaDto, Alternativa.class);
		alternativa = alternativaRepository.save(alternativa);
		AlternativaDTO dto = modelMapper.map(alternativa, AlternativaDTO.class);
		return dto;
	}

	@Override
	public Alternativa put(Alternativa alternativa) {
		alternativaRepository.findById(alternativa.getId())
				.orElseThrow(() -> new AlternativaNotFoundException(alternativa.getId().toString()));
		return alternativaRepository.save(alternativa);

	}

	@Override
	public void deleteById(Long id) {
		alternativaRepository.findById(id)
				.orElseThrow(() -> new AlternativaNotFoundException(id.toString()));
		alternativaRepository.deleteById(id);

	}

	@Override
	public List<Alternativa> postListaAlternativa(List<Alternativa> alternativas) {
//		Questao questao = questaoRepository.findById(alternativas.get(0).getQuestao().getId())
//				.orElseThrow(() -> new RegraNegocioException(
//						"Questão não encontrada | id:" + alternativas.get(0).getQuestao().getId()));
//
//		alternativas.forEach(a -> {
//			questao.getAlternativas().add(a);
//		});
//
//		alternativaRepository.saveAll(alternativas);
//
//		questaoService.putQuestao(questao);
		return alternativas;
	}

	public List<Alternativa> postListaAlternativasComQuestaoSalva(List<Alternativa> alternativas) {
//		Questao questao = alternativas.get(0).getQuestao();
//
//		alternativas.forEach(a -> {
//			questao.getAlternativas().add(a);
//		});
//
//		alternativaRepository.saveAll(alternativas);
//
//		questaoService.putQuestao(questao);
		return alternativas;
	}

	@Override
	 public List<Alternativa> getAllByTexto(String texto) {
    	return alternativaRepository.findAllByTextoContainingIgnoreCase(texto);
    }
	
	public void deletaAlternativaDeQuestao(Long questaoId, Long alternativaId){
		Questao questao = questaoRepository.findById(questaoId).orElseThrow(() -> new QuestaoNotFoundException(questaoId.toString()));
		
		alternativaRepository.findById(alternativaId).orElseThrow(() -> new AlternativaNotFoundException(alternativaId.toString()));
		
		if(questao.getResposta().getId() == alternativaId)
			questao.setResposta(null);
		
		LOGGER.info("questaoId - " + questaoId + "alternativaId" + alternativaId);
		alternativaRepository.deleteAlternativaFromQuestao(questaoId, alternativaId);
		LOGGER.info("aexcluindo relacionamento alternativa e questao lista");
		alternativaRepository.deleteById(alternativaId);
		LOGGER.info("aexcluindo  alternativa - " + alternativaId);
		
	}

}
