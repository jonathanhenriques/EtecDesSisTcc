package com.etec.tcc.sprint_quiz.controller;

import com.etec.tcc.sprint_quiz.ApiErrors;
import com.etec.tcc.sprint_quiz.exception.CategoriaProvaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.CategoriaQuestaoNaoEncontradaException;
import com.etec.tcc.sprint_quiz.exception.ProvaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(CategoriaProvaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleCategoriaProvaNotFoundException(CategoriaProvaNotFoundException ex) {
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(CategoriaQuestaoNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleCategoriaQuestaoNotFound(CategoriaQuestaoNaoEncontradaException ex){
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(ProvaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleProvaNotFoundException(ProvaNotFoundException ex) {
        return new ApiErrors(ex.getMessage());
    }

}
