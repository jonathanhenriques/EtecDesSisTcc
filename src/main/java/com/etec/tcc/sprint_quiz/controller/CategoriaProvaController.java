package com.etec.tcc.sprint_quiz.controller;

import java.util.List;

import com.etec.tcc.sprint_quiz.model.dto.CategoriaProvaComProvasDTO;
import com.etec.tcc.sprint_quiz.model.dto.CategoriaProvaDTO;
import com.etec.tcc.sprint_quiz.util.MapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.etec.tcc.sprint_quiz.service.CategoriaProvaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/categoriaProva")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaProvaController {
 
    private final CategoriaProvaService categoriaProvaService;

    private final MapperService mapperService;
    

    @Operation(summary = "Obtem categorias pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProvaComProvasDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaProvaService.getById(id));  
    }

    @Operation(summary = "Obtem categorias pelo id com provas")
    @GetMapping("/{id}/comProvas")
    public ResponseEntity<CategoriaProvaComProvasDTO> getByIdComProvas(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaProvaService.getByIdComProvas(id));
    }

    @Operation(summary = "Obtem  todas as categorias")
    @GetMapping
    public ResponseEntity<List<CategoriaProvaDTO>> getAll(){
    	log.info("Obtendo todas as categoriaProva");
    	return ResponseEntity.ok(categoriaProvaService.findAllDTO());
    }
    

    @Operation(summary = "Cadastra uma nova categoria")
    @PostMapping
    public ResponseEntity<CategoriaProvaDTO> postCategoriaProva(@RequestBody CategoriaProvaDTO categoria){
    	log.info("Cadastrando nova categoriaProva '{}'", categoria.getTitulo());
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaProvaService.post(categoria));
    }

    @Operation(summary = "Atualiza categoria")
    @PutMapping 
    public ResponseEntity<CategoriaProvaComProvasDTO> putCategoriaProva(@RequestBody CategoriaProvaDTO categoria){
        return ResponseEntity.ok(categoriaProvaService.put(categoria));
    }


    @Operation(summary = "Deleta uma categoria pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletetaCategoriaProva(@PathVariable Long id){
    	categoriaProvaService.delete(id);
    	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
