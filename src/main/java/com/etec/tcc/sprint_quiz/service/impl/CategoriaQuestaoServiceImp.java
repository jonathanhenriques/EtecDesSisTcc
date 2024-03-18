package com.etec.tcc.sprint_quiz.service.impl;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.etec.tcc.sprint_quiz.api.assembler.MapperAssembler;
import com.etec.tcc.sprint_quiz.model.dto.CategoriaProvaDTO;
import com.etec.tcc.sprint_quiz.model.dto.CategoriaQuestaoComQuestoesDTO;
import com.etec.tcc.sprint_quiz.model.dto.CategoriaQuestaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.etec.tcc.sprint_quiz.api.exception.CategoriaQuestaoNotFoundException;
import com.etec.tcc.sprint_quiz.model.CategoriaQuestao;
import com.etec.tcc.sprint_quiz.repository.CategoriaQuestaoRepository;
import com.etec.tcc.sprint_quiz.service.CategoriaQuestaoService;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoriaQuestaoServiceImp implements CategoriaQuestaoService {


    private final CategoriaQuestaoRepository categoriaQuestaoRepository;

    private final MapperAssembler mapperAssembler;



    @Override
    public CategoriaQuestaoComQuestoesDTO getById(@PathVariable Long id) {
        CategoriaQuestao categoriaQuestao = categoriaQuestaoRepository.findById(id)
                .orElseThrow(() -> new CategoriaQuestaoNotFoundException("id:" + id));

        return mapperAssembler.converteCategoriaQuestaoParaCategoriaQuestaoComQuestoesDTO(categoriaQuestao);
    }

    @Override
    public Page<CategoriaQuestaoDTO> getAll(Pageable pageable){
        Page<CategoriaQuestao> listaCategoriaQuestao = categoriaQuestaoRepository.findAll(pageable);

        List<CategoriaQuestaoDTO> listaCategoriaQuestaoDTO = mapperAssembler
                .converteListaCategoriaQuestaoParaListaCategoriaQuestaoDTO(listaCategoriaQuestao.getContent());

        Page<CategoriaQuestaoDTO> pageCategoriaQuestaoDTO =
                new PageImpl<>(listaCategoriaQuestaoDTO, pageable, listaCategoriaQuestao.getTotalElements());


        return pageCategoriaQuestaoDTO;

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
