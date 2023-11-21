package com.thecoalition.bankingApi.service;

import com.thecoalition.bankingApi.model.Account;
import com.thecoalition.bankingApi.repository.AccountRepository;
import com.thecoalition.bankingApi.handler.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
public class AccountService {

    @Autowired
    private AccountRepository AccountRepo;
    @Autowired
    private CustomerService customerService;



    public void verifyCostumer(Long CostumerId) throws ResourceNotFoundException {
        Optional<Customer> costumer = customerService.getCustomerById(CostumerId);
        if (costumer.isEmpty()) {
            throw new ResourceNotFoundException("Costumer with id " + CostumerId + " not found");
        }
    }




    public Iterable<Account> getAllAccounts() {

                return AccountRepo.findAll();
    }

    public Account createAccount(Long costusmerId,Account account) {
        verifyCostumer(costusmerId);
       return AccountRepo.save(account);
    }

    public Optional<Account> getAccount(Long AccountId) {
        verifyCostumer(AccountId);
        return AccountRepo.findById(AccountId);
    }

    public Account updateAccount(Account updatedAccount, Long accountId) {
        // Save the entity
        Optional<Account> accountOptional = AccountRepo.findById(accountId);
        // if (AccountOptional.isEmpty()) {
        //     throw new ResourceNotFoundException(;
        //  }
        Account existingAccount = accountOptional.get();

        existingAccount.setBalance(updatedAccount.getBalance());
        existingAccount.setNickname(updatedAccount.getNickname());
        existingAccount.setType(updatedAccount.getType());
        existingAccount.setRewardPoints(updatedAccount.getRewardPoints());


        return existingAccount;
    }

    public void deleteAccount(Long accountId) {
        AccountRepo.deleteById(accountId);
    }

}