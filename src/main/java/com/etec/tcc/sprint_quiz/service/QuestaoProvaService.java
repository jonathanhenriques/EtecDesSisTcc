package com.etec.tcc.sprint_quiz.service;

import com.etec.tcc.sprint_quiz.model.QuestaoProva;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface QuestaoProvaService {



    public ResponseEntity<QuestaoProva> postQuestaoProva(@RequestBody QuestaoProva questaoProva,
                                                         @PathVariable("id") Long id);
}
