package com.etec.tcc.sprint_quiz.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.etec.tcc.sprint_quiz.model.dto.CategoriaProvaComProvasDTO;
import com.etec.tcc.sprint_quiz.model.dto.CategoriaProvaDTO;
import com.etec.tcc.sprint_quiz.model.dto.ProvaDTO;
import com.etec.tcc.sprint_quiz.service.ProvaService;
import com.etec.tcc.sprint_quiz.util.MapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.etec.tcc.sprint_quiz.exception.CategoriaProvaNotFoundException;
import com.etec.tcc.sprint_quiz.model.CategoriaProva;
import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.repository.CategoriaProvaRepository;
import com.etec.tcc.sprint_quiz.service.CategoriaProvaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class CategoriaProvaServiceImp implements CategoriaProvaService {

	private final CategoriaProvaRepository categoriaProvaRepository;

	private final ProvaService provaService;

	private final MapperService mapperService;


	public CategoriaProvaComProvasDTO getById(@PathVariable Long id) {
		CategoriaProva categoria = categoriaProvaRepository.findById(id).orElseThrow(CategoriaProvaNotFoundException::new);
		return getByIdComProvas(categoria.getId());
	}


	@Override
	public List<CategoriaProvaDTO> findAllDTO() {
		List<CategoriaProva> listaCategoriaProva = categoriaProvaRepository.findAll();
		return mapperService
				.converteListaCategoriaProvaParaListaCategoriaProvaDTO(listaCategoriaProva);
	}


	public CategoriaProvaDTO post(@Valid @RequestBody CategoriaProvaDTO categoria) {
		CategoriaProva categoriaProva = new CategoriaProva();

		categoriaProva.setTitulo(categoria.getTitulo());
		categoriaProva.setDescricao(categoria.getDescricao());

		 return mapperService.converteCategoriaProvaParaCategoriaProvaDTO(categoriaProvaRepository.save(categoriaProva));
	}

	public CategoriaProvaComProvasDTO put(@Valid @RequestBody CategoriaProvaDTO categoria) {
		CategoriaProva categoriaProva = categoriaProvaRepository.findById(categoria.getId())
				.orElseThrow(() -> new CategoriaProvaNotFoundException(categoria.getId().toString()));
		List<ProvaDTO> provas = mapperService.converteListDeProvaParaListDeProvaDTO(provaService.findAllByCategoriaId(categoriaProva.getId()));

		return new CategoriaProvaComProvasDTO(
				categoriaProva.getId(),
				categoriaProva.getTitulo(),
				categoriaProva.getDescricao(),
				provas

		);
	}
 

	public void delete(@PathVariable Long id) {
		CategoriaProva cp = categoriaProvaRepository.findById(id)
				.orElseThrow(() -> new CategoriaProvaNotFoundException(id.toString()));

		List<Prova> listaProvasDaCategoria = cp.getProvas();
		List<Long> lista = new ArrayList<>();

		for(Prova p : listaProvasDaCategoria) {
			lista.add(p.getId());
		}


		categoriaProvaRepository.delete(cp);
	}

	@Override
	public CategoriaProvaComProvasDTO getByIdComProvas(Long id) {
		CategoriaProva categoriaProva = categoriaProvaRepository
				.findById(id).orElseThrow(CategoriaProvaNotFoundException::new);

		List<ProvaDTO> listaProvasDTO = mapperService.converteListDeProvaParaListDeProvaDTO(categoriaProva.getProvas());

		return new CategoriaProvaComProvasDTO(
				categoriaProva.getId(),
				categoriaProva.getTitulo(),
				categoriaProva.getDescricao(),
				listaProvasDTO
		);
	}


}
