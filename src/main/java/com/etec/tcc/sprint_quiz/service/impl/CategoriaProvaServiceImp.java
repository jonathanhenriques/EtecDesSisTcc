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

	@Autowired
	private CategoriaProvaRepository categoriaProvaRepository;

//	private final CategoriaProvaService categoriaProvaService;

	private final ProvaService provaService;

	private final MapperService mapperService;


	public CategoriaProva getById(@PathVariable Long id) {
		return categoriaProvaRepository.findById(id).orElseThrow(() -> new CategoriaProvaNotFoundException());
	}

//	public List<CategoriaProvaDTO> getAll() {
//		log.info("Obtendo todas as categoriaProva");
//		return categoriaProvaService.find();
//	}

	@Override
	public List<CategoriaProvaDTO> findAllDTO() {
		List<CategoriaProva> listaCategoriaProva = categoriaProvaRepository.findAll();
		List<CategoriaProvaDTO> listaDTO = mapperService
				.converteListaCategoriaProvaParaListaCategoriaProvaDTO(listaCategoriaProva);
		return listaDTO;
	}

	public List<CategoriaProva> getAllByTitulo(@PathVariable String titulo){
		return categoriaProvaRepository.findAllByTituloContainingIgnoreCase(titulo);
	}

	public CategoriaProva post(@Valid @RequestBody CategoriaProva categoria) {
		return categoriaProvaRepository.save(categoria);
	}

	public CategoriaProva put(@Valid @RequestBody CategoriaProva categoria) {
		return categoriaProvaRepository.findById(categoria.getId()).map(c -> categoriaProvaRepository.save(categoria))
				.orElseThrow(() -> new CategoriaProvaNotFoundException(categoria.getId().toString()));
	}
 
//	public CategoriaProva patchCategoriaProvaTitulo(@RequestBody CategoriaProva categoria) {
//		return categoriaProvaRepository.findById(categoria.getId()).map(c -> {
//			c.setTitulo(categoria.getTitulo());
//			return categoriaProvaRepository.save(c);
//		}).orElseThrow(() -> new CategoriaQuestaoNaoEncontradaException());
//	} 

	public void delete(@PathVariable Long id) {
		CategoriaProva cp = categoriaProvaRepository.findById(id)
				.orElseThrow(() -> new CategoriaProvaNotFoundException(id.toString()));

		List<Prova> listaProvasDaCategoria = cp.getProvas();
		List<Long> lista = new ArrayList<Long>();

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

		CategoriaProvaComProvasDTO dto = new CategoriaProvaComProvasDTO(
				categoriaProva.getId(),
				categoriaProva.getTitulo(),
				categoriaProva.getDescricao(),
				listaProvasDTO
		);

		return dto;
	}


}
