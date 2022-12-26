package com.etec.tcc.sprint_quiz.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etec.tcc.sprint_quiz.model.Alternativa;
import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;
import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;
import com.etec.tcc.sprint_quiz.service.AlternativaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/alternativas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AlternativaController {

    @Autowired
    private AlternativaRepository alternativaRepository;

    @Autowired
    private AlternativaService alternativaService;
    
    @Autowired
	private ModelMapper modelMapper;


    @Operation(summary = "Obtem todas as alternativas")
    @GetMapping
    public ResponseEntity<List<Alternativa>> getAll() {
        return ResponseEntity.ok(alternativaService.getAll());
    }

    @Operation(summary = "Obtem uma alternativa pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<AlternativaDTO> getById(@PathVariable Long id) {
    	
    	Alternativa a = alternativaService.getById(id);
    	return ResponseEntity.ok(modelMapper.map(a, AlternativaDTO.class));
//    	AlternativaDTO dto = modelMapper.map(a, AlternativaDTO.class);
//    	paraDTO(a);
//    	return ResponseEntity.ok(paraDTO(a));
//    	return ResponseEntity.ok(new AlternativaDTO(a));
//    	return ResponseEntity.ok(alternativaService.getById(id));
    }
    
    
    
//    @Operation(summary = "Obtem uma alternativa pelo id")
//    @GetMapping("/{id}")
//    public ResponseEntity<AlternativaDTO> getById(@PathVariable Long id) {
//    	Alternativa a = alternativaService.getById(id);
////    	AlternativaDTO dto = modelMapper.map(a, AlternativaDTO.class);
//    	paraDTO(a);
////    	return ResponseEntity.ok(paraDTO(a));
//    	return ResponseEntity.ok(new AlternativaDTO(a));
////    	return ResponseEntity.ok(alternativaService.getById(id));
//    }
    
    
//    public AlternativaDTO paraDTO(Alternativa alternativa) {
//		AlternativaDTO dto = new AlternativaDTO();
//		dto.setId(alternativa.getId());
//    	dto.setTexto(alternativa.getTexto());
//    	dto.setFoto(alternativa.getFoto());		
//    	dto.setQuestaoTexto(alternativa.getQuestao().getId());
//    	return dto;
//	}
    
    
    public Alternativa paraAlternativa(AlternativaDTO dto) {
		var alternativa = new Alternativa();
    	alternativa.setTexto(dto.getTexto());
		alternativa.setFoto(dto.getFoto());
		
		var q = new Questao();
		q.setId(dto.getId());
		alternativa.setQuestao(q);
		return alternativa;
	}
    
    
    
    
    
    
    
    
    
    

    @Operation(summary = "Obtem alternativas pelo texto da alternativa")
    @GetMapping("/texto/{texto}")
    public ResponseEntity<List<Alternativa>> getAllByTexto(@PathVariable String texto) {
    	return ResponseEntity.ok(alternativaRepository.findAllByTextoContainingIgnoreCase(texto));
    }

    @Operation(summary = "cria várias alternativas")
    @PostMapping("/listaAlternativas")
    public ResponseEntity<List<Alternativa>> postListaAlternativa(@Valid @RequestBody List<Alternativa> alternativas) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alternativaService.postListaAlternativa(alternativas));
    }

    @Operation(summary = "cria uma nova alternativa")
    @PostMapping
    public ResponseEntity<Alternativa> post(@Valid @RequestBody Alternativa alternativa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alternativaService.post(alternativa));
    }

    @Operation(summary = "atualiza uma alternativa")
    @PutMapping
    public ResponseEntity<Alternativa> put(@Valid @RequestBody Alternativa alternativa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alternativaService.put(alternativa));
    }

    @Operation(summary = "deleta uma alternativa pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
    	alternativaService.delete(id); 
    	return ResponseEntity.noContent().build();
    }
    
    
}
