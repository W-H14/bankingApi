package com.thecoalition.bankingApi.response;

import com.thecoalition.bankingApi.model.Account;
import com.thecoalition.bankingApi.service.AccountService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.thecoalition.bankingApi.dto.Body;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountResponse {


    @Autowired
    private AccountService accountService;



    @Autowired
    private CustomerService customerService;


    public ResponseEntity<?> createAccount(Long costumerId, Account account) {
        accountService.verifyCostumer(costumerId);
        accountService.createAccount(costumerId, account);
        Body body = new Body();
        body.setData(account);
        body.setCode(HttpStatus.CREATED.value());
        body.setMessage("Account created successfully");
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }





    public ResponseEntity<?> getAllAccounts() {
      try {
          Body body = new Body();
          body.setData(accountService.getAllAccounts());
          body.setCode(HttpStatus.OK.value());
          body.setMessage("Success");
          return new ResponseEntity<>(body, HttpStatus.OK);
      }catch(Exception exception){
          Body body = new Body();
          body.setCode(HttpStatus.NOT_FOUND.value());
          body.setMessage("Error Fetching Accounts");
          return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
      }
    }



    public ResponseEntity<?> getAccountById(Long accountId) throws ResourceNotFoundException {

        Optional<Account> account = accountService.getAccount(accountId);
        if (!account.isPresent()) {
            Body body = new Body();
            body.setCode(HttpStatus.NOT_FOUND.value());
            body.setMessage("error fetching account");
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }else {
            Body body = new Body();
            body.setCode(HttpStatus.OK.value());
            body.setMessage("Account retrieval Success");
            return new ResponseEntity<>(body, HttpStatus.OK);
        }
    }



    public ResponseEntity<?> deleteAccount(Long accountId) {
       try {
           accountService.deleteAccount(accountId);
           Body body = new Body();
           body.setCode(HttpStatus.NO_CONTENT.value());
           body.setMessage("Account successfully deleted");
           return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
       }catch (Exception e)
    }






    public ResponseEntity<?> updateAccount(Account updatedAccount, Long accountId) {
        accountService.updateAccount(updatedAccount, accountId);
        Body body = new Body();
        body.setData(updatedAccount);
        body.setCode(HttpStatus.OK.value());
        body.setMessage("Customer Account Updated");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }






}
