package com.thecoalition.bankingApi.handler.exceptions;

public class LoanNotValidException extends RuntimeException {
    public LoanNotValidException(String message) {
        super(message);
    }
}
