package com.etec.tcc.sprint_quiz.service;

import com.etec.tcc.sprint_quiz.model.Prova;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface ProvaService {

    ResponseEntity<List<Prova>> getAll();

    ResponseEntity<Prova> getByIdProva(@PathVariable Long id);

    ResponseEntity<List<Prova>> getAllByNome(@PathVariable String nome);

    ResponseEntity<List<Prova>> getAllByDescricao(@PathVariable String descricao);

    ResponseEntity<Prova> postProva(@Valid @RequestBody Prova prova);

    ResponseEntity<Prova> putProva(@Valid @RequestBody Prova prova);

    ResponseEntity<?> deleteProva(@PathVariable Long id);
}
