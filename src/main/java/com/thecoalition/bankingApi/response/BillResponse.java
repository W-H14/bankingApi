package com.thecoalition.bankingApi.response;

import com.thecoalition.bankingApi.dto.Body;
import com.thecoalition.bankingApi.model.Bill;
import com.thecoalition.bankingApi.model.Customer;
import com.thecoalition.bankingApi.service.BillService;
import com.thecoalition.bankingApi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class  BillResponse {
    @Autowired
    BillService billService;

    @Autowired
    CustomerService customerService;

    public ResponseEntity<?> getBillByAccount(Long accountId){
        Iterable<Bill> bill1 = billService.getBillByAccount(accountId);

        Body body = new Body();
        body.setMessage("Bills for account " + accountId + " retrieved");
        body.setData(billService.getBills());
        body.setCode(HttpStatus.OK.value());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
    public ResponseEntity<?> getBillById(Long billId){
        Optional<Bill> bill = billService.getBillById(billId);

        Body body = new Body();
        body.setData(bill.get());
        body.setMessage("Bill with Id of " + billId + " Found");
        body.setCode(HttpStatus.OK.value());
        return new ResponseEntity<>(body,HttpStatus.OK);

    }
    public ResponseEntity<?> getBillByCustomerId(Long customerId){
        Optional<Bill> customer = billService.getAllCustomerBills(customerId);

        Body body = new Body();
        body.setData(customer.get());
        body.setMessage("Bill for customer " + customerId + " returned");
        body.setCode(HttpStatus.OK.value());
        return new ResponseEntity<>(body,HttpStatus.OK);
    }

    public ResponseEntity<?> createBill(Long accountId, Bill bill ) {


        Bill bill1 = billService.createBill(bill, accountId);

        Body body = new Body();
        body.setData(bill1);
        body.setCode(HttpStatus.CREATED.value());
        body.setMessage("Created bill and added it to the account");
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateBill(Bill bill, Long billId) {
        Bill bill1 = billService.editBill(billId, bill);

        Body body = new Body();
        body.setData(bill1);
        body.setCode(HttpStatus.ACCEPTED.value());
        body.setMessage("Accepted bill modification");
        return new ResponseEntity<>(body, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> getAllBills( Long billId){
       Optional<Bill> bills = billService.getBillById(billId);

        Body body = new Body();
        body.setData(bills);
        body.setCode(HttpStatus.ACCEPTED.value());
        body.setMessage("Bills Found");

        return new ResponseEntity<>(body,HttpStatus.OK);
    }

    public ResponseEntity<?> removeBillById(Long billId) {
        billService.removeBill(billId);

        Body body = new Body();
        body.setData(billId);
        body.setMessage("Bill " + billId + " REMOVED");
        body.setCode(HttpStatus.NO_CONTENT.value());
        return new ResponseEntity<>(body ,HttpStatus.NO_CONTENT);
    }
}