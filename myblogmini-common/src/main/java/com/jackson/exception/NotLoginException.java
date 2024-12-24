package com.jackson.exception;

public class NotLoginException extends AuthorizedException {
    public NotLoginException() {
    }

    public NotLoginException(String message) {
        super(message);
    }
}
