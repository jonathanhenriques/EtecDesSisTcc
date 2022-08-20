package com.etec.tcc.sprint_quiz.service;

import com.etec.tcc.sprint_quiz.exception.CategoriaQuestaoNaoEncontradaException;
import com.etec.tcc.sprint_quiz.exception.QuestaoNotFoundException;
import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.QuestaoProva;
import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;
import com.etec.tcc.sprint_quiz.repository.CategoriaQuestaoRepository;
import com.etec.tcc.sprint_quiz.repository.QuestaoRepository;
import com.etec.tcc.sprint_quiz.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

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


    @GetMapping
    public ResponseEntity<List<Questao>> getAll() {
        return ResponseEntity.ok(questaoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Questao> getById(@PathVariable Long id) {
        return questaoRepository.findById(id)
                .map(q -> ResponseEntity.ok(q))
                .orElseThrow(() -> new QuestaoNotFoundException(id.toString()));
    }

    @GetMapping("/texto/{texto}")
    public ResponseEntity<List<Questao>> getAllByTexto(@PathVariable String texto) {
        return ResponseEntity.ok(questaoRepository.findAllByTextoContainingIgnoreCase(texto));
    }

    @GetMapping("/instituicao/{instituicao}")
    public ResponseEntity<List<Questao>> getAllByInstituicao(@PathVariable String instituicao) {
        return ResponseEntity.ok(questaoRepository.findAllByInstituicaoContainingIgnoreCase(instituicao));
    }

    @GetMapping("/ano/{ano}")
    public ResponseEntity<List<Questao>> findAllByAno(@PathVariable
                                                      @DateTimeFormat(
                                                              iso = DateTimeFormat.ISO.DATE)
                                                              LocalDate ano) {
        return ResponseEntity.ok(questaoRepository.findAllByAno(ano));
    }

    @GetMapping("/ano/entre/{anoInicial}/{anoFinal}")
    public ResponseEntity<List<Questao>> findAllByAnoInicialFinal(@PathVariable LocalDate anoInicial, LocalDate anoFinal) {
        return ResponseEntity.ok(questaoRepository.findAllByAnoBetween(anoInicial, anoFinal));
    }

    @GetMapping("/ano/antes/{ano}")
    public ResponseEntity<List<Questao>> findAllByAntesAno(@PathVariable LocalDate ano) {
        return ResponseEntity.ok(questaoRepository.findAllByAnoBefore(ano));
    }


    @Override
    public ResponseEntity<Questao> postQuestao(@Valid @RequestBody Questao questao) {
        return categoriaQuestaoRepository.findById(questao.getCategoria().getId())
                .map(c ->
                        ResponseEntity.status(HttpStatus.CREATED).body(questaoRepository.save(questao))
                ).orElseThrow(() -> new CategoriaQuestaoNaoEncontradaException(questao.getCategoria().getId().toString()));


    }

    @Override
    public ResponseEntity<Questao> putQuestao(@Valid @RequestBody Questao questao) {
        return questaoRepository.findById(questao.getId())
                .map(q -> ResponseEntity.ok(questaoRepository.save(questao)))
                .orElseThrow(() -> new QuestaoNotFoundException(questao.getId().toString()));
    }

    @Override
    public ResponseEntity<?> deleteQuestao(@PathVariable Long id) {
        return questaoRepository.findById(id)
                .map(q -> ResponseEntity.notFound().build())
                .orElseThrow(() -> new QuestaoNotFoundException(id.toString()));
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
