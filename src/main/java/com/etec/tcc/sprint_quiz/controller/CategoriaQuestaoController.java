package com.etec.tcc.sprint_quiz.controller;

import com.etec.tcc.sprint_quiz.exception.CategoriaQuestaoNaoEncontradaException;
import com.etec.tcc.sprint_quiz.model.CategoriaQuestao;
import com.etec.tcc.sprint_quiz.repository.CategoriaQuestaoRepository;
import com.etec.tcc.sprint_quiz.service.CategoriaQuestaoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categoriaQuestao")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaQuestaoController {

    @Autowired
    private CategoriaQuestaoRepository categoriaQuestaoRepository;

    @Autowired
    private CategoriaQuestaoService categoriaQuestaoService;

    @Operation(summary = "Obtem categoria pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaQuestao> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaQuestaoService.getById(id));
    }

    @Operation(summary = "Obtem categorias pela descricao da categoria")
    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<CategoriaQuestao>> getByDescricao(@PathVariable("descricao") String descricao){
        return ResponseEntity.ok(categoriaQuestaoService.getByDescricao(descricao));
    }

    @Operation(summary = "Obtem todas as  categorias")
    @GetMapping
    public ResponseEntity<List<CategoriaQuestao>> getAll(){
        return ResponseEntity.ok(categoriaQuestaoRepository.findAll());
    }

    @Operation(summary = "Cadastra uma categoria")
    @PostMapping
    public ResponseEntity<CategoriaQuestao> postCategoriaQuestao(@Valid @RequestBody CategoriaQuestao categoria){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaQuestaoService.postCategoriaQuestao(categoria));
    }

    @Operation(summary = "Atualiza uma categoria")
    @PutMapping
    public ResponseEntity<CategoriaQuestao> putCategoriaQuestao(@Valid @RequestBody CategoriaQuestao categoria){
        return ResponseEntity.ok(categoriaQuestaoService.putCategoriaQuestao(categoria));
    }

    @PatchMapping(path = "/patch")
    public ResponseEntity<CategoriaQuestao> patchCategoriaQuestaoTitulo(@RequestBody CategoriaQuestao categoria){
        return categoriaQuestaoRepository.findById(categoria.getId())
                .map(c -> {
                    c.setTitulo(categoria.getTitulo());
                    return ResponseEntity.ok(categoriaQuestaoRepository.save(c));
                }).orElseThrow(() -> new CategoriaQuestaoNaoEncontradaException());
    }

    @Operation(summary = "Deleta uma categoria pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoriaQuestao(@PathVariable Long id){
    	categoriaQuestaoService.deleteCategoriaQuestao(id);
    	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
