package com.kumar.springboot.Buzz.backend.Exceptions;

import org.springframework.http.HttpStatus;

public class PostException extends Exception {

    public PostException(String message) {
        super(message);
    }

}
