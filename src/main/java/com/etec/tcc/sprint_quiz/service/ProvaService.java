package com.etec.tcc.sprint_quiz.service;

import java.util.List;

import com.etec.tcc.sprint_quiz.model.dto.ProvaComQuestaoDTO;
import com.etec.tcc.sprint_quiz.model.dto.ProvaDTO;
import com.etec.tcc.sprint_quiz.model.dto.ProvaResponse;
import org.springframework.web.bind.annotation.PathVariable;

import com.etec.tcc.sprint_quiz.model.Prova;

public interface ProvaService{

    List<Prova> getAll();

    Prova getById(@PathVariable Long id);

    List<Prova> getAllByNome(String nome);

    List<Prova> getAllByDescricao(String descricao);

    List<Prova> getByCriadorId(Long id);

    Prova post( Prova prova);

    Prova adicionarQuestaoEmProva(Prova prova);

    Prova put(Prova prova);

//    Prova converteProvaComQuestaoDTOToProva(ProvaComQuestaoDTO dto);
//
//    ProvaResponse converteToProvaResponse(Prova prova);
//
//    List<ProvaDTO> converteListDeProvaParaListDeProvaDTO(List<Prova> provas);

//    ResponseEntity<?> deleteProva(Long id);
//    <Object> Object deleteProva(Long id);
    
    void delete(Long id);
    
    
}
