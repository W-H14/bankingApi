package com.thecoalition.bankingApi.controller;

import com.thecoalition.bankingApi.model.Customer;
import com.thecoalition.bankingApi.repository.CustomerRepository;
import com.thecoalition.bankingApi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;
    //may need an additional autowired


    //get customer that owns the specified account
    @GetMapping(value = "/account/{accountId}/customer")
    public ResponseEntity<?> getCustomerByAccountId(@PathVariable Long accountId){
        return new ResponseEntity<>(customerService.getCustomerByAccountId(accountId), HttpStatus.OK);
    }

    //get customer by Id
    @GetMapping(value = "/customer/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long customerId){
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    //get all customers
    @GetMapping(value = "/customers")
    public ResponseEntity<?> getAllCustomer(){
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    //post -create a customer
    @PostMapping(value = "/customers")
    public ResponseEntity<?> createCustomer(@Validated @RequestBody Customer customer){
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
    }

    //put -update a specific existing customer
    @PutMapping(value = "/customers/{customerId}")
    public ResponseEntity<?> updateCustomerById(@RequestBody Customer customer, @PathVariable Long customerId){
        return new ResponseEntity<>(customerService.editCustomer(customer, customerId), HttpStatus.OK);
    }

    //delete customer
    @DeleteMapping(value = "/customers/{customerId}")
    public ResponseEntity<?> deleteCustomerById (@PathVariable Long customerId){
        return new ResponseEntity<>(customerService.deleteCustomer(customerId), HttpStatus.NO_CONTENT);
    }

}
