package com.springboot.redtest.exception;

public class UserException extends RuntimeException {

    private static final long serialVersionUID = 5386492085343078839L;

    public UserException(String message) {
        super(message);
    }

}