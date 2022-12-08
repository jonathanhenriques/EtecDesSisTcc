package com.etec.tcc.sprint_quiz.service;

import com.etec.tcc.sprint_quiz.model.QuestaoProva;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Service
public interface QuestaoProvaService {

	ResponseEntity<List<QuestaoProva>> findAllByProvaId(Long id);

	ResponseEntity<QuestaoProva> postQuestaoProva(QuestaoProva questaoProva, @PathVariable("id") Long id);

	ResponseEntity<List<QuestaoProva>> postListaQuestaoProva(List<QuestaoProva> listaQuestaoProva,
			@PathVariable("id") Long id);

	ResponseEntity<?> deleteQuestaoProva(@PathVariable("id") Long id);
}
