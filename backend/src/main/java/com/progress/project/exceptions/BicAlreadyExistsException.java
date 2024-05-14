package com.progress.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BicAlreadyExistsException extends RuntimeException{

    public BicAlreadyExistsException(String message) {
        super(message);
    }
}
