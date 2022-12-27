package com.etec.tcc.sprint_quiz.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.etec.tcc.sprint_quiz.exception.CategoriaProvaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.ProvaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.UsuarioNotFoundException;
import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.repository.CategoriaProvaRepository;
import com.etec.tcc.sprint_quiz.repository.ProvaRepository;
import com.etec.tcc.sprint_quiz.repository.QuestaoProvaRepository;
import com.etec.tcc.sprint_quiz.repository.QuestaoRepository;
import com.etec.tcc.sprint_quiz.repository.UsuarioRepository;
import com.etec.tcc.sprint_quiz.service.ProvaService;

@Service
@Transactional
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


    public List<Prova> getAll() {
        return provaRepository.findAll();
    } 

    public Prova getById(Long id) {
//    	Optional<Prova> prova = provaRepository.findById(id);
//    	return prova.orElseThrow(() -> new ProvaNotFoundException(id.toString()));
//    	
        return provaRepository.findById(id)
                .orElseThrow(() -> new ProvaNotFoundException(id.toString()));
    }

    public List<Prova> getByCriadorId(Long id) {
        return usuarioRepository.findById(id)
                .map(u -> provaRepository.findAllByUsuarioId(id)
                ).orElseThrow(() -> new UsuarioNotFoundException(id.toString()));
    }

    public List<Prova> getAllByNome(String nome) {
        return provaRepository.findAllByNomeContainingIgnoreCase(nome);
    }

    public List<Prova> getAllByDescricao(String descricao) {
        return provaRepository.findAllByDescricaoContainingIgnoreCase(descricao);
    }


    @Override
//    @Transactional
    public Prova post(Prova prova) {
    	
//    	usuarioRepository.findById(prova.getUsuario().getId())
//    	.orElseThrow(() -> new UsuarioNotFoundException(prova.getUsuario().getId().toString()));
    	
//      return categoriaProvaRepository.findById(prova.getCategoria().getId())
//      .map(c -> ResponseEntity.ok(provaRepository.save(prova)))
//      .orElseThrow(() -> new CategoriaProvaNotFoundException());
    	
    	
    	if(usuarioRepository.existsById(prova.getUsuario().getId())) {
    		
    		return categoriaProvaRepository.findById(prova.getCategoria().getId())
                    .map(c -> provaRepository.save(prova))
                    .orElseThrow(() -> new CategoriaProvaNotFoundException());
    		
    	} else 
    		throw new UsuarioNotFoundException(prova.getUsuario().getId().toString());
    	 
    }

    @Override
    public Prova put(Prova prova) {
//        if (categoriaProvaRepository.existsById(prova.getCategoria().getId()))
//            return ResponseEntity.ok(provaRepository.save(prova));
//
//        throw new RegraNegocioException("Categoria não encontrada! | id:" + prova.getCategoria().getId());

    	categoriaProvaRepository.findById(prova.getCategoria().getId()).orElseThrow(() -> new ProvaNotFoundException());
    	usuarioRepository.findById(prova.getUsuario().getId()).orElseThrow(() -> new UsuarioNotFoundException(prova.getUsuario().getId().toString()));
    	
    	return provaRepository.save(prova);
    	
    	
//        return categoriaProvaRepository.findById(prova.getCategoria().getId())
//                .map(c -> ok(provaRepository.save(prova))
//                .orElseThrow(() -> new CategoriaProvaNotFoundException())); 
    	
    }


    public void delete(Long id) {
        Prova prova =  provaRepository.findById(id).orElseThrow(() -> new ProvaNotFoundException(id.toString()));
         
         provaRepository.delete((prova));
                
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
