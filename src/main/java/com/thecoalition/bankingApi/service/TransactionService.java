package com.thecoalition.bankingApi.service;

import com.thecoalition.bankingApi.model.Account;
import com.thecoalition.bankingApi.model.Deposit;
import com.thecoalition.bankingApi.model.Withdrawal;
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

    @Transactional
     public void deposit(Long accountId, double amount){
        Account account = accountService.getAccount(accountId);

        account.setBalance(account.getBalance()+ amount);
        accountService.updateAccount(account, accountId);

        Deposit deposit = new Deposit();
        deposit.setAmount(amount);
        depositService.createDeposit(accountId, deposit);
    }
    @Transactional
    public void withdrawal(Long accountId, double amount){
        Account account = accountService.getAccount(accountId);

        account.setBalance(account.getBalance()- amount);
        accountService.updateAccount(account, accountId);

        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setAmount(amount);
        withdrawalService.createWithdrawal(accountId, withdrawal);
    }


}
