package com.etec.tcc.sprint_quiz.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.etec.tcc.sprint_quiz.exception.CategoriaProvaNotFoundException;
import com.etec.tcc.sprint_quiz.model.CategoriaProva;
import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.model.QuestaoProva;
import com.etec.tcc.sprint_quiz.repository.CategoriaProvaRepository;
import com.etec.tcc.sprint_quiz.repository.QuestaoProvaRepository;

@Service
public class CategoriaProvaServiceImp implements CategoriaProvaService {

	@Autowired
	private CategoriaProvaRepository categoriaProvaRepository;
	
	@Autowired
	private QuestaoProvaRepository questaoProvaRepository;

	
	

	public CategoriaProva getById(@PathVariable Long id) {
		return categoriaProvaRepository.findById(id).orElseThrow(() -> new CategoriaProvaNotFoundException());
	}

	public List<CategoriaProva> getAll() { 
		return categoriaProvaRepository.findAll(); 
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
		
		List<QuestaoProva> listaQuestaoProva = new ArrayList<>();
		
		for(Long l : lista) {
			listaQuestaoProva.addAll(questaoProvaRepository.findAllByProvaId(l));
		}
		
		questaoProvaRepository.deleteAll(listaQuestaoProva);
		
		
		categoriaProvaRepository.delete(cp);
	}
	
	

}
