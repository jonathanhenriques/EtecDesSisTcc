package com.etec.tcc.sprint_quiz.service;

import com.etec.tcc.sprint_quiz.exception.CategoriaProvaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.CategoriaQuestaoNaoEncontradaException;
import com.etec.tcc.sprint_quiz.model.CategoriaProva;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface CategoriaProvaService {



    public ResponseEntity<CategoriaProva> getById(@PathVariable Long id);


    public ResponseEntity<List<CategoriaProva>> getAll();


    public ResponseEntity<CategoriaProva> postCategoriaProva(@Valid @RequestBody CategoriaProva categoria);


    public ResponseEntity<CategoriaProva> putCategoriaProva(@Valid @RequestBody CategoriaProva categoria);


    public ResponseEntity<CategoriaProva> patchCategoriaProvaTitulo(@RequestBody CategoriaProva categoria);


    public ResponseEntity<?> deletetaoCategoriaProva(@PathVariable Long id);


}
