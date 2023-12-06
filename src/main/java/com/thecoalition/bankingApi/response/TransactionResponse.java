package com.thecoalition.bankingApi.response;

import com.thecoalition.bankingApi.dto.Body;
import com.thecoalition.bankingApi.model.Transaction;
import com.thecoalition.bankingApi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpStatus;
@Component
public class TransactionResponse {
    @Autowired
    TransactionService transactionService;

    /*
      public ResponseEntity<?> createAccount(Long costumerId, Account account) {
        accountService.createAccount(costumerId, account);
        Body body = new Body();
        body.setData(account);
        body.setCode(HttpStatus.CREATED.value());
        body.setMessage("Account created successfully");
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }
     */
    public ResponseEntity<?> p2p(Long payerId, Long payeeId, double amount){
        transactionService.p2p(payerId, payeeId, amount);

        Body body = new Body();

        body.setCode(HttpStatus.OK.value());
        body.setMessage("Funds transfered Successfully from " + payerId + " to " + payeeId + " with amount " + amount);

 return new ResponseEntity<>(body, HttpStatus.OK);
    }


}
