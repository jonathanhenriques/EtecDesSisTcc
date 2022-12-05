package com.etec.tcc.sprint_quiz.exception;

public class UsuarioJaCadastradoException extends RuntimeException {

	public UsuarioJaCadastradoException() {
		super("Usuário já cadastrado!");
	}

	public UsuarioJaCadastradoException(String message) {
		super("Usuário já cadastrado! | " + message);
	}

	
}
