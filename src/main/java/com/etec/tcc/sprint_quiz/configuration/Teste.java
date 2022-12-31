package com.etec.tcc.sprint_quiz.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Anotação criada para organizar
 * classes que rodam apenas
 * no application-local.properties
 *  ambiente local
 *  
 * @author hsjon
 * @since 20/12/2022
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Configuration
@Profile("teste")
public @interface Teste {

}
