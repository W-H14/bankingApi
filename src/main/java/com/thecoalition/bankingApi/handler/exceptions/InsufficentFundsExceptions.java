package com.thecoalition.bankingApi.handler.exceptions;

public class InsufficentFundsExceptions extends RuntimeException{
    public InsufficentFundsExceptions(String message) {
        super(message);
    }
}
