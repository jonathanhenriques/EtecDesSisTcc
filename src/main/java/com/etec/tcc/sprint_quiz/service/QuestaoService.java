package com.etec.tcc.sprint_quiz.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;
import com.etec.tcc.sprint_quiz.model.dto.QuestaoComAlternativaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.dto.QuestaoDTO;

public interface QuestaoService {

	Page<QuestaoDTO> getAll(Pageable pageable);

	Questao getById(Long id);

	List<Questao> getAllByTexto(String texto);

	List<Questao> getAllByInstituicao(String instituicao);

	List<Questao> findAllByAno(LocalDate ano);

	List<Questao> findAllByAnoInicialFinal(LocalDate anoInicial, LocalDate anoFinal);

	List<Questao> findAllByAntesAno(LocalDate ano);

	List<Questao> getQuestoesByCriadorId(Long criadorId);

	QuestaoDTO postQuestao(QuestaoDTO questao);

	QuestaoDTO postQuestaoComPostAlternativas(QuestaoDTO questao);

	QuestaoDTO adicionarAlternativaEmQuestao(QuestaoDTO questao);

//	Questao salvarQuestaoComAlternativa(@RequestBody Questao questao);

	QuestaoDTO putQuestao(QuestaoDTO questao);

	void deleteQuestao(Long id);

	Questao findByIdFetch(Long id);

	Optional<Questao> findByIdWithFetch( Long id);

}
