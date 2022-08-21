package com.etec.tcc.sprint_quiz.service;

import com.etec.tcc.sprint_quiz.exception.CategoriaProvaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.CategoriaQuestaoNaoEncontradaException;
import com.etec.tcc.sprint_quiz.model.CategoriaProva;
import com.etec.tcc.sprint_quiz.repository.CategoriaProvaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Service
public class CategoriaProvaServiceImp implements CategoriaProvaService{

    @Autowired
    private CategoriaProvaRepository categoriaProvaRepository;

    public ResponseEntity<CategoriaProva> getById(@PathVariable Long id) {
        return categoriaProvaRepository.findById(id)
                .map(c -> ResponseEntity.ok(c))
                .orElseThrow(() -> new CategoriaProvaNotFoundException());
    }

    public ResponseEntity<List<CategoriaProva>> getAll(){
        return ResponseEntity.ok(categoriaProvaRepository.findAll());
    }

    public ResponseEntity<CategoriaProva> postCategoriaProva(@Valid @RequestBody CategoriaProva categoria){
        return ResponseEntity.ok(categoriaProvaRepository.save(categoria));
    }

    public ResponseEntity<CategoriaProva> putCategoriaProva(@Valid @RequestBody CategoriaProva categoria){
            return categoriaProvaRepository.findById(categoria.getId())
                    .map(c ->ResponseEntity.ok(categoriaProvaRepository.save(categoria)))
                    .orElseThrow(() -> new CategoriaProvaNotFoundException(categoria.getId().toString()));
    }

    public ResponseEntity<CategoriaProva> patchCategoriaProvaTitulo(@RequestBody CategoriaProva categoria){
        return categoriaProvaRepository.findById(categoria.getId())
                .map(c -> {
                    c.setTitulo(categoria.getTitulo());
                    return ResponseEntity.ok(categoriaProvaRepository.save(c));
                }).orElseThrow(() -> new CategoriaQuestaoNaoEncontradaException());
    }

    public ResponseEntity<?> deletetaoCategoriaProva(@PathVariable Long id){
        return categoriaProvaRepository.findById(id)
                .map(c -> {
                    categoriaProvaRepository.delete(c);
                    return ResponseEntity.noContent().build();
                }).orElseThrow(() -> new CategoriaProvaNotFoundException(id.toString()));

    }
}
