package com.codeseita.librarymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException{

    public ConflictException() {
    }

    public ConflictException(String s) {
        super(s);
    }
}
