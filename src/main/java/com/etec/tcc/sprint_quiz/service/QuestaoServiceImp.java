package com.etec.tcc.sprint_quiz.service;

import com.etec.tcc.sprint_quiz.exception.RegraNegocioException;
import com.etec.tcc.sprint_quiz.model.CategoriaQuestao;
import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.Usuario;
//import com.etec.tcc.sprint_quiz.model.dto.QuestaoDTO;
import com.etec.tcc.sprint_quiz.repository.CategoriaQuestaoRepository;
import com.etec.tcc.sprint_quiz.repository.QuestaoRepository;
import com.etec.tcc.sprint_quiz.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class QuestaoServiceImp implements QuestaoService {

    @Autowired
    private QuestaoRepository questaoRepository;

    @Autowired
    private CategoriaQuestaoRepository categoriaQuestaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public ResponseEntity<Questao> postQuestao(Questao questao) {
        if(categoriaQuestaoRepository.existsById(questao.getCategoria().getId()))
            return ResponseEntity.status(HttpStatus.CREATED).body(questaoRepository.save(questao));
        return ResponseEntity.badRequest().build();

    }

//    public Questao converterParaQuestao(QuestaoDTO dto) {
//        Questao q = new Questao();
//
//        q.setId(dto.getId());
//        q.setInstituicao(dto.getInstituicao());
//        dto.setAno(String.valueOf(LocalDate.now()));
//
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
////        LocalDate ld = LocalDate.parse(dto.getAno(), formatter);
////        q.setAno(ld);
//
//        q.setTexto(dto.getTexto());
//        q.setOpcao_1(dto.getOpcao_1());
//        q.setOpcao_2(dto.getOpcao_2());
//        q.setOpcao_3(dto.getOpcao_3());
//        q.setOpcao_4(dto.getOpcao_4());
//        q.setOpcao_5(dto.getOpcao_5());
//        q.setResposta(dto.getResposta());
//
//        CategoriaQuestao cq = categoriaQuestaoRepository.findById(dto.getCategoria())
//                .orElseThrow(() -> new RegraNegocioException("Código da categoria da questão inválido!"));
//        q.setCategoria(cq);
//
//        Usuario u = usuarioRepository.findById(dto.getCriador())
//                .orElseThrow(() -> new RegraNegocioException("Código do criador(usuario) inválido!"));
//        q.setCriador(u);
//        return q;
//    }
}
