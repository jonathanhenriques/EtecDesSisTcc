package com.etec.tcc.sprint_quiz.controller;

import com.etec.tcc.sprint_quiz.exception.CategoriaProvaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.CategoriaQuestaoNaoEncontradaException;
import com.etec.tcc.sprint_quiz.model.CategoriaProva;
import com.etec.tcc.sprint_quiz.model.CategoriaQuestao;
import com.etec.tcc.sprint_quiz.repository.CategoriaProvaRepository;
import com.etec.tcc.sprint_quiz.repository.CategoriaQuestaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoriaProva")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaProvaController {

    @Autowired
    private CategoriaProvaRepository categoriaProvaRepository;

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProva> getById(@PathVariable Long id) {
        return categoriaProvaRepository.findById(id)
                .map(c -> ResponseEntity.ok(c))
                .orElseThrow(() -> new CategoriaProvaNotFoundException());
    }

    @GetMapping
    public ResponseEntity<List<CategoriaProva>> getAll(){
        return ResponseEntity.ok(categoriaProvaRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<CategoriaProva> postCategoriaProva(@RequestBody CategoriaProva categoria){
        if(categoriaProvaRepository.findByTitulo(categoria.getTitulo()).isPresent())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(categoriaProvaRepository.save(categoria));
    }

    @PutMapping
    public ResponseEntity<CategoriaProva> putCategoriaProva(@RequestBody CategoriaProva categoria){
        return categoriaProvaRepository.findById(categoria.getId())
                .map(c ->ResponseEntity.ok(categoriaProvaRepository.save(categoria)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping()
    public ResponseEntity<CategoriaProva> patchCategoriaProvaTitulo(@RequestBody CategoriaProva categoria){
        return categoriaProvaRepository.findById(categoria.getId())
                .map(c -> {
                    c.setTitulo(categoria.getTitulo());
                    return ResponseEntity.ok(categoriaProvaRepository.save(c));
                }).orElseThrow(() -> new CategoriaQuestaoNaoEncontradaException());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletetaoCategoriaProva(@PathVariable Long id){
        return categoriaProvaRepository.findById(id)
                .map(c -> {
                    categoriaProvaRepository.delete(c);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());

    }
}
