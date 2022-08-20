package com.etec.tcc.sprint_quiz.exception;

public class AlternativaNotFoundException extends RuntimeException{

    public AlternativaNotFoundException(String message) {
        super("Alternativa não encontrada! | " + message);
    }

    public AlternativaNotFoundException() {
        super("Alternativa não encontrada!");
    }
}
