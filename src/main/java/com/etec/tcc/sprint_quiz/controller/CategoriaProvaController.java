package com.etec.tcc.sprint_quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etec.tcc.sprint_quiz.model.CategoriaProva;
import com.etec.tcc.sprint_quiz.repository.CategoriaProvaRepository;
import com.etec.tcc.sprint_quiz.service.CategoriaProvaService;

import io.swagger.v3.oas.annotations.Operation;

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
        return ResponseEntity.ok(categoriaProvaService.getById(id));  
    }

    @Operation(summary = "Obtem  todas as categorias")
    @GetMapping
    public ResponseEntity<List<CategoriaProva>> getAll(){
    	return ResponseEntity.ok(categoriaProvaService.getAll());
    }
    
    @Operation(summary = "Obtem  todas as categorias contendo título passado")
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<CategoriaProva>> getAllByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(categoriaProvaService.getAllByTitulo(titulo));
	}

    @Operation(summary = "Cadastra uma nova categoria")
    @PostMapping
    public ResponseEntity<CategoriaProva> postCategoriaProva(@RequestBody CategoriaProva categoria){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaProvaService.post(categoria));
    }

    @Operation(summary = "Atualiza categoria")
    @PutMapping 
    public ResponseEntity<CategoriaProva> putCategoriaProva(@RequestBody CategoriaProva categoria){
        return ResponseEntity.ok(categoriaProvaService.put(categoria));
    }

//    @PatchMapping()
//    public ResponseEntity<CategoriaProva> patchCategoriaProvaTitulo(@RequestBody CategoriaProva categoria){
//    	return ResponseEntity.ok(categoriaProvaService.patchCategoriaProvaTitulo(categoria));
//    }

    @Operation(summary = "Deleta uma categoria pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletetaCategoriaProva(@PathVariable Long id){
    	categoriaProvaService.delete(id);
    	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
