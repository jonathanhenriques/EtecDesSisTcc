package com.etec.tcc.sprint_quiz.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

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

	Questao getById(@PathVariable Long id);

	List<Questao> getAllByTexto(@PathVariable String texto);

	List<Questao> getAllByInstituicao(@PathVariable String instituicao);

	List<Questao> findAllByAno(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ano);

	List<Questao> findAllByAnoInicialFinal(@PathVariable LocalDate anoInicial, LocalDate anoFinal);

	List<Questao> findAllByAntesAno(@PathVariable LocalDate ano);

	List<Questao> getQuestoesByCriadorId(@PathVariable Long criadorId);

	QuestaoDTO postQuestao(@Valid @RequestBody QuestaoDTO questao);

	QuestaoDTO adicionarAlternativaEmQuestao(QuestaoDTO questao);

//	Questao salvarQuestaoComAlternativa(@RequestBody Questao questao);

	QuestaoDTO putQuestao(@Valid @RequestBody QuestaoDTO questao);

//	Questao converteQuestaoComAlternativaDTOToQuestao(QuestaoComAlternativaDTO dto);
//
//	QuestaoDTO converteQuestaoParaQuestaoDTO(Questao questao);
//
//	Set<QuestaoDTO> converteSetDeQuestoesParaSetDequestoesDTO(Set<Questao> questoes);
//
//	List<QuestaoDTO> converteListDeQuestoesParaListDequestoesDTO(List<Questao> questoes);

	void deleteQuestao(@PathVariable Long id);

	Questao findByIdFetch(Long id);

	Optional<Questao> findByIdWithFetch( Long id);

}
