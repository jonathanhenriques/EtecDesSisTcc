package com.etec.tcc.sprint_quiz.service;

import com.etec.tcc.sprint_quiz.exception.ProvaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.QuestaoNotFoundException;
import com.etec.tcc.sprint_quiz.exception.RegraNegocioException;
import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.model.QuestaoProva;
import com.etec.tcc.sprint_quiz.repository.ProvaRepository;
import com.etec.tcc.sprint_quiz.repository.QuestaoProvaRepository;
import com.etec.tcc.sprint_quiz.repository.QuestaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
@Transactional
public class QuestaoProvaServiceImp implements QuestaoProvaService {

    @Autowired
    private QuestaoProvaRepository questaoProvaRepository;

    @Autowired
    private ProvaRepository provaRepository;

    @Autowired
    private QuestaoRepository questaoRepository;
    
    
    
    
   
    
    public ResponseEntity<List<QuestaoProva>> findAllByProvaId(Long id){
    	Prova p = provaRepository.findById(id)
    			.orElseThrow(() -> new ProvaNotFoundException(id.toString()));
    	
    	return ResponseEntity.ok(questaoProvaRepository.findAllByProvaId(id));
    }
    
    
    

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

        List<QuestaoProva> listaQuestaoProvaManipulada = listaQuestaoProva
                .stream()
                .map(questaoMap -> {
                    Long idQuestaoParaVerificar = questaoMap.getQuestao().getId();
                    questaoRepository.findById(idQuestaoParaVerificar)
                            .orElseThrow(() -> new QuestaoNotFoundException(idQuestaoParaVerificar.toString()));
                    questaoMap.setProva(prova);
                    return questaoMap;
                }).collect(Collectors.toList());


//        List<QuestaoProva> listaQuestaoProvaManipulada = listaQuestaoProva.stream().map(
//                qp -> {
//                     qp.setProva(prova);
//                    return qp;
//                }
//        ).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.CREATED).body(questaoProvaRepository.saveAll(listaQuestaoProvaManipulada));
    }

    @Override
    public ResponseEntity<?> deleteQuestaoProva(@PathVariable("id") Long id) {
        return questaoProvaRepository.findById(id).map(
                q -> {
                    questaoProvaRepository.delete(q);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                }
        ).orElseThrow(() -> new RegraNegocioException("id não encontrado"));

    }


}
