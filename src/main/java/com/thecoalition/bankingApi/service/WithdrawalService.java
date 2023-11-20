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

    public Iterable<Withdrawal> getAllWithdrawalsForAccount(Long accountId) {
        Iterable<Withdrawal>withdrawals = withdrawalRepository.findAll();
        return withdrawalRepository.findAllById(accountId);
    }

    public Withdrawal getWithdrawalById(Long withdrawalId) {
        Optional<Withdrawal> optionalWithdrawal = withdrawalRepository.findById(withdrawalId);
        return optionalWithdrawal.orElse(null);
    }

    public Withdrawal createWithdrawal(Long accountId, Withdrawal withdrawal) {
        return withdrawalRepository.save(withdrawal);
    }

    public Withdrawal updateWithdrawal(Long withdrawalId, Withdrawal updatedWithdrawal) {
        if (withdrawalRepository.existsById(withdrawalId)) {
            updatedWithdrawal.setId(withdrawalId);
            return withdrawalRepository.save(updatedWithdrawal);
        }
        return null;
    }

    public void deleteWithdrawal(Long withdrawalId) {
        withdrawalRepository.deleteById(withdrawalId);
    }
}
