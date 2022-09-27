package com.etec.tcc.sprint_quiz.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.etec.tcc.sprint_quiz.model.Prova;

public interface ProvaService {

    List<Prova> getAll();

    Prova getByIdProva(@PathVariable Long id);

    List<Prova> getAllByNome(String nome);

    List<Prova> getAllByDescricao(String descricao);

    List<Prova> getByCriadorId(Long id);

    Prova postProva( Prova prova);

    Prova putProva(Prova prova);

//    ResponseEntity<?> deleteProva(Long id);
//    <Object> Object deleteProva(Long id);
}
