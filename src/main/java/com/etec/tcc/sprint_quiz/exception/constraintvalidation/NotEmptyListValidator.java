package com.etec.tcc.sprint_quiz.exception.constraintvalidation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyListValidator implements ConstraintValidator<NoEmptyList, List>{

	
	/**
	 * Valida se a lista não é null
	 * e nem vazia
	 */
	@Override
	public boolean isValid(List value, ConstraintValidatorContext context) {
		return value != null && !value.isEmpty();
	}

}
