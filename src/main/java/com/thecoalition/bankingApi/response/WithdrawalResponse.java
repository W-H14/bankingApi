package com.thecoalition.bankingApi.response;


import com.thecoalition.bankingApi.handler.exceptions.WithdrawalNotFoundException;

import com.thecoalition.bankingApi.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.thecoalition.bankingApi.dto.Body;
import com.thecoalition.bankingApi.model.Withdrawal;
import com.thecoalition.bankingApi.service.WithdrawalService;
import org.springframework.web.bind.annotation.*;

@Component
public class WithdrawalResponse {

    @Autowired
    private WithdrawalService withdrawalService;
    @Autowired
    private AccountService accountService;


    public ResponseEntity<?> getAllWithdrawals(Long accountId) {
        Body body = new Body();
        body.setData(withdrawalService.getAllWithdrawalsForAccount(accountId));
        body.setCode(HttpStatus.OK.value());
        body.setMessage("Success");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    public ResponseEntity<?> createWithdrawal(Long accountId, Withdrawal withdrawal) {
        withdrawalService.createWithdrawal(accountId, withdrawal);
        accountService.verifyCostumer(accountId);
        Body body = new Body();
        body.setData(withdrawal);
        body.setCode(HttpStatus.CREATED.value());
        body.setMessage("Created withdrawal and deducted it from the account");
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }


    @GetMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> getWithdrawalById(@PathVariable Long withdrawalId) throws WithdrawalNotFoundException {
        Withdrawal withdrawal = withdrawalService.getWithdrawalById(withdrawalId);

        Body body = new Body();
        body.setData(withdrawalService.getWithdrawalById(withdrawalId));
        body.setCode(HttpStatus.OK.value());
        body.setMessage("Success");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

   

    @PutMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> updateWithdrawal(@RequestBody Withdrawal updatedWithdrawal, @PathVariable Long withdrawalId) throws WithdrawalNotFoundException {
        Withdrawal updatedWithdrawalResult = withdrawalService.updateWithdrawal(withdrawalId, updatedWithdrawal);
        Body body = new Body();
        if (updatedWithdrawalResult != null) {
            body.setData(updatedWithdrawalResult);
            body.setCode(HttpStatus.OK.value());
            body.setMessage("Withdrawal updated successfully");
        } else {
            body.setCode(HttpStatus.NOT_FOUND.value());
            body.setMessage("Withdrawal not found");
        }
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @DeleteMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> deleteWithdrawal(@PathVariable Long withdrawalId) throws WithdrawalNotFoundException {
        Body body = new Body();
        body.setData(withdrawalId);
        body.setCode(HttpStatus.OK.value());
        body.setMessage("Accepted withdrawal modification");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}


