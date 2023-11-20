package com.thecoalition.bankingApi.exceptions;

import com.thecoalition.bankingApi.error.ErrorDetail;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerNotFoundException extends RuntimeException {
    @Autowired
    ErrorDetail errorDetail;


}
