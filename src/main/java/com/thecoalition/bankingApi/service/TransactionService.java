package com.thecoalition.bankingApi.service;

import com.thecoalition.bankingApi.handler.exceptions.InsufficentFundsException;
import com.thecoalition.bankingApi.handler.exceptions.ResourceNotFoundException;
import com.thecoalition.bankingApi.model.Account;
import com.thecoalition.bankingApi.model.Deposit;
import com.thecoalition.bankingApi.model.Transaction;
import com.thecoalition.bankingApi.model.Withdrawal;
import com.thecoalition.bankingApi.repository.AccountRepository;
import com.thecoalition.bankingApi.repository.TransactionRepository;
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
    private AccountRepository accountRepository;
    @Autowired
    private DepositService depositService;
    @Autowired
    private WithdrawalService withdrawalService;

    @Autowired
    private TransactionRepository transactionRepository;


    private final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Transactional
    public void deposit(Long accountId, double amount) {
        Account account = accountService.getAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        logger.info("Depositing {} to account {}", amount, accountId);

        accountService.updateAccount(account, accountId);

        Deposit deposit = new Deposit();
        deposit.setAmount(amount);
        depositService.createDeposit(accountId, deposit);
    }

    @Transactional
    public void withdrawal(Long accountId, double amount) {
        Account account = accountService.getAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        logger.info("Withdrawing {} from account {}", amount, accountId);

        account.setBalance(account.getBalance() - amount);
        accountService.updateAccount(account, accountId);

        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setAmount(amount);
        withdrawalService.createWithdrawal(accountId, withdrawal);
    }

    @Transactional
    public Transaction updateBalance(Long Id, Transaction transaction){
        Optional<Transaction> transactionOptional = transactionRepository.findById(Id);
        Transaction existingbalnce = transactionOptional.get();
        existingbalnce.setBalance(transaction.getBalance());


      return existingbalnce;
    }


    @Transactional
    public void p2p(Long payer_Id, Long payee_Id, double amount) {
        try {
            logger.info("Transfered");
            Optional<Transaction> receiver = transactionRepository.findById(payee_Id);
            Optional<Transaction> sender = transactionRepository.findById(payer_Id);
            Transaction payer = sender.get();
            payer.setBalance(payer.getBalance() - amount); //then follow rules of making an edit (maybe to a bill or whatever)


            Transaction payee = receiver.get();
            payee.setBalance(payee.getBalance() + amount);

          updateBalance(payer_Id,payer);
            updateBalance(payee_Id,payee);
        } catch (Exception e) {
            logger.error("Error performing transfer: Error with transaction", e);
            throw new ResourceNotFoundException("Error creating transfer");

        }
    }
}