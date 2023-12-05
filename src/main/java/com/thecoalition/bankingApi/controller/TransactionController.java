package com.thecoalition.bankingApi.controller;

import com.thecoalition.bankingApi.model.Deposit;
import com.thecoalition.bankingApi.model.Transaction;
import com.thecoalition.bankingApi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

     @PostMapping("/transfer/{accountId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void transferFunds(@RequestParam Long payerId, @RequestParam Long payeeId, @RequestBody Transaction transaction){
        transactionService.p2p(payerId, payeeId, transaction.getBalance());
    }
}
