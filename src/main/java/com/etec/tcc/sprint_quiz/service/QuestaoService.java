package com.etec.tcc.sprint_quiz.service;

import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.dto.QuestaoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;


public interface QuestaoService {

    ResponseEntity<Questao> postQuestao(@RequestBody Questao dto);
}
