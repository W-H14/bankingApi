package com.thecoalition.bankingApi.controller;


import com.thecoalition.bankingApi.model.Bill;
import com.thecoalition.bankingApi.repository.BillRepository;
import com.thecoalition.bankingApi.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping(value = "/accounts/{accountId}/bills")// get all bills for specific account

//    @GetMapping(value = "/bills/{billId}")//get bill by id
//
//    @GetMapping(value = "customers/{customerId}/bills")// get bill by id
//
    @PostMapping(value = "/accounts/{accountId}/bills")// create a bill
    public ResponseEntity<?> createBill(@PathVariable Long pollId, @RequestBody Bill bill){
        bill = billService.createBill(bill);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bill.getId().));
        return new ResponseEntity<>(null,responseHeaders,HttpStatus.CREATED);
    }
    //    @PutMapping(value = "bills/{billId}") //update A specific existing bill
//
    @DeleteMapping(value = "/bills/{billId}")// delete a specific existing bill
    public ResponseEntity<?> removeBillById(@PathVariable Long billId){
        billService.removeBill(billId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

