package com.thecoalition.bankingApi.handler.exceptions;

import com.thecoalition.bankingApi.error.ErrorDetail;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super(message);
    }


}
