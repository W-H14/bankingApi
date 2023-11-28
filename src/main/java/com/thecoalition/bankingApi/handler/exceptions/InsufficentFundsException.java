package com.thecoalition.bankingApi.handler.exceptions;

public class InsufficentFundsException extends ResourceNotFoundException{
    public InsufficentFundsException(String message) {
        super(message);
    }

}
