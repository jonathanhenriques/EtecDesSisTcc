package com.etec.tcc.sprint_quiz.exception;


//erro genérico
public class RegraNegocioException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegraNegocioException(Object mensagem) {
        super(mensagem.toString());
    }
}
