package com.thecoalition.bankingApi.controller;

import com.thecoalition.bankingApi.model.Withdrawal;
import com.thecoalition.bankingApi.response.WithdrawalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WithdrawalController {

    private final WithdrawalResponse withdrawalResponse;

    @Autowired
    public WithdrawalController(WithdrawalResponse withdrawalResponse) {
        this.withdrawalResponse = withdrawalResponse;
    }

    @GetMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> getAllWithdrawals(@PathVariable Long accountId) {
        return withdrawalResponse.getAllWithdrawals(accountId);
    }

    @GetMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> getWithdrawalById(@PathVariable Long withdrawalId) {
        return withdrawalResponse.getWithdrawalById(withdrawalId);
    }

    @PostMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> createWithdrawal(@PathVariable Long accountId, @RequestBody Withdrawal withdrawal) {
        return withdrawalResponse.createWithdrawal(accountId, withdrawal);
    }

    @PutMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> updateWithdrawal(@RequestBody Withdrawal updatedWithdrawal, @PathVariable Long withdrawalId) {
        return withdrawalResponse.updateWithdrawal(withdrawalId, updatedWithdrawal);
    }

    @DeleteMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> deleteWithdrawal(@PathVariable Long withdrawalId) {
        return withdrawalResponse.deleteWithdrawal(withdrawalId);
    }
}
