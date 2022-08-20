package com.etec.tcc.sprint_quiz.exception;

public class QuestaoNotFoundException extends RuntimeException{

    public QuestaoNotFoundException(String message) {
        super("Questão não encontrada | " + message);
    }

    public QuestaoNotFoundException() {
        super("Questão não encontrada!");
    }
}
