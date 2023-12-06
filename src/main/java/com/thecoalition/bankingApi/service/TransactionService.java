package com.thecoalition.bankingApi.service;

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
    private DepositService depositService;
    @Autowired
    private WithdrawalService withdrawalService;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Transactional
    public void deposit(Long accountId, double amount){
        Account account = accountService.getAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        logger.info("Depositing {} to account {}", amount, accountId);

        accountService.updateAccount(account, accountId);
        account.setBalance(account.getBalance() + amount);

        Deposit deposit = new Deposit();
        deposit.setAmount(amount);
        depositService.createDeposit(accountId, deposit);
    }

    @Transactional
    public void withdrawal(Long accountId, double amount){
        Account account = accountService.getAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        logger.info("Withdrawing {} from account {}", amount, accountId);

        account.setBalance(account.getBalance()- amount);
        accountService.updateAccount(account, accountId);

        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setAmount(amount);
        withdrawalService.createWithdrawal(accountId, withdrawal);
    }

    @Transactional
    public void p2p(Long payer_Id, Long payee_Id, double amount) {
        try {
            logger.info("Transfer initiated");

            Optional<Account> senderOptional = accountRepository.findById(payer_Id);
            Optional<Account> receiverOptional = accountRepository.findById(payee_Id);



            if (!senderOptional.isPresent()) {
                logger.error("Payer with id {} not found", payer_Id);
                throw new ResourceNotFoundException("Payer not found");
            }

            if (!receiverOptional.isPresent()) {
                logger.error("Payee with id {} not found", payee_Id);
                throw new ResourceNotFoundException("Payee not found");
            }

            Account payer = senderOptional.get();


            if (payer.getBalance() < amount) {
                logger.error("Insufficient balance in payer's account");
                throw new IllegalArgumentException("Insufficient balance in payer's account");
            }


            withdrawal(payer_Id, amount);
            deposit(payee_Id, amount);

            logger.info("Transfer successful");
        } catch (ResourceNotFoundException | IllegalArgumentException e) {
            logger.error("Error performing transfer: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error performing transfer", e);
            throw new RuntimeException("Unexpected error performing transfer", e);
        }

    }
}

//   */
//    @Transactional
//    public void p2p(Long payer_Id, Long payee_Id, double amount) {
//        try {
//            logger.info("Transfered");
//            Optional<Transaction> receiver = transactionRepository.findById(payee_Id);
//            Optional<Transaction> sender = transactionRepository.findById(payer_Id);
//            Transaction payer = sender.get();
//            payer.setBalance(payer.getBalance() - amount); //then follow rules of making an edit (maybe to a bill or whatever)
//
//
//            Transaction payee = receiver.get();
//            payee.setBalance(payee.getBalance() + amount);
//
//           // updateBalance(payer_Id, payer);
//          //  updateBalance(payee_Id, payee);
//            withdrawal(payer_Id, amount);
//            deposit(payee_Id, amount);
//        } catch (Exception e) {
//            logger.error("Error performing transfer: Error with transaction", e);
//            throw new ("Error creating transfer");
//
//        }
//
//    }


