package com.thecoalition.bankingApi.exceptions;

import com.thecoalition.bankingApi.error.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.auth.login.AccountNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleException(Exception e) {
        return new ResponseEntity<>(new ErrorDetail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new ErrorDetail(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    // Define additional exception handlers for specific exceptions

    // Example for AccountNotFoundException
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleAccountNotFoundException(AccountNotFoundException e) {
        return new ResponseEntity<>(new ErrorDetail(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    // Example for CustomerNotFoundException
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleCustomerNotFoundException(CustomerNotFoundException e) {
        return new ResponseEntity<>(new ErrorDetail(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    // Example for BillNotFoundException
    @ExceptionHandler(BillNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleBillNotFoundException(BillNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    // Example for DepositNotFoundException
    @ExceptionHandler(DepositNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleDepositNotFoundException(DepositNotFoundException e) {
        return new ResponseEntity<>(new ErrorDetail(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    // Example for WithdrawalNotFoundException
    @ExceptionHandler(WithdrawalNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleWithdrawalNotFoundException(WithdrawalNotFoundException e) {
        return new ResponseEntity<>(new ErrorDetail(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }


}
