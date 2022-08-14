package com.etec.tcc.sprint_quiz.controller;

import com.etec.tcc.sprint_quiz.exception.ProvaNotFoundException;
import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.model.QuestaoProva;
import com.etec.tcc.sprint_quiz.model.dto.ProvaDTO;
import com.etec.tcc.sprint_quiz.repository.CategoriaProvaRepository;
import com.etec.tcc.sprint_quiz.repository.ProvaRepository;
import com.etec.tcc.sprint_quiz.service.ProvaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/provas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProvaController {

    @Autowired
    private ProvaRepository provaRepository;

    @Autowired
    private ProvaService provaService;

    @Autowired
    private CategoriaProvaRepository categoriaProvaRepository;


    @Operation(summary = "Obtem todas as provas pelo nome exato da prova")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Prova>> findByNome(@PathVariable String nome) {
        return ResponseEntity.ok(provaRepository.findAllByNome(nome));
    }

    @Operation(summary = "Obtem todas as provas por descricao da prova")
    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<Prova>> findByDescricao(@PathVariable String descricao) {
        return ResponseEntity.ok(provaRepository.findAllByDescricao(descricao));
    }


    @Operation(summary = "Obtem todas as provas")
    @GetMapping
    public ResponseEntity<List<Prova>> findAll() {
        return ResponseEntity.ok(provaRepository.findAll());
    }

    @Operation(summary = "Cadastra uma prova")
    @PostMapping
    public ResponseEntity<Prova> postProva(@RequestBody Prova prova) {
        return provaService.postProva(prova);
    }



//    @PutMapping
//    public ResponseEntity<Prova> putProva(@RequestBody Prova prova) {
//
//    }

    @Operation(summary = "Deleta uma prova")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProva(@PathVariable Long id){
        return provaRepository.findById(id)
                .map(p -> {
                    provaRepository.delete(p);
                    return ResponseEntity.noContent().build();
                }).orElseThrow(() -> new ProvaNotFoundException());
    }


}
