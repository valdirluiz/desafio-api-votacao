package com.valdirluiz.app.votacao.exceptions;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String msg) {
        super(msg);
    }
}
