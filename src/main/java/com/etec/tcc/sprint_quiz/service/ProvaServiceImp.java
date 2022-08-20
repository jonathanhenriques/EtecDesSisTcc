package com.etec.tcc.sprint_quiz.service;

import com.etec.tcc.sprint_quiz.exception.CategoriaProvaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.ProvaNotFoundException;
import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

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
    public ResponseEntity<Prova> getByIdProva(@PathVariable Long id) {
        return provaRepository.findById(id)
                .map(p -> ResponseEntity.ok(p))
                .orElseThrow(() -> new ProvaNotFoundException());
    }

    @Override
//    @Transactional
    public ResponseEntity<Prova> postProva(@Valid @RequestBody Prova prova) {
        return categoriaProvaRepository.findById(prova.getCategoria().getId())
                .map(c -> ResponseEntity.ok(provaRepository.save(prova)))
                .orElseThrow(() -> new CategoriaProvaNotFoundException());
    }

    @Override
    public ResponseEntity<Prova> putProva(@Valid @RequestBody Prova prova) {
//        if (categoriaProvaRepository.existsById(prova.getCategoria().getId()))
//            return ResponseEntity.ok(provaRepository.save(prova));
//
//        throw new RegraNegocioException("Categoria não encontrada! | id:" + prova.getCategoria().getId());

        return categoriaProvaRepository.findById(prova.getCategoria().getId())
                .map(c -> ResponseEntity.ok(provaRepository.save(prova)))
                .orElseThrow(() -> new CategoriaProvaNotFoundException());
    }


    public ResponseEntity<?> deleteProva(@PathVariable Long id) {
        return provaRepository.findById(id)
                .map(p -> {
                    provaRepository.delete(p);
                    return ResponseEntity.noContent().build();
                }).orElseThrow(() -> new ProvaNotFoundException());
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
