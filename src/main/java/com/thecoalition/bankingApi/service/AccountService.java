package com.thecoalition.bankingApi.service;

import com.thecoalition.bankingApi.handler.exceptions.AccountNotFoundException;
import com.thecoalition.bankingApi.model.*;
import com.thecoalition.bankingApi.repository.ActivityRepository;
import com.thecoalition.bankingApi.repository.AccountRepository;
import com.thecoalition.bankingApi.handler.exceptions.ResourceNotFoundException;
import com.thecoalition.bankingApi.repository.CustomerRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private WithdrawalService withdrawalService;
    @Autowired
    private DepositService depositService;



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

        return accountRepository.findById(accountId);

    }



    public Iterable<Account> getAllAccounts() throws AccountNotFoundException{
        Iterable<Account> allAccounts = accountRepository.findAll();
        if (!allAccounts.iterator().hasNext()) {
            logger.error("Account Not Found");
            throw new AccountNotFoundException("Error fetching account");

        }
        logger.info("All Accounts retrieved");
        return accountRepository.findAll();
    }





    public Account createAccount(Long customerId, Account account) {
        verifyCostumer(customerId);

        try {
            logger.info("Successfully created Account");
            Customer getCustomer = customerRepository.findById(customerId).get();
            account.setCustomer(getCustomer);

            //saving account
            Account savedAccount = accountRepository.save(account);

            logger.info("account and activity were successfully Created");
            return savedAccount;

        } catch (Exception e) {
            logger.error("Error fetching creating customers account", e);
            throw new RuntimeException("Error fetching creating customers account");
        }
    }



    public Account updateAccount(Account updatedAccount, Long accountId) throws AccountNotFoundException{
        // Save the entity
        Optional<Account> accountOptional = accountRepository.findById(accountId);
if (accountOptional.isPresent()) {
    Account editThisAccount = accountOptional.get();
    editThisAccount.setBalance(updatedAccount.getBalance());
    editThisAccount.setType(updatedAccount.getType());
    editThisAccount.setNickname(updatedAccount.getNickname());
    editThisAccount.setRewardPoints(updatedAccount.getRewardPoints());



    logger.info("Account was Successfully Updated");
    return accountRepository.save(editThisAccount);
}else {
        logger.error("Unsuccessful Attempt to edit. Account not found");
        throw new AccountNotFoundException("Error updating Account with id " + accountId );
    }
}
public Iterable<Account> getAllAccountsForCostumer(Long customer_id){
       logger.info("Retrieved all accounts for customer successfully");
        verifyCostumer(customer_id);
    return accountRepository.findByCustomer_Id(customer_id);


}




    public void deleteAccount(Long accountId){
Account account = accountRepository.findById(accountId).get();
        accountRepository.delete(account);
        logger.info("Account deleted successfully");
    }

    }

