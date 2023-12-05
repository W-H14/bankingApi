
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

package com.thecoalition.bankingApi.controller;


import com.thecoalition.bankingApi.model.Bill;
import com.thecoalition.bankingApi.response.BillResponse;
import com.thecoalition.bankingApi.service.BillService;
import com.thecoalition.bankingApi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BillResponse billResponse;

    /**
     * gets bill by account
     * @param accountId
     * @return
     */
    @GetMapping(value = "/accounts/{accountId}/bills")// get all bills for specific account
    public ResponseEntity<?> getBillByAccount(@PathVariable Long accountId){
        return new ResponseEntity<>(billResponse.getBillByAccount(accountId),HttpStatus.OK);
    }

    /**
     * gets bill by bill id
     * @param billId
     * @return
     */
    @GetMapping(value = "/accounts/{accountId}/bills/{billId}")//get bill by id
    public ResponseEntity<?> getBillById(@PathVariable Long billId){
        return new ResponseEntity<>(billResponse.getBillById(billId),HttpStatus.OK);

    }

//<<<<<<< HEAD
//=======
//    /**
//     * get bill by customer id
//     * @param customerId
//     * @return
//     */
//>>>>>>> 9b4b07289967e6f5a34598e8f9d416c2a4ec991b
    @GetMapping(value = "/customers/{customerId}/bills")// get bill by id
    public ResponseEntity<?> getBillByCustomerId(@PathVariable Long customerId){
        return new ResponseEntity<>(billResponse.getBillByCustomerId(customerId),HttpStatus.OK);

    }

    /**
     * gets all bills for a customer
     * @param customerId
     * @return
     */
    @GetMapping(value = "/customers/{customerId}/allbills")//Action: Get all bills for customer
    public ResponseEntity<?> getAllBillsForCustomer(@PathVariable Long customerId){
        return new ResponseEntity<>(billResponse.getAllBills(customerId),HttpStatus.OK);
    }

    /**
     * creates a bill
     * @param bill
     * @return
     */
    @PostMapping(value = "/accounts/{accountId}/bills")// create a bill
    public ResponseEntity<?> createBill(@RequestBody Bill bill){
        bill = billService.createBill(bill);
        return new ResponseEntity<>(billResponse.createBill(bill),HttpStatus.CREATED);
    }

    /**
     * updates a bill
     * @param bill
     * @param billId
     * @return
     */
    @PutMapping(value = "bills/{billId}") //update A specific existing bill
    public ResponseEntity<?>updateBill(@RequestBody Bill bill, @PathVariable Long billId){
        billService.editBill(billId,bill);
        return new ResponseEntity<>(billResponse.updateBill(bill,billId),HttpStatus.OK);
    }

    /**
     * deletes a bill by id
     * @param billId
     * @return
     */
    @DeleteMapping(value = "/bills/{billId}")// delete a specific existing bill
    public ResponseEntity<?> removeBillById(@PathVariable Long billId){
        billService.removeBill(billId);
        return new ResponseEntity<>(billResponse.removeBillById(billId),HttpStatus.NO_CONTENT);
    }
}

