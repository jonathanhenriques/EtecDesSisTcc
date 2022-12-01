package com.etec.tcc.sprint_quiz.model;

public enum DificuldadeQuestao {

	FACIL(1), INTERMEDIARIO(2), DIFICIL(3);
	
	private int valor;
	
	DificuldadeQuestao(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}
	
	

}
