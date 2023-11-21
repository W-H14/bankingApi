package com.thecoalition.bankingApi.exceptions;

public class WithdrawalNotFoundException extends Throwable {
    public WithdrawalNotFoundException(String message) {
        super(message);
    }
}
