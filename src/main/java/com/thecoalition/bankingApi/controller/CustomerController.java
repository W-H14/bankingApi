package com.thecoalition.bankingApi.controller;

import com.thecoalition.bankingApi.model.Customer;
import com.thecoalition.bankingApi.repository.CustomerRepository;
import com.thecoalition.bankingApi.response.CustomerResponse;
import com.thecoalition.bankingApi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    private CustomerResponse customerResponse;

    @Autowired
    private CustomerRepository customerRepository;
    //may need an additional autowired





    /**
     * get customer by Id
     * @param customerId
     * @return
     */
    @GetMapping(value = "/customers/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long customerId){
        return new ResponseEntity<>(customerResponse.getCustomerById(customerId), HttpStatus.OK);
    }

    /**
     * get all customers
     * @return
     */
    @GetMapping(value = "/customers")
    public ResponseEntity<?> getAllCustomers(){
        return new ResponseEntity<>(customerResponse.getAllCustomers(), HttpStatus.OK);
    }

    /**
     * create a customer
     * @param customer
     * @return
     */
    @PostMapping(value = "/customers")
    public ResponseEntity<?> createCustomer(@Validated @RequestBody Customer customer){
        return new ResponseEntity<>(customerResponse.createCustomer(customer), HttpStatus.CREATED);
    }

    /**
     * update a specific existing customer
     * @param customer
     * @param customerId
     * @return
     */
    @PutMapping(value = "/customers/{customerId}")
    public ResponseEntity<?> updateCustomerById(@RequestBody Customer customer, @PathVariable Long customerId){
        return new ResponseEntity<>(customerResponse.updateCustomer(customer, customerId), HttpStatus.OK);
    }

    /**
     * deletes a customer
     * @param customerId
     * @return
     */
    @DeleteMapping(value = "/customers/{customerId}")
    public ResponseEntity<?> deleteCustomerById (@PathVariable Long customerId){
        return new ResponseEntity<>(customerResponse.deleteCustomer(customerId), HttpStatus.NO_CONTENT);
    }

}
