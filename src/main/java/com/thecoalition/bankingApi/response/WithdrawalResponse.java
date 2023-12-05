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
        Withdrawal withdrawal1 = withdrawalService.createWithdrawal(accountId, withdrawal);
        Body body = new Body();
        body.setData(withdrawal1);
        body.setCode(HttpStatus.CREATED.value());
        body.setMessage("Created withdrawal and deducted it from the account");
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }



    public ResponseEntity<?> getWithdrawalById(Long withdrawalId) throws WithdrawalNotFoundException {
        Withdrawal withdrawal = withdrawalService.getWithdrawalById(withdrawalId);

        Body body = new Body();
        body.setData(withdrawalService.getWithdrawalById(withdrawalId));
        body.setCode(HttpStatus.OK.value());
        body.setMessage("Success");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

   

    public ResponseEntity<?> updateWithdrawal(Withdrawal updatedWithdrawal, Long withdrawalId) throws WithdrawalNotFoundException {
        Withdrawal updatedWithdrawalResult = withdrawalService.updateWithdrawal(withdrawalId, updatedWithdrawal);
        Body body = new Body();
            body.setData(updatedWithdrawalResult);
            body.setCode(HttpStatus.OK.value());
            body.setMessage("Withdrawal updated successfully");

        return new ResponseEntity<>(body, HttpStatus.OK);
    }


    public ResponseEntity<?> deleteWithdrawal(Long withdrawalId) throws WithdrawalNotFoundException {
        withdrawalService.deleteWithdrawal(withdrawalId);
        Body body = new Body();
        body.setCode(HttpStatus.NO_CONTENT.value());
        body.setMessage("Withdrawal deleted successfully");
        return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
    }
}


