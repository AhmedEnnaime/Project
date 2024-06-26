package com.progress.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CodeAlreadyExistsException extends RuntimeException{

    public CodeAlreadyExistsException(String message) {
        super(message);
    }
}
