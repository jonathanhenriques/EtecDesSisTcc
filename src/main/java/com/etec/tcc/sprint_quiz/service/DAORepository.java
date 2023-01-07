package com.etec.tcc.sprint_quiz.service;

import java.util.List;

public interface DAORepository<T, ID>{

	T getById(ID id);
	List<T> getAll();
	T post(T obj);
	T put(T obj);
	void delete(Long id);

}
