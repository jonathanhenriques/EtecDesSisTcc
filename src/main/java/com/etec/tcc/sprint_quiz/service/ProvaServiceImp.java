package com.etec.tcc.sprint_quiz.service;

import com.etec.tcc.sprint_quiz.exception.CategoriaProvaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.RegraNegocioException;
import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.QuestaoProva;
import com.etec.tcc.sprint_quiz.model.Usuario;
import com.etec.tcc.sprint_quiz.model.dto.ProvaDTO;
import com.etec.tcc.sprint_quiz.model.dto.QuestaoProvaDTO;
import com.etec.tcc.sprint_quiz.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProvaServiceImp implements ProvaService {

    @Autowired
    private ProvaRepository provaRepository;

    @Autowired
    private CategoriaProvaRepository categoriaProvaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private QuestaoProvaRepository questaoProvaRepository;

    @Autowired
    private QuestaoRepository questaoRepository;


    @Override
    @Transactional
    public ResponseEntity<Prova> postProva(Prova prova) {
            return ResponseEntity.status(HttpStatus.CREATED).body(provaRepository.save(prova));
    }

//    private List<QuestaoProva> converterLista(List<QuestaoProvaDTO> lista, Prova prova) {
////        if (lista.isEmpty())
////            throw new RegraNegocioException("Não é possível criar uma prova sem questões!");
//
//        return lista
//                .stream()
//                .map(dto -> {
//                    QuestaoProva questaoProva = new QuestaoProva();
//                    Questao questao = questaoRepository.findById(dto.getQuestao())
//                            .orElseThrow(() -> new RegraNegocioException("código da questao não encontrado!" + dto.getQuestao()));
//                    questaoProva.setQuestao(questao);
//                    questaoProva.setProva(prova);
//                    return questaoProva;
//
//                }).collect(Collectors.toList());
//    }
//
//    private List<QuestaoProva> trocaIdQuestaoProva(List<QuestaoProva> lista, Prova prova) {
////        List<Prova> pp = provaRepository.findAll();
//        return lista
//                .stream()
//                .map(questaoProva -> {
////                    questaoProvaRepository.findById(questaoProva.getId())
////                            .orElseThrow(() -> new RegraNegocioException("código da questão não encontrado! " + questaoProva.getQuestao().getId()));
//                    questaoProva.getProva().setId(prova.getId());
//                    return questaoProva;
//                }).collect(Collectors.toList());
//    }
}
