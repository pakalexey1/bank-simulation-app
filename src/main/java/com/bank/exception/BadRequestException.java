package com.bank.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);//will throw the same message as the RuntimeException does
    }
}
