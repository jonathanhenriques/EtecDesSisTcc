package com.etec.tcc.sprint_quiz.exception;

public class ProvaNotFoundException extends RuntimeException {


    public ProvaNotFoundException(String message) {
        super("Prova não encontrada! | " + message);
    }

    public ProvaNotFoundException() {
        super("Prova não encontrada!");
    }
}
