package com.etec.tcc.sprint_quiz.service;

import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.QuestaoProva;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;


public interface QuestaoService {

    ResponseEntity<List<Questao>> getAll();

    ResponseEntity<Questao> getById(@PathVariable Long id);

    ResponseEntity<List<Questao>> getAllByTexto(@PathVariable String texto);

    ResponseEntity<List<Questao>> getAllByInstituicao(@PathVariable String instituicao);

    ResponseEntity<List<Questao>> findAllByAno(@PathVariable
                                               @DateTimeFormat(
                                                       iso = DateTimeFormat.ISO.DATE)
                                                       LocalDate ano);

    ResponseEntity<List<Questao>> findAllByAnoInicialFinal(@PathVariable LocalDate anoInicial, LocalDate anoFinal);

    ResponseEntity<List<Questao>> findAllByAntesAno(@PathVariable LocalDate ano);


    public ResponseEntity<Questao> postQuestao(@Valid @RequestBody Questao questao);

    ResponseEntity<Questao> putQuestao(@Valid @RequestBody Questao questao);

    ResponseEntity<?> deleteQuestao(@PathVariable Long id);

}
