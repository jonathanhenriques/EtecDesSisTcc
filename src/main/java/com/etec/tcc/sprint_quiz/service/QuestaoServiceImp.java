package com.etec.tcc.sprint_quiz.service;

//import com.etec.tcc.sprint_quiz.model.dto.QuestaoDTO;
import com.etec.tcc.sprint_quiz.exception.RegraNegocioException;
import com.etec.tcc.sprint_quiz.model.Alternativa;
import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;
import com.etec.tcc.sprint_quiz.repository.CategoriaQuestaoRepository;
import com.etec.tcc.sprint_quiz.repository.QuestaoRepository;
import com.etec.tcc.sprint_quiz.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestaoServiceImp implements QuestaoService {

    @Autowired
    private QuestaoRepository questaoRepository;

    @Autowired
    private CategoriaQuestaoRepository categoriaQuestaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AlternativaRepository alternativaRepository;

    @Override
    public ResponseEntity<Questao> postQuestao(Questao questao) {
        if(categoriaQuestaoRepository.existsById(questao.getCategoria().getId()))
            return ResponseEntity.status(HttpStatus.CREATED).body(questaoRepository.save(questao));
        return ResponseEntity.badRequest().build();

    }

    @Override
    public ResponseEntity<Questao> putQuestao(@Valid @RequestBody Questao questao) {
        return questaoRepository.findById(questao.getId())
                .map(q -> ResponseEntity.ok(questaoRepository.save(questao)))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }








//    @Override
//    @Transactional
//    public ResponseEntity<Questao> salvarQuestao(@RequestBody Questao questao){
////        Prova idProva = questao.get
//        if(categoriaQuestaoRepository.existsById(questao.getCategoria().getId())){
//
//            //setando id da questao nas alternativas
////            Alternativa alternativa = new Alternativa();
//            List<Alternativa> listaAlternativas = questao.getAlternativas()
//                    .stream()
//                    .map(a -> {
//                        a.setQuestao(questao);
//                        return a;
//                    }).collect(Collectors.toList());
//            questao.setAlternativas(listaAlternativas);
//
//            //setando id da questao nas alternativas
//
//
//            questaoRepository.save(questao);
//            alternativaRepository.saveAll(listaAlternativas);
////            questao.setAlternativas(listaAlternativas);
//
//
//
//            return ResponseEntity.status(HttpStatus.CREATED).body(questao);
//
//        } else {
//            throw new RegraNegocioException("Categoria não encontrada! id:" + questao.getCategoria().getId());
//        }
//    }

//    private List<Alternativa>  salvarAlternativas(List<Alternativa> alternativas){
//        if(alternativas.isEmpty())
//            throw new RegraNegocioException("Não é possível criar uma questão sem alternativas");
//
//
//
//
//    }









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
