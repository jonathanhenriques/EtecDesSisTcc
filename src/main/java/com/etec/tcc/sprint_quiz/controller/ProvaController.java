package com.etec.tcc.sprint_quiz.controller;

import com.etec.tcc.sprint_quiz.exception.ProvaNotFoundException;
import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.model.dto.ProvaDTO;
import com.etec.tcc.sprint_quiz.repository.CategoriaProvaRepository;
import com.etec.tcc.sprint_quiz.repository.ProvaRepository;
import com.etec.tcc.sprint_quiz.service.ProvaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Prova>> findByNome(@PathVariable String nome) {
        return ResponseEntity.ok(provaRepository.findAllByNome(nome));
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<Prova>> findByDescricao(@PathVariable String descricao) {
        return ResponseEntity.ok(provaRepository.findAllByDescricao(descricao));
    }


    @GetMapping
    public ResponseEntity<List<Prova>> findAll() {
        return ResponseEntity.ok(provaRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<ProvaDTO> postProva(@RequestBody ProvaDTO prova) {
//        if (categoriaProvaRepository.existsById(prova.getId())) {
//            return ResponseEntity.status(HttpStatus.CREATED).body(provaRepository.save(prova));
//        }
//        throw new CategoriaProvaNotFoundException();
        return provaService.postProva(prova);
    }

//    @PutMapping
//    public ResponseEntity<Prova> putProva(@RequestBody Prova prova) {
//
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProva(@PathVariable Long id){
        return provaRepository.findById(id)
                .map(p -> {
                    provaRepository.delete(p);
                    return ResponseEntity.noContent().build();
                }).orElseThrow(() -> new ProvaNotFoundException());
    }


}
