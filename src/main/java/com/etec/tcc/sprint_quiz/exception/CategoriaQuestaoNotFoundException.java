package com.etec.tcc.sprint_quiz.exception;

public class CategoriaQuestaoNotFoundException extends RuntimeException {


    public CategoriaQuestaoNotFoundException(String message) {
        super("Categoria da Questão não encontrada | " + message);
    }

    public CategoriaQuestaoNotFoundException() {
        super("Categoria da Questão não encontrada!");
    }
}
