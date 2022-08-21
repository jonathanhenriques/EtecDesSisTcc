package com.etec.tcc.sprint_quiz.controller;

import com.etec.tcc.sprint_quiz.exception.CategoriaProvaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.CategoriaQuestaoNaoEncontradaException;
import com.etec.tcc.sprint_quiz.model.CategoriaProva;
import com.etec.tcc.sprint_quiz.model.CategoriaQuestao;
import com.etec.tcc.sprint_quiz.repository.CategoriaProvaRepository;
import com.etec.tcc.sprint_quiz.repository.CategoriaQuestaoRepository;
import com.etec.tcc.sprint_quiz.service.CategoriaProvaService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Autowired
    private CategoriaProvaService categoriaProvaService;

    @Operation(summary = "Obtem categorias pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProva> getById(@PathVariable Long id) {
        return categoriaProvaService.getById(id);
    }

    @Operation(summary = "Obtem  todas as categorias")
    @GetMapping
    public ResponseEntity<List<CategoriaProva>> getAll(){
        return categoriaProvaService.getAll();
    }

    @Operation(summary = "Cadastra uma nova categoria")
    @PostMapping
    public ResponseEntity<CategoriaProva> postCategoriaProva(@RequestBody CategoriaProva categoria){
        return categoriaProvaService.postCategoriaProva(categoria);
    }

    @Operation(summary = "Atualiza categoria")
    @PutMapping
    public ResponseEntity<CategoriaProva> putCategoriaProva(@RequestBody CategoriaProva categoria){
        return categoriaProvaService.putCategoriaProva(categoria);
    }

    @PatchMapping()
    public ResponseEntity<CategoriaProva> patchCategoriaProvaTitulo(@RequestBody CategoriaProva categoria){
        return categoriaProvaService.patchCategoriaProvaTitulo(categoria);
    }

    @Operation(summary = "Deleta uma categoria pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletetaoCategoriaProva(@PathVariable Long id){
        return categoriaProvaService.deletetaoCategoriaProva(id);

    }
}
