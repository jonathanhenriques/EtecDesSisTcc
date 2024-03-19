package com.etec.tcc.sprint_quiz.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.etec.tcc.sprint_quiz.api.exception.CategoriaProvaNotFoundException;
import com.etec.tcc.sprint_quiz.api.exception.ProvaNotFoundException;
import com.etec.tcc.sprint_quiz.api.exception.UsuarioNotFoundException;
import com.etec.tcc.sprint_quiz.model.*;
import com.etec.tcc.sprint_quiz.model.dto.ProvaDTO;
import com.etec.tcc.sprint_quiz.model.dto.ProvaResponse;
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
    public Prova put(Prova prova) {
        categoriaProvaRepository.findById(prova.getCategoria().getId()).orElseThrow(() -> new ProvaNotFoundException());
        usuarioRepository.findById(prova.getUsuario().getId()).orElseThrow(() -> new UsuarioNotFoundException(prova.getUsuario().getId().toString()));

        return provaRepository.save(prova);

    }


    public void delete(Long id) {
        Prova prova =  provaRepository.findById(id).orElseThrow(() -> new ProvaNotFoundException(id.toString()));

         provaRepository.delete((prova));
    }

}
