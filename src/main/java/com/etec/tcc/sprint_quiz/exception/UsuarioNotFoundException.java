package com.etec.tcc.sprint_quiz.exception;

public class UsuarioNotFoundException extends RuntimeException{

    public UsuarioNotFoundException(String message) {
        super("Usuário não encontrado | " + message);
    }

    public UsuarioNotFoundException() {
        super("Usuário não encontrado");
    }
}
