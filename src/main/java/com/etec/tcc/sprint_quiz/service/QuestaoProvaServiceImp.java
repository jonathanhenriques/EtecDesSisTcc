package com.etec.tcc.sprint_quiz.service;

import com.etec.tcc.sprint_quiz.exception.ProvaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.RegraNegocioException;
import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.model.QuestaoProva;
import com.etec.tcc.sprint_quiz.repository.ProvaRepository;
import com.etec.tcc.sprint_quiz.repository.QuestaoProvaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestaoProvaServiceImp implements QuestaoProvaService {

    @Autowired
    QuestaoProvaRepository questaoProvaRepository;

    @Autowired
    ProvaRepository provaRepository;

    @Override
    public ResponseEntity<QuestaoProva> postQuestaoProva(@RequestBody QuestaoProva questaoProva,
                                                         @PathVariable("id") Long id) {
        Prova prova = provaRepository.findById(id)
                .orElseThrow(() -> new ProvaNotFoundException(id.toString()));

        questaoProva.setProva(prova);
        return ResponseEntity.status(HttpStatus.CREATED).body(questaoProvaRepository.save(questaoProva));
    }

    @Override
    public ResponseEntity<List<QuestaoProva>> postListaQuestaoProva(@RequestBody List<QuestaoProva> listaQuestaoProva,
                                                                    @PathVariable("id") Long id) {

        Prova prova = provaRepository.findById(id)
                .orElseThrow(() -> new ProvaNotFoundException(id.toString()));

//        List<QuestaoProva> listaQuestaoProvaManipulada = listaQuestaoProva.stream().map(
//                qp -> {
//                     qp.setProva(prova);
//                    return qp;
//                }
//        ).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.CREATED).body(questaoProvaRepository.saveAll(listaQuestaoProva));
    }

    @Override
    public ResponseEntity<?> deleteQuestaoProva(@PathVariable("id") Long id){
        return questaoProvaRepository.findById(id).map(
                q -> {
                    questaoProvaRepository.delete(q);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                }
        ).orElseThrow(() -> new RegraNegocioException("id não encontrado"));

    }


}
