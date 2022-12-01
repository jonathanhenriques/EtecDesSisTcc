package com.etec.tcc.sprint_quiz.controller;

import com.etec.tcc.sprint_quiz.model.Alternativa;
import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;
import com.etec.tcc.sprint_quiz.service.AlternativaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alternativas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AlternativaController {

    @Autowired
    private AlternativaRepository alternativaRepository;

    @Autowired
    private AlternativaService alternativaService;


    @Operation(summary = "Obtem todas as alternativas")
    @GetMapping
    public ResponseEntity<List<Alternativa>> getAll() {
        return ResponseEntity.ok(alternativaService.getAll());
    }

    @Operation(summary = "Obtem uma alternativa pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<Alternativa> getById(@PathVariable Long id) {
    	return ResponseEntity.ok(alternativaService.getById(id));
    }

    @Operation(summary = "Obtem alternativas pelo texto da alternativa")
    @GetMapping("/texto/{texto}")
    public ResponseEntity<List<Alternativa>> getAllByTexto(@PathVariable String texto) {
    	return ResponseEntity.ok(alternativaRepository.findAllByTextoContainingIgnoreCase(texto));
    }

    @Operation(summary = "cria várias alternativas")
    @PostMapping("/listaAlternativas")
    public ResponseEntity<List<Alternativa>> postListaAlternativa(@Valid @RequestBody List<Alternativa> alternativas) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alternativaService.postListaAlternativa(alternativas));
    }

    @Operation(summary = "cria uma nova alternativa")
    @PostMapping
    public ResponseEntity<Alternativa> post(@Valid @RequestBody Alternativa alternativa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alternativaService.post(alternativa));
    }

    @Operation(summary = "atualiza uma alternativa")
    @PutMapping
    public ResponseEntity<Alternativa> put(@Valid @RequestBody Alternativa alternativa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alternativaService.put(alternativa));
    }

    @Operation(summary = "deleta uma alternativa pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
    	alternativaService.delete(id); 
    	return ResponseEntity.noContent().build();
    }
    
    
}
