package com.etec.tcc.sprint_quiz.controller;

import com.etec.tcc.sprint_quiz.exception.CategoriaQuestaoNaoEncontradaException;
import com.etec.tcc.sprint_quiz.model.CategoriaQuestao;
import com.etec.tcc.sprint_quiz.repository.CategoriaQuestaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoriaQuestao")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaQuestaoController {

    @Autowired
    private CategoriaQuestaoRepository categoriaQuestaoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaQuestao> getById(@PathVariable Long id) {
        return categoriaQuestaoRepository.findById(id)
                .map(c -> ResponseEntity.ok(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/descritivo/{descritivo}")
    public ResponseEntity<CategoriaQuestao> getByDescritivo(@PathVariable("descritivo") String descritivo){
        return categoriaQuestaoRepository.findByDescritivoContainingIgnoreCase(descritivo)
                .map(categoria -> ResponseEntity.ok(categoria))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @GetMapping
    public ResponseEntity<List<CategoriaQuestao>> getAll(){
        return ResponseEntity.ok(categoriaQuestaoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<CategoriaQuestao> postCategoriaQuestao(@RequestBody CategoriaQuestao categoria){
//        if(categoriaQuestaoRepository.findByTitulo(categoria.getTitulo()).isPresent())
//            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaQuestaoRepository.save(categoria));
    }

    @PutMapping
    public ResponseEntity<CategoriaQuestao> putCategoriaQuestao(@RequestBody CategoriaQuestao categoria){
        return categoriaQuestaoRepository.findById(categoria.getId())
                .map(c ->ResponseEntity.ok(categoriaQuestaoRepository.save(categoria)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping(path = "/patch")
    public ResponseEntity<CategoriaQuestao> patchCategoriaQuestaoTitulo(@RequestBody CategoriaQuestao categoria){
        return categoriaQuestaoRepository.findById(categoria.getId())
                .map(c -> {
                    c.setTitulo(categoria.getTitulo());
                    return ResponseEntity.ok(categoriaQuestaoRepository.save(c));
                }).orElseThrow(() -> new CategoriaQuestaoNaoEncontradaException());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoriaQuestao(@PathVariable Long id){
        return categoriaQuestaoRepository.findById(id)
                .map(c -> {
                    categoriaQuestaoRepository.delete(c);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());

    }
}
