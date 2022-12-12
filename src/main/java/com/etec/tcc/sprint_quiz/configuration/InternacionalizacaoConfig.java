package com.etec.tcc.sprint_quiz.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

/**
 * 
 * @author hsjon
 *
 *Classe criada para carregar as mensagens do arquivo message.properties
 *para serem acessadas através de variáveis desse arquivo
 *nas anotações de validação (ex: NotNull) nos campos das classes
 */
@Configuration
public class InternacionalizacaoConfig {

	
	/**
	 * Método responsável por carregar o arquivo message.properties
	 * que contém as frases e configura charset
	 * 
	 * @return messageSource
	 */
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
//        messageSource.setDefaultEncoding("ISO-8859-1");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setDefaultLocale(Locale.getDefault());
        return messageSource;
    }

    
    /**
	 * Responsável por fazer a interpolação das variáveis nas classes
	 * pelas frases a serem mostradas
	 * 
	 * @return LocalValidatorFactoryBean
	 */
    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(){
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}