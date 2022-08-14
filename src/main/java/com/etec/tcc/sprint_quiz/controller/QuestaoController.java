package com.etec.tcc.sprint_quiz.controller;

import com.etec.tcc.sprint_quiz.model.Questao;
//import com.etec.tcc.sprint_quiz.model.dto.QuestaoDTO;
import com.etec.tcc.sprint_quiz.repository.QuestaoRepository;
import com.etec.tcc.sprint_quiz.service.QuestaoService;
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

    @GetMapping
    public ResponseEntity<List<Questao>> findAll(){
        return ResponseEntity.ok(questaoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Questao> finById(@PathVariable Long id) {
        return questaoRepository.findById(id)
                .map(q -> ResponseEntity.ok(q))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/texto/{texto}")
    public ResponseEntity<List<Questao>> findAllByTexto(@PathVariable String texto) {
        return ResponseEntity.ok(questaoRepository.findAllByTextoContainingIgnoreCase(texto));
    }

    @GetMapping("/instituicao/{instituicao}")
    public ResponseEntity<List<Questao>> findAllByInstituicao(@PathVariable String instituicao) {
        return ResponseEntity.ok(questaoRepository.findAllByInstituicaoContainingIgnoreCase(instituicao));
    }

    @GetMapping("/ano/{ano}")
    public ResponseEntity<List<Questao>> findAllByAno(@PathVariable
                                                          @DateTimeFormat(
                                                                  iso = DateTimeFormat.ISO.DATE)
                                                                  LocalDate ano) {
        return ResponseEntity.ok(questaoRepository.findAllByAno(ano));
    }

    @GetMapping("/ano/entre/{anoInicial}/{anoFinal}")
    public ResponseEntity<List<Questao>> findAllByAnoInicialFinal(@PathVariable LocalDate anoInicial, LocalDate anoFinal) {
        return ResponseEntity.ok(questaoRepository.findAllByAnoBetween(anoInicial, anoFinal));
    }

    @GetMapping("/ano/antes/{ano}")
    public ResponseEntity<List<Questao>> findAllByAntesAno(@PathVariable LocalDate ano) {
        return ResponseEntity.ok(questaoRepository.findAllByAnoBefore(ano));
    }

    @PostMapping
    public ResponseEntity<Questao> postQuestao( @RequestBody
//                                                       @DateTimeFormat(
//                                                                  iso = DateTimeFormat.ISO.DATE)
                                                                  Questao questao) {
        return questaoService.postQuestao(questao);
    }

    @PutMapping
    public ResponseEntity<Questao> putQuestao(@Valid @RequestBody Questao questao) {
        return questaoRepository.findById(questao.getId())
                .map(q -> ResponseEntity.ok(questaoRepository.save(questao)))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestao(@PathVariable Long id) {
        return questaoRepository.findById(id)
                .map(q -> ResponseEntity.notFound().build())
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


}
