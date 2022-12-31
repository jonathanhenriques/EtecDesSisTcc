package com.etec.tcc.sprint_quiz.service;

import java.util.List;

import com.etec.tcc.sprint_quiz.model.Alternativa;
import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;

public interface AlternativaService {

	List<Alternativa> getAll();

	Alternativa getById(Long id);

	List<Alternativa> getAllByTexto(String texto);

	List<Alternativa> postListaAlternativa(List<Alternativa> alternativas);

	List<Alternativa> postListaAlternativasComQuestaoSalva(List<Alternativa> alternativas);

	AlternativaDTO post(AlternativaDTO alternativa);

	Alternativa put(Alternativa alternativa);
	
	void deletaAlternativaDeQuestao(Long questaoId, Long alternativaId);

	void deleteById(Long id);
}
