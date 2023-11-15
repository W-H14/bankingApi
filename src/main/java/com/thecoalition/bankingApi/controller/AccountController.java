package com.thecoalition.bankingApi.controller
import com.thecoalition.bankingApi.model.Account;
import com.thecoalition.bankingApi.repository.AccountRepository;
import com.thecoalition.bankingApi.repository.AccountRepository;
import com.thecoalition.bankingApi.service.AccountService;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

public class AccountController {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private AccountService accountService;


    public void verifyCostumer(Long CostumerId) throws ResourceNotFoundException {
        Optional<Costumer> costumer = costumerService.getCostumer(CostumerId);
        if (Costumer.isEmpty()) {
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

}