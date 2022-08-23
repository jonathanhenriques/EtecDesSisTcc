package com.etec.tcc.sprint_quiz.controller;

import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.repository.CategoriaProvaRepository;
import com.etec.tcc.sprint_quiz.repository.ProvaRepository;
import com.etec.tcc.sprint_quiz.service.ProvaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @Operation(summary = "Obtem todas as provas")
    @GetMapping
    public ResponseEntity<List<Prova>> getAll() {
        return provaService.getAll();
    }


    @Operation(summary = "Obtem uma prova pelo seu id")
    @GetMapping("/{id}")
    public ResponseEntity<Prova> getByIdProva(@PathVariable Long id) {
        return provaService.getByIdProva(id);
    }

    @Operation(summary = "Obtem todas as provas pelo id do usuario")
    @GetMapping("/criador/{id}")
    public ResponseEntity<List<Prova>> getByCriadorId(@PathVariable Long id) {
        return provaService.getByCriadorId(id);
    }

    @Operation(summary = "Obtem todas as provas pelo nome exato da prova")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Prova>> getAllByNome(@PathVariable String nome) {
        return provaService.getAllByNome(nome);
    }

    @Operation(summary = "Obtem todas as provas por descricao da prova")
    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<Prova>> getAllByDescricao(@PathVariable String descricao) {
        return provaService.getAllByDescricao(descricao);
    }


    @Operation(summary = "Cadastra uma prova")
    @PostMapping
    public ResponseEntity<Prova> postProva(@Valid @RequestBody Prova prova) {
        return provaService.postProva(prova);
    }

    @Operation(summary = "atualiza uma prova")
    @PutMapping
    public ResponseEntity<Prova> putProva(@Valid @RequestBody Prova prova){
        return provaService.putProva(prova);
    }


    @Operation(summary = "Deleta uma prova")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProva(@PathVariable Long id) {
        return provaService.deleteProva(id);
    }


}
