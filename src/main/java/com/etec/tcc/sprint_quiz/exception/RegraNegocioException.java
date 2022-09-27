package com.etec.tcc.sprint_quiz.exception;


//erro genérico
public class RegraNegocioException extends RuntimeException {

    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }
}
