package com.etec.tcc.sprint_quiz.exception;

public class CargoNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CargoNotFoundException() {
		super("Cargo não cadastrado!");
	}

	public CargoNotFoundException(String message) {
		super("Cargo não cadastrado! |" + message);
	}
	
	

}
