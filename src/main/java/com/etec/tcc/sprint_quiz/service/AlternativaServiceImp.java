package com.etec.tcc.sprint_quiz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etec.tcc.sprint_quiz.exception.AlternativaNotFoundException;
import com.etec.tcc.sprint_quiz.model.Alternativa;
import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;

@Service
@Transactional
public class AlternativaServiceImp implements AlternativaService {

	@Autowired
	private AlternativaRepository alternativaRepository;

//	@Autowired
//	private QuestaoRepository questaoRepository;

//	@Autowired
//	private QuestaoService questaoService;

	@Override
	public List<Alternativa> getAll() {
		return alternativaRepository.findAll();
	}

	@Override
	public Alternativa getById(Long id) {
		return alternativaRepository.findById(id).orElseThrow(() -> new AlternativaNotFoundException(id.toString()));
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
	public Alternativa post(Alternativa alternativa) {
		return alternativaRepository.save(alternativa);
	}

	@Override
	public Alternativa put(Alternativa alternativa) {
		alternativaRepository.findById(alternativa.getId())
				.orElseThrow(() -> new AlternativaNotFoundException(alternativa.getId().toString()));
		return alternativaRepository.save(alternativa);

	}

	@Override
	public void delete(Long id) {
		Alternativa alternativa = alternativaRepository.findById(id).orElseThrow(() -> new AlternativaNotFoundException(id.toString()));
		alternativaRepository.delete(alternativa);

	}

}
