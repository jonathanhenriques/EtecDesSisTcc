package com.etec.tcc.sprint_quiz.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.etec.tcc.sprint_quiz.model.dto.ProvaComQuestaoDTO;
import com.etec.tcc.sprint_quiz.model.dto.ProvaDTO;
import com.etec.tcc.sprint_quiz.model.dto.ProvaResponse;
import com.etec.tcc.sprint_quiz.api.assembler.MapperAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.service.ProvaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequiredArgsConstructor
@RequestMapping("/provas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProvaController {

    private final ProvaService provaService;

    private final MapperAssembler mapperAssembler;

    @Operation(summary = "Obtem todas as provas")
    @GetMapping 
    public ResponseEntity<Page<ProvaResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(provaService.getAll(pageable));
    }


    @Operation(summary = "Obtem uma prova pelo seu id")
    @GetMapping("/{id}")
    public ResponseEntity<ProvaResponse> getByIdProva(@PathVariable Long id) {
        Prova prova = provaService.getById(id);
        ProvaResponse provaResponse = mapperAssembler.converteToProvaResponse(prova);
        return ResponseEntity.ok(provaResponse);
    }

    @Operation(summary = "Obtem todas as provas pelo id do usuario")
    @GetMapping("/criador/{id}")
    public ResponseEntity<List<Prova>> getByCriadorId(@PathVariable Long id) {
        return ResponseEntity.ok(provaService.getByCriadorId(id));
    }

    @Operation(summary = "Obtem todas as provas pelo nome exato da prova")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Prova>> getAllByNome(@PathVariable String nome) {
        return ResponseEntity.ok(provaService.getAllByNome(nome));
    }

    @Operation(summary = "Obtem todas as provas por descricao da prova")
    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<Prova>> getAllByDescricao(@PathVariable String descricao) {
        return ResponseEntity.ok(provaService.getAllByDescricao(descricao));
    }

    @Operation(summary = "Obtem todas as provas por categoria da prova")
    @GetMapping("/categoriaProva/{categoria}")
    public ResponseEntity<List<Prova>> getAllByCategoriaProva(@PathVariable String categoria) {
        return ResponseEntity.ok(provaService.getAllByCategoriaProva(categoria));
    }

    @Operation(summary = "Obtem todas as provas por categoria da prova")
    @GetMapping("/categoriaProvaId/{categoriaProvaId}")
    public ResponseEntity<List<Prova>> findAllByCategoriaProvaIdContainingIgnoreCase(@PathVariable Long categoriaProvaId) {
        return ResponseEntity.ok(provaService.findAllByCategoriaId(categoriaProvaId));
    }


    @Operation(summary = "Cadastra uma prova")
    @PostMapping
    public ResponseEntity<ProvaDTO> postProva(@Valid @RequestBody ProvaDTO prova) {
        return ResponseEntity.ok(provaService.post(prova));
    }

    @Operation(summary = "atualiza uma prova")
    @PutMapping
    public ResponseEntity<ProvaResponse> putProva(@Valid @RequestBody ProvaDTO prova){
    	return ResponseEntity.ok(provaService.put(prova));
    }


    @Operation(summary = "Deleta uma prova")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProva(@PathVariable Long id) {
    	provaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping("/questaoProva")
    public Prova adicionandoQuestaoEmProva(@RequestBody Prova prova) {
        return provaService.adicionarQuestaoEmProva(prova);
    }



}
