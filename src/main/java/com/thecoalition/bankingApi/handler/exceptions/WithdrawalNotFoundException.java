package com.thecoalition.bankingApi.handler.exceptions;

public class WithdrawalNotFoundException extends Throwable {
    public WithdrawalNotFoundException(String message) {
        super(message);
    }
}
