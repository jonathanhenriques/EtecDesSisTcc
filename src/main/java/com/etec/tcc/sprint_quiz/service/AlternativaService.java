package com.etec.tcc.sprint_quiz.service;

import java.util.List;

import com.etec.tcc.sprint_quiz.model.Alternativa;

public interface AlternativaService {

	List<Alternativa> getAll();

	Alternativa getById(Long id);

	List<Alternativa> postListaAlternativa(List<Alternativa> alternativas);

	List<Alternativa> postListaAlternativasComQuestaoSalva(List<Alternativa> alternativas);

	Alternativa post(Alternativa alternativa);

	Alternativa put(Alternativa alternativa);

	void delete(Long id);
}
