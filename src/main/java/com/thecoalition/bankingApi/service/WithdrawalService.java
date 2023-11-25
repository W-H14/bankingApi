package com.thecoalition.bankingApi.service;

import com.thecoalition.bankingApi.model.Withdrawal;
import com.thecoalition.bankingApi.repository.WithdrawalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WithdrawalService {

    private final WithdrawalRepository withdrawalRepository;

    private final Logger logger = LoggerFactory.getLogger(WithdrawalService.class);

    @Autowired
    public WithdrawalService(WithdrawalRepository withdrawalRepository) {
        this.withdrawalRepository = withdrawalRepository;
    }

    public Iterable<Withdrawal> getAllWithdrawalsForAccount(Long accountId) {
        Iterable<Withdrawal>withdrawals = withdrawalRepository.findAll();
        logger.info("Successfully gotten all withdraws for account");
        return withdrawalRepository.findAllById(accountId);
    }

    public Withdrawal getWithdrawalById(Long withdrawalId) {
        Optional<Withdrawal> optionalWithdrawal = withdrawalRepository.findById(withdrawalId);
        logger.info("Successfully retrieved withdrawal by id");
        return optionalWithdrawal.orElse(null);
    }

    public Withdrawal createWithdrawal(Long accountId, Withdrawal withdrawal) {

        logger.info("successfully created Withdraw");
        return withdrawalRepository.save(withdrawal);
    }

    public Withdrawal updateWithdrawal(Long withdrawalId, Withdrawal updatedWithdrawal) {
        if (withdrawalRepository.existsById(withdrawalId)) {
            updatedWithdrawal.setId(withdrawalId);

            logger.info("Successfully Updated Withdraw");
            return withdrawalRepository.save(updatedWithdrawal);
        }
       logger.error("Couldn't update Withdraw");
        return null;
    }

    public void deleteWithdrawal(Long withdrawalId) {

        logger.info("Successfully deleted Withdraw");
        withdrawalRepository.deleteById(withdrawalId);
    }
}
