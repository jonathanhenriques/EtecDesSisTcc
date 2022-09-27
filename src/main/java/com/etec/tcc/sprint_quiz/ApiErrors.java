package com.etec.tcc.sprint_quiz;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;


//classe standart que monta o erro
@Hidden
public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }

    public ApiErrors(String mensagemErro){
        this.errors =Arrays.asList(mensagemErro);
    }

}
