package com.etec.tcc.sprint_quiz.exception.constraintvalidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = NotEmptyListValidator.class)
public @interface NoEmptyList {

	String message() default "A lista não pode ser vazia";
}
