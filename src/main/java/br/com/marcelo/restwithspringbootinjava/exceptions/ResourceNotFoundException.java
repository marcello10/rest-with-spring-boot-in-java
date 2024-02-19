package br.com.marcelo.restwithspringbootinjava.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperatioExcepition extends RuntimeException{

    public UnsupportedMathOperatioExcepition(String ex) {
        super(ex);
    }
}
