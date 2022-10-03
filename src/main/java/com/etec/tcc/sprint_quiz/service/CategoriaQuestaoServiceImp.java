package com.etec.tcc.sprint_quiz.service;

import com.etec.tcc.sprint_quiz.exception.CategoriaQuestaoNotFoundException;
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
import java.util.List;

@Service
public class CategoriaQuestaoServiceImp implements CategoriaQuestaoService {


    @Autowired
    private CategoriaQuestaoRepository categoriaQuestaoRepository;

    @Override
    public CategoriaQuestao getById(@PathVariable Long id) {
        return categoriaQuestaoRepository.findById(id)
                .orElseThrow(() -> new CategoriaQuestaoNotFoundException("id:" + id));
    }

    @Override
    public List<CategoriaQuestao> getByTitutlo(@PathVariable("titulo") String titulo){
        return categoriaQuestaoRepository.findAllByTituloContainingIgnoreCase(titulo);

    }

    @Override
    public List<CategoriaQuestao> getByDescricao(@PathVariable("descricao") String descricao){
        return categoriaQuestaoRepository.findByDescricaoContainingIgnoreCase(descricao);
    }

    @Override
    public CategoriaQuestao postCategoriaQuestao(@Valid @RequestBody CategoriaQuestao categoria){
        return categoriaQuestaoRepository.save(categoria);
    }

    @Override
    public CategoriaQuestao putCategoriaQuestao(@Valid @RequestBody CategoriaQuestao categoria){
        return categoriaQuestaoRepository.findById(categoria.getId())
                .map(c ->categoriaQuestaoRepository.save(categoria))
                .orElseThrow(() -> new CategoriaQuestaoNotFoundException("id:" + categoria.getId()));
    }

    @Override
    public void deleteCategoriaQuestao(@PathVariable Long id){
         categoriaQuestaoRepository.findById(id)
                .map(c -> {
                    categoriaQuestaoRepository.delete(c);
                    return ResponseEntity.noContent().build();
                }).orElseThrow(() -> new CategoriaQuestaoNotFoundException("id:" + id));

    }
}
