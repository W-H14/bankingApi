package com.thecoalition.bankingApi.service;

import com.thecoalition.bankingApi.model.Withdrawal;
import com.thecoalition.bankingApi.repository.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WithdrawalService {

    private final WithdrawalRepository withdrawalRepository;

    @Autowired
    public WithdrawalService(WithdrawalRepository withdrawalRepository) {
        this.withdrawalRepository = withdrawalRepository;
    }

    public List<Withdrawal> getAllWithdrawalsForAccount(Long accountId) {
        // Implement logic to fetch all withdrawals for a specific account
        // For example: return withdrawalRepository.findAllByAccountId(accountId);
        // Adjust the method based on your actual data model and repository methods.
        return null;
    }

    public Withdrawal getWithdrawalById(Long withdrawalId) {
        Optional<Withdrawal> optionalWithdrawal = withdrawalRepository.findById(withdrawalId);
        return optionalWithdrawal.orElse(null); // Handle if not found
    }

    public Withdrawal createWithdrawal(Long accountId, Withdrawal withdrawal) {
        // Implement logic to create a withdrawal for a specific account
        // For example: withdrawal.setAccountId(accountId);
        // Adjust the method based on your actual data model and repository methods.
        return null;
    }

    public Withdrawal updateWithdrawal(Long withdrawalId, Withdrawal updatedWithdrawal) {
        if (withdrawalRepository.existsById(withdrawalId)) {
            updatedWithdrawal.setId(withdrawalId);
            return withdrawalRepository.save(updatedWithdrawal);
        }
        return null; // Handle if not found
    }

    public void deleteWithdrawal(Long withdrawalId) {
        withdrawalRepository.deleteById(withdrawalId);
    }
}
