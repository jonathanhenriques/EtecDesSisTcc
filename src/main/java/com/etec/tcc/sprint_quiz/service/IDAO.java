package com.etec.tcc.sprint_quiz.service;

import java.util.List;

public interface IDAO<T> {

	T getById(Long id);

	List<T> getAll();

	T post(T obj);

	T put(T obj);

	void delete(Long id);

}
