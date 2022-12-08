package com.etec.tcc.sprint_quiz.exception;

public class UsuarioJaCadastradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioJaCadastradoException() {
		super("Usuário já cadastrado!");
	}

	public UsuarioJaCadastradoException(String message) {
		super("Usuário já cadastrado! | " + message);
	}

	
}
