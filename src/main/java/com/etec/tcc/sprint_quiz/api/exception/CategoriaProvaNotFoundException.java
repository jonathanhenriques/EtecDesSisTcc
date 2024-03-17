package com.etec.tcc.sprint_quiz.api.exception;


public class CategoriaProvaNotFoundException extends RuntimeException {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoriaProvaNotFoundException(Object message) { 
        super("Categoria de Prova não encontrada! | " + message);
    } 

    public CategoriaProvaNotFoundException() { 
        super("Categoria de Prova não encontrada!");
    }
}
