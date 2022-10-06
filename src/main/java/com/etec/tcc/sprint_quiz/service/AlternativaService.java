package com.etec.tcc.sprint_quiz.service;

import com.etec.tcc.sprint_quiz.model.Alternativa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface AlternativaService extends IDAO<Alternativa> {

	List<Alternativa> getAll();

	Alternativa getById(Long id);

	List<Alternativa> postListaAlternativa(List<Alternativa> alternativas);

	List<Alternativa> postListaAlternativasComQuestaoSalva(List<Alternativa> alternativas);

	Alternativa post(Alternativa alternativa);

	Alternativa put(Alternativa alternativa);

	void delete(Long id);
}
