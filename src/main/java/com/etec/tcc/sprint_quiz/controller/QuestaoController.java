package com.etec.tcc.sprint_quiz.controller;

//import com.etec.tcc.sprint_quiz.model.dto.QuestaoDTO;
import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.repository.QuestaoRepository;
import com.etec.tcc.sprint_quiz.service.QuestaoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<Questao>> findAll(){
        return ResponseEntity.ok(questaoRepository.findAll());
    }

    @Operation(summary = "Obtem questoes por id")
    @GetMapping("/{id}")
    public ResponseEntity<Questao> finById(@PathVariable Long id) {
        return questaoRepository.findById(id)
                .map(q -> ResponseEntity.ok(q))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtem questoes pelo texto da questao")
    @GetMapping("/texto/{texto}")
    public ResponseEntity<List<Questao>> findAllByTexto(@PathVariable String texto) {
        return ResponseEntity.ok(questaoRepository.findAllByTextoContainingIgnoreCase(texto));
    }

    @Operation(summary = "Obtem questoes  pela instituicao da questao")
    @GetMapping("/instituicao/{instituicao}")
    public ResponseEntity<List<Questao>> findAllByInstituicao(@PathVariable String instituicao) {
        return ResponseEntity.ok(questaoRepository.findAllByInstituicaoContainingIgnoreCase(instituicao));
    }

    @Operation(summary = "Obtem questoes  pelo ano da questao")
    @GetMapping("/ano/{ano}")
    public ResponseEntity<List<Questao>> findAllByAno(@PathVariable
                                                          @DateTimeFormat(
                                                                  iso = DateTimeFormat.ISO.DATE)
                                                                  LocalDate ano) {
        return ResponseEntity.ok(questaoRepository.findAllByAno(ano));
    }

    @Operation(summary = "Obtem questoes entre um periodo inicial e final")
    @GetMapping("/ano/entre/{anoInicial}/{anoFinal}")
    public ResponseEntity<List<Questao>> findAllByAnoInicialFinal(@PathVariable LocalDate anoInicial, LocalDate anoFinal) {
        return ResponseEntity.ok(questaoRepository.findAllByAnoBetween(anoInicial, anoFinal));
    }

    @Operation(summary = "Obtem questoes pelo anteriores a um periodo")
    @GetMapping("/ano/antes/{ano}")
    public ResponseEntity<List<Questao>> findAllByAntesAno(@PathVariable LocalDate ano) {
        return ResponseEntity.ok(questaoRepository.findAllByAnoBefore(ano));
    }

    @Operation(summary = "Cadastra uma questao")
    @PostMapping
    public ResponseEntity<Questao> postQuestao( @RequestBody
//                                                       @DateTimeFormat(
//                                                                  iso = DateTimeFormat.ISO.DATE)
                                                                  Questao questao) {
        return questaoService.postQuestao(questao);
    }

    @Operation(summary = "Atualiza uma questao")
    @PutMapping
    public ResponseEntity<Questao> putQuestao(@Valid @RequestBody Questao questao) {
        return questaoRepository.findById(questao.getId())
                .map(q -> ResponseEntity.ok(questaoRepository.save(questao)))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @Operation(summary = "Deleta uma questao")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestao(@PathVariable Long id) {
        return questaoRepository.findById(id)
                .map(q -> ResponseEntity.notFound().build())
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


}
