package com.etec.tcc.sprint_quiz.service;

import com.etec.tcc.sprint_quiz.model.Alternativa;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AlternativaService {

     ResponseEntity<List<Alternativa>> getAll();

     ResponseEntity<Alternativa> findById(@PathVariable Long id);

     ResponseEntity<List<Alternativa>> postListaAlternativa(@RequestBody List<Alternativa> alternativas);

     ResponseEntity<Alternativa> postAlternativa(@RequestBody Alternativa alternativa);

     ResponseEntity<?> deleteAlternativa(@PathVariable Long id);
}
