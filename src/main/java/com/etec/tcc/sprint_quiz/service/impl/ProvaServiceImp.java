package com.etec.tcc.sprint_quiz.service.impl;

import java.util.*;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.etec.tcc.sprint_quiz.api.exception.CategoriaProvaNotFoundException;
import com.etec.tcc.sprint_quiz.api.exception.ProvaNotFoundException;
import com.etec.tcc.sprint_quiz.api.exception.UsuarioNotFoundException;
import com.etec.tcc.sprint_quiz.model.*;
import com.etec.tcc.sprint_quiz.model.dto.ProvaDTO;
import com.etec.tcc.sprint_quiz.model.dto.ProvaResponse;
import com.etec.tcc.sprint_quiz.model.dto.UsuarioSimplificadoDTO;
import com.etec.tcc.sprint_quiz.repository.*;
import com.etec.tcc.sprint_quiz.api.assembler.MapperAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.etec.tcc.sprint_quiz.service.ProvaService;

@Service
@RequiredArgsConstructor
@Transactional
public class ProvaServiceImp implements ProvaService {

    private final ProvaRepository provaRepository;

    private final CategoriaProvaRepository categoriaProvaRepository;

    private final UsuarioRepository usuarioRepository;

    private final QuestaoRepository questaoRepository;

    private final MapperAssembler mapperAssembler;


    public Page<ProvaResponse> getAll(Pageable pageable) {
        Page<Prova> provas = provaRepository.findAll(pageable);
        List<ProvaResponse> response = provas.getContent().stream().map(provaResponse -> {
            return mapperAssembler.converteToProvaResponse(provaResponse);
        }).collect(Collectors.toList());

        Page<ProvaResponse> pageProvaResponse =
                new PageImpl<>(response, pageable, provas.getTotalElements());
        return pageProvaResponse;

    }

    public Prova getById(Long id) {
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

    public List<Prova> getAllByCategoriaProva(String categoria) {
        return provaRepository.findAllByCategoriaTituloContainingIgnoreCase(categoria);
    }

    public List<Prova> findAllByCategoriaId(Long categoriaProvaId) {
        return provaRepository.findAllByCategoriaId(categoriaProvaId);
    }


    @Override
//    @Transactional
    public ProvaDTO post(ProvaDTO dto) {


        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario()).orElseThrow(UsuarioNotFoundException::new);

        CategoriaProva categoriaProva = categoriaProvaRepository.findById(dto.getIdCategoria())
                .orElseThrow(CategoriaProvaNotFoundException::new);


        Prova provaRequest = new Prova();
        provaRequest.setId(dto.getId());
        provaRequest.setNome(dto.getNome());
        provaRequest.setDescricao(dto.getDescricao());
        provaRequest.setDuracao(dto.getDuracao());
        provaRequest.setUsuario(usuario);
        provaRequest.setInstituicao(dto.getInstituicao());
        provaRequest.setCategoria(categoriaProva);
//        }

        return mapperAssembler.converteProvaParaProvaDTO(provaRepository.save(provaRequest));


    }


    @Override
    public ProvaResponse put(ProvaDTO dto) {
        CategoriaProva categoriaProva = categoriaProvaRepository.findById(dto.getIdCategoria()).orElseThrow(() -> new ProvaNotFoundException());
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario()).orElseThrow(() -> new UsuarioNotFoundException(dto.getIdUsuario().toString()));
        Prova provaRecuperada = provaRepository.findById(dto.getId()).orElseThrow(ProvaNotFoundException::new);

        Prova provaRequest = new Prova();
        provaRequest.setId(dto.getId());
        provaRequest.setNome(dto.getNome());
        provaRequest.setDescricao(dto.getDescricao());
        provaRequest.setDuracao(dto.getDuracao());
        provaRequest.setUsuario(usuario);
        provaRequest.setInstituicao(dto.getInstituicao());
        provaRequest.setCategoria(categoriaProva);
        provaRequest.setQuestoes(provaRecuperada.getQuestoes());

        return mapperAssembler.converteToProvaResponse(provaRepository.save(provaRequest));

    }


    public void delete(Long id) {
        Prova prova =  provaRepository.findById(id).orElseThrow(() -> new ProvaNotFoundException(id.toString()));

         provaRepository.delete((prova));
    }

    public Prova adicionarQuestaoEmProva(Prova prova) {


        List<Questao> listaQuestoes = new ArrayList<>(prova.getQuestoes());
        List<Questao> novasQuestoes = new ArrayList<>();
        for(int j = 0; j < prova.getQuestoes().size();j++) {
            Optional<Questao> questao = Optional.ofNullable(listaQuestoes.get(j));
            questao = questaoRepository.findById(questao.get().getId());
            novasQuestoes.add(questao.get());
        }
        Prova provaSalvar = provaRepository.findById(prova.getId()).orElseThrow(ProvaNotFoundException::new);
        provaSalvar.setQuestoes(null);
        Set<Questao> listaQuestoesSalvar = new HashSet<>(novasQuestoes);
        provaSalvar.setQuestoes(listaQuestoesSalvar);

//        String nomeProva = provaRepository.findById(prova.getId()).get().getNome();
//        provaSalvar.setNome(nomeProva);
        return provaRepository.save(provaSalvar);
    }

}
