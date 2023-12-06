package com.thecoalition.bankingApi.controller;


import com.thecoalition.bankingApi.model.Transaction;
import com.thecoalition.bankingApi.response.TransactionResponse;
import com.thecoalition.bankingApi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionResponse transactionResponse;

    /*
    @PostMapping("/transfer/{accountId}")
     */

    @PostMapping("/transfer/{payerId}/{payeeId}/{amount}")
    public ResponseEntity<?> p2p(@PathVariable Long payerId, @PathVariable Long payeeId, @PathVariable double amount){

        return  transactionResponse.p2p(payerId, payeeId, amount);
    }
}
