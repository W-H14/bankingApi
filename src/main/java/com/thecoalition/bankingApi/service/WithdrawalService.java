package com.thecoalition.bankingApi.service;

import com.thecoalition.bankingApi.handler.exceptions.ResourceNotFoundException;
import com.thecoalition.bankingApi.handler.exceptions.WithdrawalNotFoundException;
import com.thecoalition.bankingApi.model.Account;
import com.thecoalition.bankingApi.model.Withdrawal;
import com.thecoalition.bankingApi.repository.AccountRepository;
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
    private AccountRepository accountRepository;
    @Autowired
    public WithdrawalService(WithdrawalRepository withdrawalRepository) {
        this.withdrawalRepository = withdrawalRepository;
    }

    public Optional<Withdrawal> getAllWithdrawalsForAccount(Long accountId) {
        Iterable<Withdrawal> withdrawals = withdrawalRepository.findAll();
        if (!withdrawals.iterator().hasNext()) {
            logger.error("Account not found");
            throw new ResourceNotFoundException("Account not found");
        }
        logger.info("Successfully gotten all withdraws for account");
        return withdrawalRepository.findByPayerId(accountId);
    }

    public Withdrawal getWithdrawalById(Long withdrawalId) throws WithdrawalNotFoundException {
        Optional<Withdrawal> optionalWithdrawal = withdrawalRepository.findById(withdrawalId);
        if (optionalWithdrawal.isEmpty()) {
            logger.error("Error fetching withdrawal with id: " + withdrawalId);
            throw new WithdrawalNotFoundException("Error fetching withdrawal with id: " + withdrawalId);
        }
        logger.info("Successfully retrieved withdrawal by id");
        return optionalWithdrawal.orElse(null);
    }

    public Withdrawal createWithdrawal(Long accountId, Withdrawal withdrawal) {
        try {
            var accountOptional = accountRepository.findById(accountId);
            if (accountOptional.isPresent()) {
                Account account = accountOptional.get();
                withdrawal.setAccount(account);
                logger.info("Successfully created Withdrawal");
                return withdrawalRepository.save(withdrawal);
            } else {
                logger.error("Error creating withdrawal: Account not found");
                throw new ResourceNotFoundException("Error creating withdrawal: Account not found");
            }
        } catch (Exception e) {
            logger.error("Error creating withdrawal: Account not found", e);
            throw new ResourceNotFoundException("Error creating withdrawal: Account not found");
        }
    }

    public Withdrawal updateWithdrawal(Long withdrawalId, Withdrawal updatedWithdrawal) throws WithdrawalNotFoundException {
        var withdrawalOptional = withdrawalRepository.findById(withdrawalId);
        if (withdrawalOptional.isPresent()) {
            Withdrawal withdrawal = withdrawalOptional.get();
            Optional<Account> account = accountRepository.findById(updatedWithdrawal.getPayer_id());
            if (account.isPresent()) {
                withdrawal.setAccount(account.get());
            }
            withdrawal.setAmount(updatedWithdrawal.getAmount());
            withdrawal.setDescription(updatedWithdrawal.getDescription());
            withdrawal.setMedium(updatedWithdrawal.getMedium());
            withdrawal.setType(updatedWithdrawal.getType());
            withdrawal.setTransaction_date(updatedWithdrawal.getTransaction_date());
            withdrawal.setStatus(updatedWithdrawal.getStatus());
            logger.info("Successfully Updated Withdraw");
            return withdrawalRepository.save(withdrawal);
        }
        logger.error("Error fetching withdrawal with id: " + withdrawalId);
        throw new WithdrawalNotFoundException("Error fetching withdrawal with id: " + withdrawalId);
    }

    public void deleteWithdrawal(Long withdrawalId) throws WithdrawalNotFoundException {
        if (!withdrawalRepository.existsById(withdrawalId)) {
            logger.error("This id does not exist in withdrawals");
            throw new WithdrawalNotFoundException("This id does not exist in withdrawals");
        }

        logger.info("Successfully deleted Withdraw");
        withdrawalRepository.deleteById(withdrawalId);
    }
}






//    public Withdrawal createWithdrawal(Long accountId, Withdrawal withdrawal) {
//
//        logger.info("successfully created Withdraw");
//        return withdrawalRepository.save(withdrawal);
//    }
//
//    public Withdrawal updateWithdrawal(Long withdrawalId, Withdrawal updatedWithdrawal) {
//        if (withdrawalRepository.existsById(withdrawalId)) {
//            updatedWithdrawal.setId(withdrawalId);
//
//            logger.info("Successfully Updated Withdraw");
//            return withdrawalRepository.save(updatedWithdrawal);
//        }
//        logger.error("Couldn't update Withdraw");
//        return null;
//    }
//
//    public void deleteWithdrawal(Long withdrawalId) {
//
//        logger.info("Successfully deleted Withdraw");
//        withdrawalRepository.deleteById(withdrawalId);
//    }
//}
