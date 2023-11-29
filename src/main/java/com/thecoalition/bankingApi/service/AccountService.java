package com.thecoalition.bankingApi.service;

import ch.qos.logback.classic.joran.action.LoggerAction;
import com.thecoalition.bankingApi.handler.exceptions.AccountNotFoundException;
import com.thecoalition.bankingApi.model.Account;
import com.thecoalition.bankingApi.model.Customer;
import com.thecoalition.bankingApi.repository.AccountRepository;
import com.thecoalition.bankingApi.handler.exceptions.ResourceNotFoundException;
import com.thecoalition.bankingApi.repository.CustomerRepository;
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

    @Autowired
    private CustomerRepository customerRepository;


    private final org.slf4j.Logger logger = LoggerFactory.getLogger(AccountService.class);

    public void verifyCostumer(Long CostumerId)  {
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



    public Iterable<Account> getAllAccounts() throws AccountNotFoundException{
        Iterable<Account> allAccounts = AccountRepo.findAll();
        if (!allAccounts.iterator().hasNext()) {
            logger.error("Account Not Found");
            throw new AccountNotFoundException("Error fetching account");

        }
        logger.info("All Accounts retrieved");
        return AccountRepo.findAll();
    }





    public Account createAccount(Long customerId, Account account) {
        verifyCostumer(customerId);

        try {
            logger.info("Successfully created Account");
            Customer getCustomer = customerRepository.findById(customerId).get();
            account.setCustomer(getCustomer);
            return AccountRepo.save(account);

        } catch (Exception e) {
            logger.error("Error fetching creating customers account", e);
            throw new RuntimeException("Error fetching creating customers account");
        }
    }



    public Account updateAccount(Account updatedAccount, Long accountId) throws AccountNotFoundException{
        // Save the entity
        Optional<Account> accountOptional = AccountRepo.findById(accountId);
if (accountOptional.isEmpty()) {
    Account editThisAccount = getAccountById(accountId).get();
    editThisAccount.setBalance(updatedAccount.getBalance());
    editThisAccount.setType(updatedAccount.getType());
    editThisAccount.setNickname(updatedAccount.getNickname());
    editThisAccount.setRewardPoints(updatedAccount.getRewardPoints());

    logger.info("Account was Successfully Updated");
    return AccountRepo.save(editThisAccount);
}else {
        logger.error("Unsuccessful Attempt to edit. Account not found");
        throw new AccountNotFoundException("Error updating Account with id " + accountId );
    }
}





        public void deleteAccount (Long accountId) throws AccountNotFoundException{
            if (!AccountRepo.existsById(accountId)) {
                logger.error("Account does not exist");
                throw new AccountNotFoundException("Account does not exist");
            }

            logger.info("Successfully deleted Account");
            AccountRepo.deleteById(accountId);
        }

    }

