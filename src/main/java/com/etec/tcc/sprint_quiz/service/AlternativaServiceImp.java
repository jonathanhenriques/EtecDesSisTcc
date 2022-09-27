package com.etec.tcc.sprint_quiz.service;

import com.etec.tcc.sprint_quiz.exception.AlternativaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.QuestaoNotFoundException;
import com.etec.tcc.sprint_quiz.exception.RegraNegocioException;
import com.etec.tcc.sprint_quiz.model.Alternativa;
import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;
import com.etec.tcc.sprint_quiz.repository.QuestaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Service
public class AlternativaServiceImp implements  AlternativaService{

    @Autowired
    private AlternativaRepository alternativaRepository;

    @Autowired
    private QuestaoRepository questaoRepository;

    @Autowired
    private QuestaoService questaoService;

    @Override
    public ResponseEntity<List<Alternativa>> getAll(){
        return ResponseEntity.ok(alternativaRepository.findAll());
    } 

    @Override
    public ResponseEntity<Alternativa> findById(@PathVariable Long id){
        return alternativaRepository.findById(id)
                .map(alternativa -> ResponseEntity.ok(alternativa))
                .orElseThrow(() -> new AlternativaNotFoundException(id.toString()));
    }

    @Override
    public ResponseEntity<List<Alternativa>> postListaAlternativa(@Valid @RequestBody List<Alternativa> alternativas) {
            Questao questao = questaoRepository.findById(alternativas.get(0).getQuestao().getId())
                    .orElseThrow(() -> new RegraNegocioException("Questão não encontrada | id:" + alternativas.get(0).getQuestao().getId()));

            alternativas.forEach(a -> {
                questao.getAlternativas().add(a);
            });

            alternativaRepository.saveAll(alternativas);

//            questao.getAlternativas().add(alternativas);
            questaoService.putQuestao(questao);
            return ResponseEntity.status(HttpStatus.CREATED).body(alternativas);
    }

    public ResponseEntity<List<Alternativa>> postListaAlternativasComQuestaoSalva(@Valid @RequestBody List<Alternativa> alternativas) {
        Questao questao = alternativas.get(0).getQuestao();

        alternativas.forEach(a -> {
            questao.getAlternativas().add(a);
        });

        alternativaRepository.saveAll(alternativas);

        questaoService.putQuestao(questao);
        return ResponseEntity.status(HttpStatus.CREATED).body(alternativas);
    }

    @Override
    public ResponseEntity<Alternativa> postAlternativa(@Valid @RequestBody Alternativa alternativa){
//        Questao questao = questaoRepository.findById(alternativa.getQuestao().getId())
//                .orElseThrow(() -> new QuestaoNotFoundException(alternativa.getQuestao().getId().toString()));

        return  ResponseEntity.status(HttpStatus.CREATED).body(alternativaRepository.save(alternativa));
    }

    @Override
    public ResponseEntity<Alternativa> putAlternativa(@Valid @RequestBody Alternativa alternativa){
        return alternativaRepository.findById(alternativa.getId())
                .map(q -> ResponseEntity.ok(alternativaRepository.save(alternativa)))
                .orElseThrow(() -> new AlternativaNotFoundException(alternativa.getId().toString()));

    }

    @Override
    public ResponseEntity<?> deleteAlternativa(@PathVariable Long id){
        return alternativaRepository.findById(id)
                .map(alternativa -> {
                    alternativaRepository.delete(alternativa);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                }).orElseThrow(() -> new AlternativaNotFoundException(id.toString()));
    }

}
