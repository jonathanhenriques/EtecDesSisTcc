package com.etec.tcc.sprint_quiz.service;

import com.etec.tcc.sprint_quiz.model.QuestaoProva;
import com.etec.tcc.sprint_quiz.repository.QuestaoProvaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QuestaoProvaImp implements QuestaoProvaService{

    @Autowired
    QuestaoProvaRepository questaoProvaRepository;

    @Override
    public QuestaoProva postQuestaoProva(QuestaoProva questaoProva) {

        return questaoProvaRepository.save(questaoProva);
    }
}
