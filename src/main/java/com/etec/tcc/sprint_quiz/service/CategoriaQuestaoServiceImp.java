package com.etec.tcc.sprint_quiz.service;

import com.etec.tcc.sprint_quiz.exception.CategoriaQuestaoNaoEncontradaException;
import com.etec.tcc.sprint_quiz.exception.RegraNegocioException;
import com.etec.tcc.sprint_quiz.model.CategoriaQuestao;
import com.etec.tcc.sprint_quiz.repository.CategoriaQuestaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@Service
public class CategoriaQuestaoServiceImp implements CategoriaQuestaoService {


    @Autowired
    private CategoriaQuestaoRepository categoriaQuestaoRepository;

    @Override
    public ResponseEntity<CategoriaQuestao> getById(@PathVariable Long id) {
        return categoriaQuestaoRepository.findById(id)
                .map(c -> ResponseEntity.ok(c))
                .orElseThrow(() -> new CategoriaQuestaoNaoEncontradaException("id:" + id));
    }

    @Override
    public ResponseEntity<CategoriaQuestao> getByDescricao(@PathVariable("descricao") String descricao){
        return categoriaQuestaoRepository.findByDescricaoContainingIgnoreCase(descricao)
                .map(categoria -> ResponseEntity.ok(categoria))
                .orElseThrow(() -> new CategoriaQuestaoNaoEncontradaException("descricao:" + descricao));
    }

    @Override
    public ResponseEntity<CategoriaQuestao> postCategoriaQuestao(@Valid @RequestBody CategoriaQuestao categoria){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaQuestaoRepository.save(categoria));
    }

    @Override
    public ResponseEntity<CategoriaQuestao> putCategoriaQuestao(@Valid @RequestBody CategoriaQuestao categoria){
        return categoriaQuestaoRepository.findById(categoria.getId())
                .map(c ->ResponseEntity.ok(categoriaQuestaoRepository.save(categoria)))
                .orElseThrow(() -> new CategoriaQuestaoNaoEncontradaException("id:" + categoria.getId()));
    }

    @Override
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> deleteCategoriaQuestao(@PathVariable Long id){
        return categoriaQuestaoRepository.findById(id)
                .map(c -> {
                    categoriaQuestaoRepository.delete(c);
                    return ResponseEntity.noContent().build();
                }).orElseThrow(() -> new CategoriaQuestaoNaoEncontradaException("id:" + id));

    }
}
