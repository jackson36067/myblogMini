package com.jackson.exception;

public class AuthorizedException extends RuntimeException{
    public AuthorizedException() {
    }

    public AuthorizedException(String message) {
        super(message);
    }
}
