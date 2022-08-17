package com.etec.tcc.sprint_quiz.service;

import com.etec.tcc.sprint_quiz.model.Prova;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProvaService {

    ResponseEntity<Prova> getByIdProva(@PathVariable Long id);

    ResponseEntity<Prova> postProva(@RequestBody Prova prova);
}
