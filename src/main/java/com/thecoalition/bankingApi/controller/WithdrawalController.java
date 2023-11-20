package com.thecoalition.bankingApi.controller;

import com.thecoalition.bankingApi.model.Withdrawal;
import com.thecoalition.bankingApi.repository.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts/{accountId}/withdrawals")
public class WithdrawalController {

    private final WithdrawalRepository withdrawalRepository;

    @Autowired
    public WithdrawalController(WithdrawalRepository withdrawalRepository) {
        this.withdrawalRepository = withdrawalRepository;
    }

    @GetMapping
    public List<Withdrawal> getAllWithdrawalsForAccount(@PathVariable Long accountId) {
        return null;
    }

    @GetMapping("/{withdrawalId}")
    public Withdrawal getWithdrawalById(@PathVariable Long withdrawalId) {
        Optional<Withdrawal> optionalWithdrawal = withdrawalRepository.findById(withdrawalId);
        return optionalWithdrawal.orElse(null);
    }

    @PostMapping
    public Withdrawal createWithdrawal(@PathVariable Long accountId, @RequestBody Withdrawal withdrawal) {
        return null;
    }

    @PutMapping("/{withdrawalId}")
    public Withdrawal updateWithdrawal(@PathVariable Long withdrawalId, @RequestBody Withdrawal updatedWithdrawal) {
        if (withdrawalRepository.existsById(withdrawalId)) {
            updatedWithdrawal.setId(withdrawalId);
            return withdrawalRepository.save(updatedWithdrawal);
        }
        return null;
    }

    @DeleteMapping("/{withdrawalId}")
    public void deleteWithdrawal(@PathVariable Long withdrawalId) {
        withdrawalRepository.deleteById(withdrawalId);
    }
}
