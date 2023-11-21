package com.thecoalition.bankingApi.exceptions;

public class DepositNotFoundException extends RuntimeException {
    public DepositNotFoundException(String message) {
        super(message);
    }
}
