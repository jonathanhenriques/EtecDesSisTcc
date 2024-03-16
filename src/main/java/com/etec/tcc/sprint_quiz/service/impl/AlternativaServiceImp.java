package com.etec.tcc.sprint_quiz.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import com.etec.tcc.sprint_quiz.util.MapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.etec.tcc.sprint_quiz.exception.AlternativaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.QuestaoNotFoundException;
import com.etec.tcc.sprint_quiz.model.Alternativa;
import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;
import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;
import com.etec.tcc.sprint_quiz.repository.QuestaoRepository;
import com.etec.tcc.sprint_quiz.service.AlternativaService;

@Service
@RequiredArgsConstructor
@Transactional
public class AlternativaServiceImp implements AlternativaService {

	private final AlternativaRepository alternativaRepository;

	private final QuestaoRepository questaoRepository;

	private final MapperService mapperService;


	/**
	 * @see AlternativaService#getAll()
	 */
	@Override
	public Page<AlternativaDTO> getAll(Pageable pageable) {
		Page<Alternativa> listaAlternativas = alternativaRepository.findAll(pageable);

		List<AlternativaDTO> listaAlternativasDTO = mapperService
				.converteListDeAlternativasParaListDeAlternativasDTO(listaAlternativas.getContent());

		Page<AlternativaDTO> pageAlternativasDTO =
				new PageImpl<>(listaAlternativasDTO, pageable, listaAlternativas.getTotalElements());
		return pageAlternativasDTO;
	}

	/**
	 * @see AlternativaService#findById(Long)
	 * @throws AlternativaNotFoundException
	 */
	@Override
	public AlternativaDTO getById(Long id) {


		return alternativaRepository.findById(id).map(mapperService::converteAlternativaParaAlternativaDTO)
				.orElseThrow(() -> new AlternativaNotFoundException(id.toString()));

	}

	/**
	 * @see AlternativaService#post(AlternativaDTO)
	 */
	// verificar se funciona
	@Override
	public AlternativaDTO post(AlternativaDTO alternativaDto) {
		Alternativa alternativa = new Alternativa();
		alternativa.setTexto(alternativaDto.getTexto());
		alternativa.setFoto(alternativaDto.getFoto());
		Alternativa retorno = alternativaRepository.save(alternativa);

		AlternativaDTO dto = new AlternativaDTO();
		dto.setId(retorno.getId());
		dto.setTexto(retorno.getTexto());
		dto.setFoto(retorno.getFoto());

		return dto;
	}

	/**
	 * @see AlternativaService#put(AlternativaDTO)
	 * @throws AlternativaNotFoundException
	 */
	@Override
	public AlternativaDTO put(AlternativaDTO dto) {
		Alternativa a = alternativaRepository.findById(dto.getId())
				.orElseThrow(() -> new AlternativaNotFoundException(dto.getId().toString()));

		Alternativa alternativa = new Alternativa();
		alternativa.setTexto(dto.getTexto());
		alternativa.setFoto(dto.getFoto());
		Alternativa retorno = alternativaRepository.save(alternativa);

		AlternativaDTO alternativaAtualizada = new AlternativaDTO();
		alternativaAtualizada.setId(retorno.getId());
		alternativaAtualizada.setTexto(retorno.getTexto());
		alternativaAtualizada.setFoto(retorno.getFoto());

		return alternativaAtualizada;

	}


	/**
	 * @see AlternativaService#deleteById(Long)
	 * @throws AlternativaNotFoundException
	 */
	@Override
	public void deleteById(Long id) {
		alternativaRepository.findById(id).orElseThrow(() -> new AlternativaNotFoundException(id.toString()));
		alternativaRepository.deleteById(id);

	}

	/**
	 * @see AlternativaService#removeAlternativaDeQuestao(Long questaoId, Long alternativaId) * @throws QuestaoNotFoundException
	 * @throws AlternativaNotFoundException
	 */
	public void removeAlternativaDeQuestao(Long questaoId, Long alternativaId) {
		Questao questao = questaoRepository.findById(questaoId)
				.orElseThrow(() -> new QuestaoNotFoundException(questaoId.toString()));

		buscarOuFalhar(alternativaId);

		List<Alternativa> listaAlternativas = new ArrayList<>(questao.getAlternativas());
		Alternativa alternativaExcluir = new Alternativa();
		for(int j = 0; j < questao.getAlternativas().size(); j++) {


			if(listaAlternativas.get(j).getId().equals(alternativaId)) {
				alternativaExcluir = listaAlternativas.get(j);
				listaAlternativas.remove(j);
			}
			questao.setAlternativas(listaAlternativas);
			questaoRepository.save(questao);
			alternativaRepository.delete(alternativaExcluir);

		}

	}




	public Alternativa buscarOuFalhar(Long alternativaId) {
		return alternativaRepository.findById(alternativaId)
				.orElseThrow(() -> new AlternativaNotFoundException(alternativaId));
	}

}
