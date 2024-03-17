package com.etec.tcc.sprint_quiz.api.exception;

public class CategoriaQuestaoNotFoundException extends RuntimeException {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoriaQuestaoNotFoundException(Object message) {
        super("Categoria da Questão não encontrada | " + message);
    }

    public CategoriaQuestaoNotFoundException() {
        super("Categoria da Questão não encontrada!");
    }
}
