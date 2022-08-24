package com.etec.tcc.sprint_quiz.controller;

import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.repository.QuestaoRepository;
import com.etec.tcc.sprint_quiz.service.QuestaoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;


@RestController()
@RequestMapping("/questoes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QuestaoController {

    @Autowired
    private QuestaoRepository questaoRepository;

    @Autowired
    private QuestaoService questaoService;

    @Operation(summary = "Obtem todas as questoes")
    @GetMapping
    public ResponseEntity<List<Questao>> getAll() {
        return ResponseEntity.ok(questaoRepository.findAll());
    }

    @Operation(summary = "Obtem questoes por id")
    @GetMapping("/{id}")
    public ResponseEntity<Questao> getById(@PathVariable Long id) {
        return questaoService.getById(id);
    }

    @Operation(summary = "Obtem questoes pelo texto da questao")
    @GetMapping("/texto/{texto}")
    public ResponseEntity<List<Questao>> getAllByTexto(@PathVariable String texto) {
        return questaoService.getAllByTexto(texto);
    }

    @Operation(summary = "Obtem questoes  pela instituicao da questao")
    @GetMapping("/instituicao/{instituicao}")
    public ResponseEntity<List<Questao>> getAllByInstituicao(@PathVariable String instituicao) {
        return questaoService.getAllByInstituicao(instituicao);
    }

    @Operation(summary = "Obtem questoes  pelo ano da questao")
    @GetMapping("/ano/{ano}")
    public ResponseEntity<List<Questao>> findAllByAno(@PathVariable
                                                      @DateTimeFormat(
                                                              iso = DateTimeFormat.ISO.DATE)
                                                              LocalDate ano) {
        return questaoService.findAllByAno(ano);
    }

    @Operation(summary = "Obtem questoes entre um periodo inicial e final")
    @GetMapping("/ano/entre/{anoInicial}/{anoFinal}")
    public ResponseEntity<List<Questao>> findAllByAnoInicialFinal(@PathVariable LocalDate anoInicial, LocalDate anoFinal) {
        return questaoService.findAllByAnoInicialFinal(anoInicial, anoFinal);
    }

    @Operation(summary = "Obtem questoes pelo anteriores a um periodo")
    @GetMapping("/ano/antes/{ano}")
    public ResponseEntity<List<Questao>> findAllByAntesAno(@PathVariable LocalDate ano) {
        return questaoService.findAllByAntesAno(ano);
    }

    @Operation(summary = "Obtem questoes pelo id do criador da questão")
    @GetMapping("/criador/{id}")
    public ResponseEntity<List<Questao>> getQuestoesByCriadorId(@PathVariable Long id){
//        return questaoService.getQuestoesByCriadorId(id);
        return ResponseEntity.ok(questaoRepository.findAllByCriadorId(id));    }


    @Operation(summary = "Cadastra uma questao")
    @PostMapping
    public ResponseEntity<Questao> postQuestao(@Valid @RequestBody
//                                                       @DateTimeFormat(
//                                                                  iso = DateTimeFormat.ISO.DATE)
                                                       Questao questao) {
        return questaoService.postQuestao(questao);
    }

//    @Operation(summary = "Cadastra uma questao com alternativas")
//    @PostMapping("/questaoComAlternativas")
//    public ResponseEntity<Questao> postQuestaoComAlternativas(@RequestBody Questao questao){
//        return questaoService.salvarQuestaoComAlternativa(questao);
//    }

    @Operation(summary = "Atualiza uma questao")
    @PutMapping
    public ResponseEntity<Questao> putQuestao(@Valid @RequestBody Questao questao) {
        return questaoService.putQuestao(questao);
    }

    @Operation(summary = "Deleta uma questao")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestao(@PathVariable Long id) {
        return questaoService.deleteQuestao(id);
    }


}
