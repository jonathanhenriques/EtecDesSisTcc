package com.etec.tcc.sprint_quiz.exception;


public class CategoriaProvaNotFoundException extends RuntimeException {


    public CategoriaProvaNotFoundException(String message) { 
        super("Categoria de Prova não encontrada! | " + message);
    } 

    public CategoriaProvaNotFoundException() { 
        super("Categoria de Prova não encontrada!");
    }
}
