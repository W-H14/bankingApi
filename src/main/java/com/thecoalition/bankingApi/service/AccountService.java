package com.thecoalition.bankingApi.service;

import com.thecoalition.bankingApi.model.Account;
import com.thecoalition.bankingApi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;



    @Service
    public class AccountService {

        @Autowired
        AccountRepository AccountRepo;

        @Autowired

        public void getAllAccounts() {
            Iterable<Account> allAccounts = AccountRepo.findAll();
        }

        public void createAccount(Account account) {
            AccountRepo.save(account);
        }

        public Optional<Account> getAccount(Long AccountId) {
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

