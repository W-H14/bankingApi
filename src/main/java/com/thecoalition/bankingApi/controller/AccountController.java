package com.thecoalition.bankingApi.controller;

import com.thecoalition.bankingApi.model.Account;
import com.thecoalition.bankingApi.repository.AccountRepository;
import com.thecoalition.bankingApi.response.AccountResponse;
import com.thecoalition.bankingApi.service.AccountService;


import com.thecoalition.bankingApi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountResponse accountResponse;


    /**
     * Create a new account for a customer
     * @param costumerId
     * @param account
     * @return
     */
    @PostMapping(value = "/customers/{costumerId}/accounts")
    public ResponseEntity<?> createAccount(@PathVariable Long costumerId, @RequestBody Account account) {
       return new ResponseEntity<> (accountResponse.createAccount(costumerId, account), HttpStatus.CREATED);

    }

    /**
     * Delete an account
     * @param accountId
     * @return
     */
    @DeleteMapping(value = "/accounts/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId){
         return accountResponse.deleteAccount(accountId);
    }

    /**
     * Get all accounts
     * @return
     */
@GetMapping(value = "/accounts")
    public ResponseEntity<?> getAllAccounts(){

    return  new ResponseEntity<>  (accountResponse.getAllAccounts(), HttpStatus.OK);
    }

    /**
     * Get an account by id
     * @param accountId
     * @return
     */
    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountId){
      return accountResponse.getAccountById(accountId)   ;
    }

    /**
     * Update an account
     * @param accountId
     * @param updatedAccount
     * @return
     */
    @PutMapping("/accounts/{accountId}")
    public ResponseEntity<?> updateAccount(@PathVariable Long accountId, @RequestBody Account updatedAccount){
        return accountResponse.updateAccount(updatedAccount, accountId);
    }

    /**
     * Get all accounts for a customer
     * @param customerId
     * @return
     */
   @GetMapping("/customers/{customerId}/accounts")
    public ResponseEntity<?> getAllAccountsForCostumer(@PathVariable Long customerId){
        return accountResponse.getAllAccountsForCostumer(customerId);
    }
}
