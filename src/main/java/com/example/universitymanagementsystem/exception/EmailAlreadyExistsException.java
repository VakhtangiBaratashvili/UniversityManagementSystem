package com.example.universitymanagementsystem.exception;

public class EmailAlreadyExistsException extends  RuntimeException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
