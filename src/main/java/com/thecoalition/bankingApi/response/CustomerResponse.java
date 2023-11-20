package com.thecoalition.bankingApi.response;

import com.thecoalition.bankingApi.dto.Body;
import com.thecoalition.bankingApi.model.Customer;
import com.thecoalition.bankingApi.service.AccountService;
import com.thecoalition.bankingApi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class CustomerResponse {

    @Autowired
    private AccountService accountService;


    @Autowired
    private CustomerService customerService;


    public ResponseEntity<?> createCustomer(Customer customer) {
        customerService.addCustomer(customer);
        Body body = new Body();
        body.setData(customer);
        body.setCode(HttpStatus.CREATED.value());
        body.setMessage("Customer created successfully");
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getAllCostumers() {
        Body body = new Body();
        body.setData(customerService.getCustomers());
        body.setCode(HttpStatus.OK.value());
        body.setMessage("Success");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }


    public ResponseEntity<?> getCustomerById(Long customerId) {
        Body body = new Body();
        body.setData(customerService.getCustomerById(customerId));
        body.setCode(HttpStatus.OK.value());
        body.setMessage("Success");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    public ResponseEntity<?> updateCustomer(Customer customer, Long customerId) {
        customerService.editCustomer(customer, customerId);
        Body body = new Body();
        body.setData(customer);
        body.setCode(HttpStatus.OK.value());
        body.setMessage("Customer updated successfully");
        return new ResponseEntity<>(body, HttpStatus.OK);


    }

public ResponseEntity<?> deleteCustomer(Long customerId) {
        customerService.deleteCustomer(customerId);
        Body body = new Body();
        body.setCode(HttpStatus.NO_CONTENT.value());
        body.setMessage("Customer deleted successfully");
        return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
    }


    public ResponseEntity<?> getCustomerByAccountId(Long accountId) {
        Body body = new Body();
        body.setData(customerService.getCustomerByAccountId(accountId));
        body.setCode(HttpStatus.OK.value());
        body.setMessage("Success");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}

