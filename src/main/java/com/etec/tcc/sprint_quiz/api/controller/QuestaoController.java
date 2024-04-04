package com.etec.tcc.sprint_quiz.api.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;
import com.etec.tcc.sprint_quiz.model.dto.QuestaoComAlternativaDTO;
import com.etec.tcc.sprint_quiz.api.assembler.MapperAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.dto.QuestaoDTO;
import com.etec.tcc.sprint_quiz.repository.QuestaoRepository;
import com.etec.tcc.sprint_quiz.service.QuestaoService;

import io.swagger.v3.oas.annotations.Operation;


@RestController()
@RequestMapping("/questoes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QuestaoController {


    private final QuestaoService questaoService;

    private final MapperAssembler mapperAssembler;

    @Operation(summary = "Obtem todas as questoes")
    @GetMapping
    public ResponseEntity<Page<QuestaoDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(questaoService.getAll(pageable));
    }

    @Operation(summary = "Obtem questoes por id")
    @GetMapping("/{id}")
    public ResponseEntity<QuestaoDTO> getById(@PathVariable Long id) {
        Questao questao = questaoService.findByIdWithFetch(id).get();
        QuestaoDTO dto = mapperAssembler.converteQuestaoParaQuestaoDTO(questao);
    	return ResponseEntity.ok(dto);
    }



//    @Operation(summary = "Obtem questoes  pelo ano da questao")
//    @GetMapping("/ano/{ano}")
//    public ResponseEntity<List<Questao>> findAllByAno(@PathVariable
//                                                      @DateTimeFormat(
//                                                              iso = DateTimeFormat.ISO.DATE)
//                                                              LocalDate ano) {
//        return ResponseEntity.ok(questaoService.findAllByAno(ano));
//    }

//    @Operation(summary = "Obtem questoes entre um periodo inicial e final")
//    @GetMapping("/ano/entre/{anoInicial}/{anoFinal}")
//    public ResponseEntity<List<Questao>> findAllByAnoInicialFinal(@PathVariable LocalDate anoInicial, LocalDate anoFinal) {
//        return ResponseEntity.ok(questaoService.findAllByAnoInicialFinal(anoInicial, anoFinal));
//    }

//    @Operation(summary = "Obtem questoes pelo anteriores a um periodo")
//    @GetMapping("/ano/antes/{ano}")
//    public ResponseEntity<List<Questao>> findAllByAntesAno(@PathVariable @DateTimeFormat(
//            iso = DateTimeFormat.ISO.DATE)
//            LocalDate ano) {
//        return ResponseEntity.ok(questaoService.findAllByAntesAno(ano));
//    }


    @Operation(summary = "Cadastra uma questao")
    @PostMapping
    public ResponseEntity<QuestaoDTO> postQuestao(@Valid @RequestBody
//                                                       @DateTimeFormat(
//                                                                  iso = DateTimeFormat.ISO.DATE)
                                                       QuestaoDTO questao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(questaoService.postQuestao(questao));
    }

    @Operation(summary = "Cadastra uma questao com uma lista de novas alternativas")
    @PostMapping("/questaoComPostAlternativas")
    public ResponseEntity<QuestaoDTO> postQuestaoComListaDeNovasAlternativas(@RequestBody
                                                    QuestaoDTO questao) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(questaoService.postQuestaoComPostAlternativas(questao));
    }

    @PutMapping("/questaoAlternativa")
    public QuestaoDTO adicionarAlternativaEmQuestao(@RequestBody QuestaoComAlternativaDTO questao) {
        Questao questaoRecuperada = mapperAssembler.converteQuestaoComAlternativaDTOToQuestao(questao);
        QuestaoDTO questaoDTO = mapperAssembler.converteQuestaoParaQuestaoDTO(questaoRecuperada);
        return questaoService.adicionarAlternativaEmQuestao(questaoDTO);
    }

    @Operation(summary = "Atualiza uma questao")
    @PutMapping
    public ResponseEntity<QuestaoDTO> putQuestao(@Valid @RequestBody QuestaoDTO questao) {
        return ResponseEntity.ok(questaoService.putQuestao(questao));
    }

    @Operation(summary = "Deleta uma questao")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestao(@PathVariable Long id) {
    	questaoService.deleteQuestao(id);
    	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
