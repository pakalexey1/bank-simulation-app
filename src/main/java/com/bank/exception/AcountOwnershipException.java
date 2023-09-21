package com.bank.exception;

public class AcountOwnershipException extends RuntimeException {
    public AcountOwnershipException(String message) {
        super(message);
    }
}
