package com.progress.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CodeAlreadyExistsException extends RuntimeException{

    public CodeAlreadyExistsException(String message) {
        super(message);
    }
}
