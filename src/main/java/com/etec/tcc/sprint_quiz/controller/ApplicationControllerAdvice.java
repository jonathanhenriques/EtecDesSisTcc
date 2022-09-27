package com.etec.tcc.sprint_quiz.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.assertj.core.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.etec.tcc.sprint_quiz.ApiErrors;
import com.etec.tcc.sprint_quiz.exception.AlternativaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.CategoriaProvaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.CategoriaQuestaoNaoEncontradaException;
import com.etec.tcc.sprint_quiz.exception.ProvaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.QuestaoNotFoundException;
import com.etec.tcc.sprint_quiz.exception.UsuarioNotFoundException;

//manipulador de erros
@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(CategoriaProvaNotFoundException.class)
//	 @ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ApiErrors> handleCategoriaProvaNotFoundException(CategoriaProvaNotFoundException ex,
			HttpServletRequest request) {
		ApiErrors error = new ApiErrors(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

//	
//    @ExceptionHandler(CategoriaProvaNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ApiErrors handleCategoriaProvaNotFoundException(CategoriaProvaNotFoundException ex) {
//        String mensagemErro = ex.getMessage();
//        return new ApiErrors(mensagemErro);
//    }

	@ExceptionHandler(CategoriaQuestaoNaoEncontradaException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ApiErrors> handleCategoriaQuestaoNotFound(CategoriaQuestaoNaoEncontradaException ex,
			HttpServletRequest request) {
		ApiErrors error = new ApiErrors(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(ProvaNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ApiErrors> handleProvaNotFoundException(ProvaNotFoundException ex,
			HttpServletRequest request) {
		ApiErrors error = new ApiErrors(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(QuestaoNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ApiErrors> handleQuestaoNotFoundException(QuestaoNotFoundException ex,
			HttpServletRequest request) {
		ApiErrors error = new ApiErrors(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(AlternativaNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ApiErrors> handleAlternativaNotFoundException(AlternativaNotFoundException ex,
			HttpServletRequest request) {
		ApiErrors error = new ApiErrors(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(UsuarioNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ApiErrors> handleUsuarioNotFoundException(UsuarioNotFoundException ex,
			HttpServletRequest request) {
		ApiErrors error = new ApiErrors(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

}
