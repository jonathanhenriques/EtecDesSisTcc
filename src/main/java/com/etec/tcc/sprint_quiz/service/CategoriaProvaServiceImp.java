package com.etec.tcc.sprint_quiz.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.etec.tcc.sprint_quiz.exception.CategoriaProvaNotFoundException;
import com.etec.tcc.sprint_quiz.model.CategoriaProva;
import com.etec.tcc.sprint_quiz.repository.CategoriaProvaRepository;

@Service
public class CategoriaProvaServiceImp implements CategoriaProvaService {

	@Autowired
	private CategoriaProvaRepository categoriaProvaRepository;

	
	

	public CategoriaProva getById(@PathVariable Long id) {
		return categoriaProvaRepository.findById(id).orElseThrow(() -> new CategoriaProvaNotFoundException());
	}

	public List<CategoriaProva> getAll() { 
		return categoriaProvaRepository.findAll(); 
	}

	public CategoriaProva postCategoriaProva(@Valid @RequestBody CategoriaProva categoria) {
		return categoriaProvaRepository.save(categoria);
	}

	public CategoriaProva putCategoriaProva(@Valid @RequestBody CategoriaProva categoria) {
		return categoriaProvaRepository.findById(categoria.getId()).map(c -> categoriaProvaRepository.save(categoria))
				.orElseThrow(() -> new CategoriaProvaNotFoundException(categoria.getId().toString()));
	} 
 
//	public CategoriaProva patchCategoriaProvaTitulo(@RequestBody CategoriaProva categoria) {
//		return categoriaProvaRepository.findById(categoria.getId()).map(c -> {
//			c.setTitulo(categoria.getTitulo());
//			return categoriaProvaRepository.save(c);
//		}).orElseThrow(() -> new CategoriaQuestaoNaoEncontradaException());
//	} 

	public void deletetaCategoriaProva(@PathVariable Long id) {
		CategoriaProva cp = categoriaProvaRepository.findById(id)
				.orElseThrow(() -> new CategoriaProvaNotFoundException(id.toString()));
		categoriaProvaRepository.delete(cp);
	}
}
