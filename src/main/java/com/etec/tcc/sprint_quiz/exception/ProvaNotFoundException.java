package com.etec.tcc.sprint_quiz.exception;

public class ProvaNotFoundException extends RuntimeException {

    public ProvaNotFoundException() {
        super("Prova não encontrada!");
    }
}
