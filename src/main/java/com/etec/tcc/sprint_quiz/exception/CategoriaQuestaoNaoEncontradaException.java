package com.etec.tcc.sprint_quiz.exception;

public class CategoriaQuestaoNaoEncontradaException extends RuntimeException {

    public CategoriaQuestaoNaoEncontradaException() {
        super("Categoria da Questão não encontrada!");
    }
}
