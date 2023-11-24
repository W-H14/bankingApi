package com.thecoalition.bankingApi.response;

import com.thecoalition.bankingApi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.thecoalition.bankingApi.dto.Body;
import com.thecoalition.bankingApi.model.Withdrawal;
import com.thecoalition.bankingApi.service.WithdrawalService;

@Component
public class WithdrawalResponse {

    @Autowired
    private WithdrawalService withdrawalService;
    @Autowired
    private AccountService accountService;

    public ResponseEntity<?> createWithdrawal(Long accountId, Withdrawal withdrawal) {
        withdrawalService.createWithdrawal(accountId, withdrawal);
        accountService.verifyCostumer(accountId);
        Body body = new Body();
        body.setData(withdrawal);
        body.setCode(HttpStatus.CREATED.value());
        body.setMessage("Created withdrawal and deducted it from the account");
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getAllWithdrawals(Long accountId) {
        Body body = new Body();
        body.setData(withdrawalService.getAllWithdrawalsForAccount(accountId));
        body.setCode(HttpStatus.OK.value());
        body.setMessage("Success");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    public ResponseEntity<?> updateWithdrawal(Long withdrawalId, Withdrawal withdrawal) {
        withdrawalService.updateWithdrawal(withdrawalId, withdrawal);
        Body body = new Body();
        body.setData(withdrawal);
        body.setCode(HttpStatus.OK.value());
        body.setMessage("Accepted withdrawal modification");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteWithdrawal(Long withdrawalId) {
        withdrawalService.deleteWithdrawal(withdrawalId);
        Body body = new Body();
        body.setCode(HttpStatus.NO_CONTENT.value());
        body.setMessage("Withdrawal deleted successfully");
        return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> getWithdrawalById(Long withdrawalId) {
        Body body = new Body();
        body.setData(withdrawalService.getWithdrawalById(withdrawalId));
        body.setCode(HttpStatus.OK.value());
        body.setMessage("Success");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}


