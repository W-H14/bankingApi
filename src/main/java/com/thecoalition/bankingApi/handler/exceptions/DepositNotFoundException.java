package com.thecoalition.bankingApi.handler.exceptions;

public class DepositNotFoundException extends RuntimeException {
    public DepositNotFoundException(String message) {
        super(message);
    }
}
