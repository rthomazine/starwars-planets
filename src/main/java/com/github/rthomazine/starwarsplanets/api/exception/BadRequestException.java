package com.github.rthomazine.starwarsplanets.api.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception {

    private static final long serialVersionUID = -3477827736234624093L;

    public BadRequestException(String message){
        super(message);
    }

    public BadRequestException(Throwable e){
        super(e);
    }
}