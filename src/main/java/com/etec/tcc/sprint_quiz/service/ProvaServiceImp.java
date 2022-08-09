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
    public ResponseEntity<ProvaDTO> postProva(ProvaDTO dto) {

        return categoriaProvaRepository.findById(dto.getCategoria())
                .map(c -> {
                    Usuario usuario = usuarioRepository.findById(dto.getUsuario())
                            .orElseThrow(() -> new RegraNegocioException("código de cliente inválido!" + dto.getUsuario()));
                    Prova prova = ProvaDTO.converterEmProva(dto, usuario, c);

                    List<QuestaoProva> questaoProva = converterLista(dto.getQuestoes(), prova);
                    provaRepository.save(prova);
                   questaoProva = questaoProvaRepository.saveAll(questaoProva);
                    prova.setQuestoes(questaoProva);

                    ProvaDTO provaDTO = new ProvaDTO(prova);

                    return ResponseEntity.status(HttpStatus.CREATED).body(provaDTO);

                }).orElseThrow(() -> new CategoriaProvaNotFoundException());
    }

    private List<QuestaoProva> converterLista(List<QuestaoProvaDTO> lista, Prova prova) {
        if (lista.isEmpty())
            throw new RegraNegocioException("Não é possível criar uma prova sem questões!");

        return lista
                .stream()
                .map(dto -> {
                    QuestaoProva questaoProva = new QuestaoProva();
                    Questao questao = questaoRepository.findById(dto.getQuestao())
                            .orElseThrow(() -> new RegraNegocioException("código da questao não encontrado!" + dto.getQuestao()));
                    questaoProva.setQuestao(questao);
                    questaoProva.setProva(prova);
                    return questaoProva;

                }).collect(Collectors.toList());
    }
}
