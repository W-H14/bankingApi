<<<<<<< HEAD
//package com.thecoalition.bankingApi.controller;
//
//
//import com.thecoalition.bankingApi.model.Bill;
//import com.thecoalition.bankingApi.repository.BillRepository;
//import com.thecoalition.bankingApi.service.BillService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//public class BillController {
//    @Autowired
//    private BillService billService;
//
//    @GetMapping(value = "/accounts/{accountId}/bills")// get all bills for specific account
//
////    @GetMapping(value = "/bills/{billId}")//get bill by id
////
////    @GetMapping(value = "customers/{customerId}/bills")// get bill by id
////
////    @PostMapping(value = "/accounts/{accountId}/bills")// create a bill
////    public ResponseEntity<?> createBill(@PathVariable Long pollId, @RequestBody Bill bill){
////        bill = billService.createBill(bill);
////
//////        HttpHeaders responseHeaders = new HttpHeaders();
//////        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bill.getId().));
////        return new ResponseEntity<>(null,responseHeaders,HttpStatus.CREATED);
////    }
//    //    @PutMapping(value = "bills/{billId}") //update A specific existing bill
////
//    @DeleteMapping(value = "/bills/{billId}")// delete a specific existing bill
//    public ResponseEntity<?> removeBillById(@PathVariable Long billId){
//        billService.removeBill(billId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//}
//
=======
package com.thecoalition.bankingApi.controller;


import com.thecoalition.bankingApi.model.Bill;
import com.thecoalition.bankingApi.model.Customer;
import com.thecoalition.bankingApi.service.BillService;
import com.thecoalition.bankingApi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class BillController {
    @Autowired
    private BillService billService;
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/accounts/{accountId}/bills")// get all bills for specific account
    public ResponseEntity<?> getBillByAccount(@PathVariable Long accountId){
        billService.getBillByAccount(accountId);
        return new ResponseEntity<>(billService.getBillByAccount(accountId),HttpStatus.OK);
    }

    @GetMapping(value = "/bills/{billId}")//get bill by id
    public Optional<Bill> getBillById(@PathVariable Long billId){
        return billService.getBillById(billId);

    }

    @GetMapping(value = "customers/{customerId}/bills")// get bill by id
    public Optional<Customer> getBillByCustomerId(@PathVariable Long customerId){
        return customerService.getCustomerById(customerId);

    }

    @PostMapping(value = "/accounts/{accountId}/bills")// create a bill
    public ResponseEntity<?> createBill(@RequestBody Bill bill){
        bill = billService.createBill(bill);

        HttpHeaders responseHeaders = new HttpHeaders();



        return new ResponseEntity<>(responseHeaders,HttpStatus.CREATED);
    }
    @PutMapping(value = "bills/{billId}") //update A specific existing bill
    public ResponseEntity<?>updateBill(@RequestBody Bill bill, @PathVariable Long billId){
        billService.editBill(billId,bill);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping(value = "/bills/{billId}")// delete a specific existing bill
    public ResponseEntity<?> removeBillById(@PathVariable Long billId){
        billService.removeBill(billId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
>>>>>>> 7e6b67cf2ee8d8f40557952105ed9bc57a41349f
