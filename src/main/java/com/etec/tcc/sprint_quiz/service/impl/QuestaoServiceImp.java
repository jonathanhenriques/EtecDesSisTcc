package com.etec.tcc.sprint_quiz.service.impl;

import com.etec.tcc.sprint_quiz.exception.CategoriaQuestaoNotFoundException;
import com.etec.tcc.sprint_quiz.exception.QuestaoNotFoundException;
import com.etec.tcc.sprint_quiz.exception.UsuarioNotFoundException;
import com.etec.tcc.sprint_quiz.model.Alternativa;
import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;
import com.etec.tcc.sprint_quiz.repository.CategoriaQuestaoRepository;
import com.etec.tcc.sprint_quiz.repository.QuestaoRepository;
import com.etec.tcc.sprint_quiz.repository.UsuarioRepository;
import com.etec.tcc.sprint_quiz.service.AlternativaService;
import com.etec.tcc.sprint_quiz.service.QuestaoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
//@RequiredArgsConstructor
@Service
@Transactional
public class QuestaoServiceImp implements QuestaoService {

	@Autowired
	private QuestaoRepository questaoRepository;

	@Autowired
	private CategoriaQuestaoRepository categoriaQuestaoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private AlternativaRepository alternativaRepository;

	@Autowired
	@Lazy
	private AlternativaService alternativaService;

	@Override
	public List<Questao> getAll() {
		return questaoRepository.findAll();
	}

	@Override
	public Questao getById(@PathVariable Long id) {
		return questaoRepository.findById(id).map(q -> q)
				.orElseThrow(() -> new QuestaoNotFoundException(id.toString()));
	}

	@Override
	public List<Questao> getAllByTexto(@PathVariable String texto) {
		return questaoRepository.findAllByTextoContainingIgnoreCase(texto);
	}

	@Override
	public List<Questao> getAllByInstituicao(@PathVariable String instituicao) {
		return questaoRepository.findAllByInstituicaoContainingIgnoreCase(instituicao);
	}

	@Override
	public List<Questao> findAllByAno(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ano) {
		return questaoRepository.findAllByAno(ano);
	}

	@Override
	public List<Questao> findAllByAnoInicialFinal(@PathVariable LocalDate anoInicial, LocalDate anoFinal) {
		return questaoRepository.findAllByAnoBetween(anoInicial, anoFinal);
	}

	@Override
	public List<Questao> findAllByAntesAno(@PathVariable LocalDate ano) {
		return questaoRepository.findAllByAnoBefore(ano);
	}

	@Override
	public List<Questao> getQuestoesByCriadorId(@PathVariable Long id) {
		return usuarioRepository.findById(id).map(u -> questaoRepository.findAllByCriadorId(id))
				.orElseThrow(() -> new UsuarioNotFoundException(id.toString()));
	}

	@Override
	public Questao postQuestao(@Valid @RequestBody Questao questao) {
//		if (usuarioRepository.existsById(questao.getCriador().getId())) {
////            Questao q =  salvaQuestao(questao).getBody();
//			Alternativa a = questao.getResposta();
//			List<Alternativa> lista = new ArrayList<Alternativa>();
////            lista.add(a);
//			questao.setAlternativas(lista);
//			questao.getAlternativas().add(a);
//			return salvaQuestao(questao);
//
//		}
		usuarioRepository.findById(questao.getCriador().getId())
				.orElseThrow(() -> new UsuarioNotFoundException(questao.getCriador().getId().toString()));
		return categoriaQuestaoRepository.findById(questao.getCategoria().getId()) 
				.map(c -> questaoRepository.save(questao))
				.orElseThrow(() -> new CategoriaQuestaoNotFoundException(questao.getCategoria().getId().toString()));

	}

	private Questao salvaQuestao(Questao questao) {
		usuarioRepository.findById(questao.getCriador().getId())
				.orElseThrow(() -> new UsuarioNotFoundException(questao.getCriador().getId().toString()));
		return categoriaQuestaoRepository.findById(questao.getCategoria().getId())
				.map(c -> questaoRepository.save(questao))
				.orElseThrow(() -> new CategoriaQuestaoNotFoundException(questao.getCategoria().getId().toString()));
	}

	@Transactional
	public Questao salvarQuestaoComAlternativa(@RequestBody Questao questao) {
//        List<Alternativa> alternativas = questao.getAlternativas();
//        questao.setAlternativas(new ArrayList<Alternativa>());
//        postQuestao(questao);
//
//        List<Alternativa> listaAlternativasComQuestao = alternativas.stream().map(a -> {
//            a.setQuestao(questao);
//            return a;
//        }).collect(Collectors.toList());
//
//        alternativaService.postListaAlternativasComQuestaoSalva(listaAlternativasComQuestao);

		return questao;
	}

	@Override
	public Questao putQuestao(@Valid @RequestBody Questao questao) {
		return questaoRepository.findById(questao.getId()).map(q -> questaoRepository.save(questao))
				.orElseThrow(() -> new QuestaoNotFoundException(questao.getId().toString()));
	}

	@Override
	public void deleteQuestao(@PathVariable Long id) {
		questaoRepository
				.delete(questaoRepository.findById(id).orElseThrow(() -> new QuestaoNotFoundException(id.toString())));
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
