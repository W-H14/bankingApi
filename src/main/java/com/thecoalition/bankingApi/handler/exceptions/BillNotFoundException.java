package com.thecoalition.bankingApi.handler.exceptions;

public class BillNotFoundException extends RuntimeException {
    public BillNotFoundException(String message) {
        super(message);
    }
}
