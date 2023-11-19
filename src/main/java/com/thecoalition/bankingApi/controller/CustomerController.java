package com.thecoalition.bankingApi.controller;

import com.thecoalition.bankingApi.model.Customer;
import com.thecoalition.bankingApi.repository.CustomerRespository;
import com.thecoalition.bankingApi.response.CustomerResponse;
import com.thecoalition.bankingApi.service.CustomerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired

    private CustomerService customerService;

   @Autowired
    CustomerRespository customerRespository;

   @Autowired
    CustomerResponse costumerResponse;



    @GetMapping(value="/customers")

    public ResponseEntity<?> getAllCustomers(){
        return new ResponseEntity<>(costumerResponse.getAllCostumers(), HttpStatus.OK);
    }


    @PostMapping("/customers")
    public ResponseEntity<?> createCustomer(@Validated @RequestBody Customer customer){

        return new ResponseEntity<>(customerService.addCustomer(customer),HttpStatus.CREATED);
    }





    @GetMapping("/customers/{customerId}")
    public ResponseEntity<?> getCustomer(@PathVariable Long customerId)  {
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PutMapping("/customers/{customerId}")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable Long customerId){


        return new ResponseEntity<>( costumerResponse.updateCustomer(customer,customerId),HttpStatus.OK);
    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId){


        return new ResponseEntity<>(costumerResponse.deleteCustomer(customerId),HttpStatus.NO_CONTENT);
    }

    @GetMapping("/accounts/{accountId}customer")
    public ResponseEntity<?> getCustomerByAccountId(@PathVariable Long accountId){
        return new ResponseEntity<>(customerService.getCustomerByAccountId(accountId),HttpStatus.OK);
    }


    /*
     @GetMapping("/customers/{customerId}/accounts")
    public ResponseEntity<Iterable<Account>> getAllAccountsForCustomer(@PathVariable Long customerId){
        verifyCostumer(customerId);

        Iterable<Account> accounts = accountRepo.findByCustomerId(customerId);

        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

     */

}
