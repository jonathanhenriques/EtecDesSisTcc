package com.etec.tcc.sprint_quiz.api.controller;

import com.etec.tcc.sprint_quiz.api.exception.CategoriaQuestaoNotFoundException;
import com.etec.tcc.sprint_quiz.model.CategoriaQuestao;
import com.etec.tcc.sprint_quiz.model.dto.CategoriaQuestaoComQuestoesDTO;
import com.etec.tcc.sprint_quiz.model.dto.CategoriaQuestaoDTO;
import com.etec.tcc.sprint_quiz.repository.CategoriaQuestaoRepository;
import com.etec.tcc.sprint_quiz.service.CategoriaQuestaoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categoriaQuestao")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaQuestaoController {


    private final CategoriaQuestaoService categoriaQuestaoService;

    @Operation(summary = "Obtem categoria pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaQuestaoComQuestoesDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaQuestaoService.getById(id));
    }


    @Operation(summary = "Obtem todas as  categorias")
    @GetMapping
    public ResponseEntity<Page<CategoriaQuestaoDTO>> getAll(Pageable pageable){
        return ResponseEntity.ok(categoriaQuestaoService.getAll(pageable));
    }

    @Operation(summary = "Cadastra uma categoria")
    @PostMapping
    public ResponseEntity<CategoriaQuestao> postCategoriaQuestao(@Valid @RequestBody CategoriaQuestao categoria){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaQuestaoService.post(categoria));
    }

    @Operation(summary = "Atualiza uma categoria")
    @PutMapping
    public ResponseEntity<CategoriaQuestao> putCategoriaQuestao(@Valid @RequestBody CategoriaQuestao categoria){
        return ResponseEntity.ok(categoriaQuestaoService.put(categoria));
    }


    @Operation(summary = "Deleta uma categoria pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoriaQuestao(@PathVariable Long id){
    	categoriaQuestaoService.delete(id);
    	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
