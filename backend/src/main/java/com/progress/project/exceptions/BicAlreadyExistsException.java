package com.progress.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BicAlreadyExistsException extends RuntimeException{

    public BicAlreadyExistsException(String message) {
        super(message);
    }
}
