package com.thecoalition.bankingApi.service;

import ch.qos.logback.classic.joran.action.LoggerAction;
import com.thecoalition.bankingApi.model.Account;
import com.thecoalition.bankingApi.model.Customer;
import com.thecoalition.bankingApi.repository.AccountRepository;
import com.thecoalition.bankingApi.handler.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AccountService {

    @Autowired
    private AccountRepository AccountRepo;
    @Autowired
    private CustomerService customerService;


    private final org.slf4j.Logger logger = LoggerFactory.getLogger(AccountService.class);

    public void verifyCostumer(Long CostumerId) throws ResourceNotFoundException {
        Optional<Customer> costumer = customerService.getCustomerById(CostumerId);
        if (costumer.isEmpty()) {
            logger.error("Customer Not Verified");
            throw new ResourceNotFoundException("Costumer with id " + CostumerId + " not found");
        }

    }
    public Optional<Account> getAccountById(Long accountId) {
        logger.info("Retrieved account by Id successfully ");

        return AccountRepo.findById(accountId);

    }



    public Iterable<Account> getAllAccounts() {
        Iterable<Account> allAccounts = AccountRepo.findAll();
        if (!allAccounts.iterator().hasNext()) {
            logger.error("Account Not Found");
            throw new ResourceNotFoundException("Error fetching account");

        }
        logger.info("All Accounts retrieved");
        return AccountRepo.findAll();
    }

    public Account createAccount(Long customerId, Account account) {

        verifyCostumer(customerId);
        logger.info("Successfully created Account");
        return AccountRepo.save(account);
    }

    public Account updateAccount(Account updatedAccount, Long accountId) {
        // Save the entity
        Optional<Account> accountOptional = AccountRepo.findById(accountId);

        if (accountOptional.isEmpty()) {
            logger.error("Couldn't update Account");
            throw new ResourceNotFoundException("Account not found with ID: " + accountId);
        }

        Account existingAccount = accountOptional.get();

        existingAccount.setBalance(updatedAccount.getBalance());
        existingAccount.setNickname(updatedAccount.getNickname());
        existingAccount.setType(updatedAccount.getType());
        existingAccount.setRewardPoints(updatedAccount.getRewardPoints());

        logger.info("Successfully Updated Account");
        return existingAccount;
    }




        public void deleteAccount (Long accountId){
            verifyCostumer(accountId);

            logger.info("Successfully deleted Account");
            AccountRepo.deleteById(accountId);
        }

    }

