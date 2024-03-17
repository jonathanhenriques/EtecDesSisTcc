package com.etec.tcc.sprint_quiz.api.exception;

public class SenhaInvalidaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SenhaInvalidaException() {
		super("Senha inválida!");
	}

	
}
