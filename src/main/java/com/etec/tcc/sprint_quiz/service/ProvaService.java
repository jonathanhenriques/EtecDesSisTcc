package com.etec.tcc.sprint_quiz.service;

import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.model.dto.ProvaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProvaService {

    ResponseEntity<ProvaDTO> postProva(@RequestBody ProvaDTO prova);
}
