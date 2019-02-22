package com.github.rthomazine.starwarsplanets.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7125997973335370582L;

    public NotFoundException(String message){
        super(message);
    }

    public NotFoundException(Throwable e){
        super(e);
    }

    public NotFoundException(String message, Throwable e){
        super(message, e);
    }

}