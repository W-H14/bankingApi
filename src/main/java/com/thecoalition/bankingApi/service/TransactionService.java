package com.thecoalition.bankingApi.service;

import com.thecoalition.bankingApi.model.Account;
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
        account.
    }


}
