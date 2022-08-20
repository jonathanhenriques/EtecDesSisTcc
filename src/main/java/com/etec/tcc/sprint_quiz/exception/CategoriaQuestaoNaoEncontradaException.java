package com.etec.tcc.sprint_quiz.exception;

public class CategoriaQuestaoNaoEncontradaException extends RuntimeException {


    public CategoriaQuestaoNaoEncontradaException(String message) {
        super("Categoria da Questão não encontrada | " + message);
    }

    public CategoriaQuestaoNaoEncontradaException() {
        super("Categoria da Questão não encontrada!");
    }
}
