package com.example.exception;

public class InvalidMessageDataException extends RuntimeException {
    public InvalidMessageDataException(String message) {
        super(message);
    }
}
