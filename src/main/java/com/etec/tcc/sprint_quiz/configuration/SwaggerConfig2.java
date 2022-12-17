//package com.etec.tcc.sprint_quiz.configuration;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Classe para configurar e habilitar o swagger
// * Documentação da api
// * @author hsjon
// *@since 17/12/2022
// */
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig2 {
//	
//	/**
//	 * Método principal que constrói o Swagger na Api
//	 * @return
//	 */
//	@Bean
//    public Docket docket(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .useDefaultResponseMessages(false)
//                .select()
//                .apis(RequestHandlerSelectors
//                        .basePackage("io.github.dougllasfps.rest.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .securityContexts(Arrays.asList(securityContext()))
//                .securitySchemes(Arrays.asList(apiKey()))
//                .apiInfo(apiInfo());
//    }
//	
//	
//	/**
//	 * Para retornar informações da Api
//	 * @return nome, descrição
//	 */
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder().title("SprintQuizzApp").description("Api da plataforma para Estudos e preparação para provas").version("1.0").contact(contato()).build();
//	}
//	
//	/**
//	 * Para retornar as informações de contato
//	 * @return email, github, nome
//	 */
//	private Contact contato() {
//		return new Contact("Jonathan Henrique","http://github.com/jonathanhenriques","silva.henriquejonas@gmail.com");
//	}
//	
//	/**
//	 * Header Authorization
//	 * Indica que o header conterá o Token JWT
//	 * @return
//	 */
//	public ApiKey apiKey() {
//		return new ApiKey("JWT", "Authorization", "header");
//	}
//	
//	 private SecurityContext securityContext(){
//	        return SecurityContext.builder()
//	                .securityReferences(defaultAuth())
//	                .forPaths(PathSelectors.any())
//	                .build();
//	    }
//
//	    private List<SecurityReference> defaultAuth(){
//	        AuthorizationScope authorizationScope = new AuthorizationScope(
//	                "global", "accessEverything");
//	        AuthorizationScope[] scopes = new AuthorizationScope[1];
//	        scopes[0] = authorizationScope;
//	        SecurityReference reference = new SecurityReference("JWT", scopes);
//	        List<SecurityReference> auths = new ArrayList<>();
//	        auths.add(reference);
//	        return auths;
//	    }
//
//}
