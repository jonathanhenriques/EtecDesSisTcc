package com.etec.tcc.sprint_quiz.controller;

import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.model.QuestaoProva;
import com.etec.tcc.sprint_quiz.repository.ProvaRepository;
import com.etec.tcc.sprint_quiz.repository.QuestaoProvaRepository;
import com.etec.tcc.sprint_quiz.service.QuestaoProvaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questaoprova")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QuestaoProvaController {

    @Autowired
    private QuestaoProvaService questaoProvaService;

    @Autowired
    private QuestaoProvaRepository questaoProvaRepository;

    @Autowired
    private ProvaRepository provaRepository;

    @Operation(summary = "Obtem todas as questoesProva")
    @GetMapping
    public ResponseEntity<List<QuestaoProva>> findAll() {
        return ResponseEntity.ok(questaoProvaRepository.findAll());
    }
    
    @Operation(summary = "Obtem todas as questoesProva por prova id")
    @GetMapping("/listaprovas/{id}")
    public ResponseEntity<List<QuestaoProva>> findAllByProvaId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(questaoProvaRepository.findAllByProvaId(id));
    }

    @Operation(summary = "Cadastra uma questaoProva")
    @PostMapping("/provaid/{id}")
    public ResponseEntity<QuestaoProva> postQuestaoProva(@RequestBody QuestaoProva questaoProva, @PathVariable("id") Long id) {
        return questaoProvaService.postQuestaoProva(questaoProva, id);
    }

    @Operation(summary = "Cadastra lista de questoesProva")
    @PostMapping("/listaprovaid/{id}")
    public ResponseEntity<List<QuestaoProva>> postListaQuestaoProva(@RequestBody List<QuestaoProva> listaQuestaoProva,
                                                                    @PathVariable("id") Long id) {
        return questaoProvaService.postListaQuestaoProva(listaQuestaoProva, id);
    }

    @Operation(summary = "Deleta questoesProva pelo id")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteQuestaoProva(@PathVariable("id") Long id){
        return questaoProvaService.deleteQuestaoProva(id);
    }
}
