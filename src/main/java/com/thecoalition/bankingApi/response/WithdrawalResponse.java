package com.thecoalition.bankingApi.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.thecoalition.bankingApi.dto.Body;
import com.thecoalition.bankingApi.model.Withdrawal;
import com.thecoalition.bankingApi.service.WithdrawalService;

@Component
public class WithdrawalResponse {

    @Autowired
    private WithdrawalService withdrawalService;

    @GetMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> getAllWithdrawals(@PathVariable Long accountId) {
        withdrawalService.getAllWithdrawalsForAccount(accountId);
        Body body = new Body();
        body.setData(accountId);
        body.setCode(HttpStatus.OK.value());
        body.setMessage("Success");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> getWithdrawalById(@PathVariable Long withdrawalId) {
        Withdrawal withdrawal = withdrawalService.getWithdrawalById(withdrawalId);
        Body body = new Body();
        if (withdrawal != null) {
            body.setData(withdrawal);
            body.setCode(HttpStatus.OK.value());
            body.setMessage("Success");
        } else {
            body.setCode(HttpStatus.NOT_FOUND.value());
            body.setMessage("Withdrawal not found");
        }
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> createWithdrawal(@PathVariable Long accountId, @RequestBody Withdrawal withdrawal) {
        Withdrawal createdWithdrawal = withdrawalService.createWithdrawal(accountId, withdrawal);
        Body body = new Body();
        body.setData(createdWithdrawal);
        body.setCode(HttpStatus.CREATED.value());
        body.setMessage("Withdrawal created successfully");
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @PutMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> updateWithdrawal(@RequestBody Withdrawal updatedWithdrawal, @PathVariable Long withdrawalId) {
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
    public ResponseEntity<?> deleteWithdrawal(@PathVariable Long withdrawalId) {
        withdrawalService.deleteWithdrawal(withdrawalId);
        Body body = new Body();
        body.setCode(HttpStatus.NO_CONTENT.value());
        body.setMessage("Withdrawal deleted successfully");
        return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
    }
}

