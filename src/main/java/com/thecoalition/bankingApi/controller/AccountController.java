package com.thecoalition.bankingApi.controller;

import com.thecoalition.bankingApi.model.Account;
import com.thecoalition.bankingApi.model.Customer;
import com.thecoalition.bankingApi.repository.AccountRepository;
import com.thecoalition.bankingApi.service.AccountService;


import com.thecoalition.bankingApi.service.CustomerService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

public class AccountController {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;


    public void verifyCostumer(Long CostumerId) throws ResourceNotFoundException {
        Optional<Customer> costumer = customerService.getCustomerById(CostumerId);
        if (costumer.isEmpty()) {
            throw new ResourceNotFoundException("Costumer with id " + CostumerId + " not found");
        }
    }


    @RequestMapping(value = "/costumers/{costumerId}/accounts", method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(@PathVariable Long costumerId, @RequestBody Account account) {
        verifyCostumer(costumerId);
        accountService.createAccount(costumerId, account);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(account.getAccountId()).toUri());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @RequestMapping(value = "/accounts//{accountId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId){
        accountService.deleteAccount(accountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //endpoint to get all accounts
    @GetMapping("/accounts")
    public ResponseEntity<Iterable<Account>> getAllAccounts(){
        Iterable<Account> accounts = accountRepo.findAll();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
    //endpoint for an account by Id
    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId){
      Optional<Account> account = accountRepo.findById(accountId);
      return account.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //get all accounts for a customer
    @GetMapping("/customers/{customerId}/accounts")
    public ResponseEntity<Iterable<Account>> getAllAccountsForCustomer(@PathVariable Long customerId){
        verifyCostumer(customerId);

        Iterable<Account> accounts = accountRepo.findByCustomerId(customerId);

        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    //update an existing account
    @PutMapping("{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long accountId, @RequestBody Account updatedAccount){
        try{
            Account updated = accountService.updateAccount(updatedAccount, accountId);
            return new ResponseEntity<>(updated, HttpStatus.OK);

        }catch(ResourceNotFoundException exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
