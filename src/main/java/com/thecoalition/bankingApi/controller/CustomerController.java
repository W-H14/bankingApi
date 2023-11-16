package com.thecoalition.bankingApi.controller;

import com.thecoalition.bankingApi.model.Customer;
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

    @RequestMapping(value="/customers", method= RequestMethod.GET)

    public ResponseEntity<Iterable<Customer>> getAllCustomers(){
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<?> createCustomer(@Validated @RequestBody Customer customer){
         Customer newCustomer = customerService.addCustomer(customer);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

//    @GetMapping("/accounts/{accountid}/customer")
//    public ResponseEntity<?> getCustomer(@PathVariable Long customerId) throws Exception {
//        Optional<Customer> customer =  customerService.getCustomerById(customerId);
//
//        if(!customer.isPresent()){
//            throw new Exception("Customer not found");
//        }
//
//        return new ResponseEntity<>(customer.get(), HttpStatus.OK);
//    }



    @GetMapping("/customers/{customerId}")
    public ResponseEntity<?> getCustomer(@PathVariable Long customerId) throws Exception {
        Optional<Customer> customer =  customerService.getCustomerById(customerId);

        if(!customer.isPresent()){
            throw new Exception("Customer not found");
        }

        return new ResponseEntity<>(customer.get(), HttpStatus.OK);
    }

    @PutMapping("/customers/{customerId}")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable Long customerId){
        customerService.editCustomer(customer, customerId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId){
        customerService.deleteCustomer(customerId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
