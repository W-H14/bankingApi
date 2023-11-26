package com.thecoalition.bankingApi.service;

import com.thecoalition.bankingApi.model.Account;
import com.thecoalition.bankingApi.model.Deposit;
import com.thecoalition.bankingApi.model.Withdrawal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
     private AccountService accountService;
    @Autowired
    private DepositService depositService;
    @Autowired
    private WithdrawalService withdrawalService;

    private final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Transactional
     public void deposit(Long accountId, double amount){
        Account account = accountService.getAccountById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        logger.info("");
        accountService.updateAccount(account, accountId);

        Deposit deposit = new Deposit();
        deposit.setAmount(amount);
        depositService.createDeposit(accountId, deposit);
    }
    @Transactional
    public void withdrawal(Long accountId, double amount){
        Account account = accountService.getAccountById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        account.setBalance(account.getBalance()- amount);
        accountService.updateAccount(account, accountId);

        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setAmount(amount);
        withdrawalService.createWithdrawal(accountId, withdrawal);
    }


}
