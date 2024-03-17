package com.etec.tcc.sprint_quiz.service.impl;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.etec.tcc.sprint_quiz.api.exception.CategoriaQuestaoNotFoundException;
import com.etec.tcc.sprint_quiz.model.CategoriaQuestao;
import com.etec.tcc.sprint_quiz.repository.CategoriaQuestaoRepository;
import com.etec.tcc.sprint_quiz.service.CategoriaQuestaoService;

@Service
@Transactional
public class CategoriaQuestaoServiceImp implements CategoriaQuestaoService {


    @Autowired
    private CategoriaQuestaoRepository categoriaQuestaoRepository;

    @Override
    public CategoriaQuestao getById(@PathVariable Long id) {
        return categoriaQuestaoRepository.findById(id)
                .orElseThrow(() -> new CategoriaQuestaoNotFoundException("id:" + id));
    }
    
    @Override
    public List<CategoriaQuestao> getAll(){
        return categoriaQuestaoRepository.findAll();
        		
    }
    

    @Override
    public List<CategoriaQuestao> getByTitutlo(@PathVariable("titulo") String titulo){
        return categoriaQuestaoRepository.findAllByTituloContainingIgnoreCase(titulo);

    }

    @Override
    public List<CategoriaQuestao> getAllByDescricao(@PathVariable("descricao") String descricao){
        return categoriaQuestaoRepository.findAllByDescricaoContainingIgnoreCase(descricao);
    }

//    @Override
//    public CategoriaQuestao postCategoriaQuestao(@Valid @RequestBody CategoriaQuestao categoria){
//        return categoriaQuestaoRepository.save(categoria);
//    }
//
//    @Override
//    public CategoriaQuestao putCategoriaQuestao(@Valid @RequestBody CategoriaQuestao categoria){
//        return categoriaQuestaoRepository.findById(categoria.getId())
//                .map(c ->categoriaQuestaoRepository.save(categoria))
//                .orElseThrow(() -> new CategoriaQuestaoNotFoundException("id:" + categoria.getId()));
//    }
//
//    @Override
//    public void deleteCategoriaQuestao(@PathVariable Long id){
//         categoriaQuestaoRepository.findById(id)
//                .map(c -> {
//                    categoriaQuestaoRepository.delete(c);
//                    return ResponseEntity.noContent().build();
//                }).orElseThrow(() -> new CategoriaQuestaoNotFoundException("id:" + id));
//
//    }
    
  @Override
  public CategoriaQuestao post(@Valid @RequestBody CategoriaQuestao categoria){
      return categoriaQuestaoRepository.save(categoria);
  }

  @Override
  public CategoriaQuestao put(@Valid @RequestBody CategoriaQuestao categoria){
      return categoriaQuestaoRepository.findById(categoria.getId())
              .map(c ->categoriaQuestaoRepository.save(categoria))
              .orElseThrow(() -> new CategoriaQuestaoNotFoundException("id:" + categoria.getId()));
  }

  @Override
  public void delete(@PathVariable Long id){
       categoriaQuestaoRepository.findById(id)
              .map(c -> {
                  categoriaQuestaoRepository.delete(c);
                  return ResponseEntity.noContent().build();
              }).orElseThrow(() -> new CategoriaQuestaoNotFoundException("id:" + id));

  }





	
}
