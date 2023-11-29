package com.thecoalition.bankingApi.controller;

import com.thecoalition.bankingApi.handler.exceptions.WithdrawalNotFoundException;
import com.thecoalition.bankingApi.model.Withdrawal;
import com.thecoalition.bankingApi.response.WithdrawalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WithdrawalController {
// needs autowired
    private final WithdrawalResponse withdrawalResponse;

    @Autowired
    public WithdrawalController(WithdrawalResponse withdrawalResponse) {
        this.withdrawalResponse = withdrawalResponse;
    }

    /**
     * gets all withdrawls in an account
     * @param accountId
     * @return
     */
    @GetMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> getAllWithdrawals(@PathVariable Long accountId) {
        return withdrawalResponse.getAllWithdrawals(accountId);
    }

    /**
     * gets withdrawal by id
     * @param withdrawalId
     * @return
     * @throws WithdrawalNotFoundException
     */
    @GetMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> getWithdrawalById(@PathVariable Long withdrawalId) throws WithdrawalNotFoundException {
        return withdrawalResponse.getWithdrawalById(withdrawalId);
    }

    /**
     * creates a withdrawal
     * @param accountId
     * @param withdrawal
     * @return
     */
    @PostMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> createWithdrawal(@PathVariable Long accountId, @RequestBody Withdrawal withdrawal) {
        return withdrawalResponse.createWithdrawal(accountId, withdrawal);
    }

    /**
     * updates withdrawal
     * @param updatedWithdrawal
     * @param withdrawalId
     * @return
     * @throws WithdrawalNotFoundException
     */
    @PutMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> updateWithdrawal(@RequestBody Withdrawal updatedWithdrawal, @PathVariable Long withdrawalId) throws WithdrawalNotFoundException {
        return withdrawalResponse.updateWithdrawal(updatedWithdrawal, withdrawalId);

    }

    /**
     * deletes withdrawal
     * @param withdrawalId
     * @return
     * @throws WithdrawalNotFoundException
     */
    @DeleteMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> deleteWithdrawal(@PathVariable Long withdrawalId) throws WithdrawalNotFoundException {
        return withdrawalResponse.deleteWithdrawal(withdrawalId);
    }
}
