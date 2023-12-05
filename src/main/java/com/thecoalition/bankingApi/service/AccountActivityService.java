package com.thecoalition.bankingApi.service;

import com.thecoalition.bankingApi.handler.exceptions.ResourceNotFoundException;
import com.thecoalition.bankingApi.model.Account;
import com.thecoalition.bankingApi.model.AccountActivity;
import com.thecoalition.bankingApi.model.Deposit;
import com.thecoalition.bankingApi.model.Withdrawal;
import com.thecoalition.bankingApi.repository.AccountActivityRepository;
import com.thecoalition.bankingApi.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AccountActivityService {

    @Autowired
    private AccountActivityRepository accountActivityRepository;
    @Autowired
    private DepositService depositService;
    @Autowired
    private WithdrawalService withdrawalService;
    @Autowired
    private AccountRepository accountRepository;


    private final Logger logger = LoggerFactory.getLogger(AccountActivityService.class);


    public AccountActivity createAccountActivity(Long accountId, Double amount, boolean isDeposit) {
        try {
            Optional<Account> accountOptional = accountRepository.findById(accountId);
            if (accountOptional.isPresent()) {
                Account account = accountOptional.get();

                AccountActivity activity = new AccountActivity();
                activity.setActivityDate(new Date());
                activity.setAmount(amount);
                //activity.setAccount(account);

                if (isDeposit) {
                    Deposit newDeposit = createDeposit(account, amount);
                    activity.setDeposit(newDeposit);

                } else {
                    Withdrawal newWithdrawal =createWithdrawal(account, amount);
                    activity.setWithdrawal(newWithdrawal);
                }
                logger.info("Successfully created Account activity");
                return accountActivityRepository.save(activity);
            } else {
                logger.error("Error creating Account Activity account not found");
                throw new ResourceNotFoundException("Error creating AccountActivity: Account not found");
            }

        } catch (Exception e) {
            logger.error("Error creating AccountActivity", e);
            throw new RuntimeException("Error creating AccountActivity");
        }
    }

//    public Iterable<AccountActivity> getAllActivities() {
//        return accountActivityRepository.findAll();
//    }
        private Deposit createDeposit(Account account, Double amount) {
            Deposit deposit = new Deposit();
            deposit.setAccount(account);
            deposit.setAmount(amount);


            return depositService.createDeposit(account.getAccountId(), deposit);
        }

        private Withdrawal createWithdrawal(Account account, Double amount) {
            Withdrawal withdrawal = new Withdrawal();
            withdrawal.setAccount(account);
            withdrawal.setAmount(amount);


            return withdrawalService.createWithdrawal(account.getAccountId(), withdrawal);
        }
}
