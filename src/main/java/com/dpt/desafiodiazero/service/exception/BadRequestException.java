package com.dpt.desafiodiazero.service.exception;

public class BadRequestException extends BusinessException{

    private static final long serialVersionUID = 1L;

    public BadRequestException(String message) {
        super(message);
    }
}
