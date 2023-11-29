
//package com.thecoalition.bankingApi.service;
//
//
//import com.thecoalition.bankingApi.model.Bill;
//import com.thecoalition.bankingApi.repository.BillRepository;
//
//import java.util.Optional;
//
//
//public class BillService {
//
//    private BillRepository billRepository;
//    public Bill createBill(Bill bill){
//        return billRepository.save(bill);
//    }
//
//    public Iterable<Bill> getBills(){
//        return billRepository.findAll();
//    }
//    public Optional<Bill> getBillByAccount(Long id){
//        return billRepository.findById(id);
//    }
//    public Bill editBill(Long id, Bill bill){
//        Optional<Bill> existingPollOptional = billRepository.findById(id);
//        if(existingPollOptional.isEmpty()) {
////            throw new ResponseStatusExceptionHandler()
//        }
//        Bill existingBill = existingPollOptional.get();
//        existingBill.setId(bill.getId());
////        existingBill.setStatus(bill.getStatus( ));
//        existingBill.setAccount_id(bill.getAccount_id());
//        existingBill.setNickname(bill.getNickname());
//        existingBill.setPayee(bill.getPayee());
//        existingBill.setCreation_date(bill.getCreation_date());
//        existingBill.setPayment_amount(bill.getPayment_amount());
//        existingBill.setPayment_date(bill.getPayment_date());
//        existingBill.setRecurring_date(bill.getRecurring_date());
//        return billRepository.save(existingBill);
//    }
//
//    public void removeBill(Long id){
//        billRepository.deleteById(id);
//
//    }
//}

package com.thecoalition.bankingApi.service;


import com.thecoalition.bankingApi.handler.exceptions.BillNotFoundException;
import com.thecoalition.bankingApi.handler.exceptions.ResourceNotFoundException;
import com.thecoalition.bankingApi.model.Bill;
import com.thecoalition.bankingApi.repository.AccountRepository;
import com.thecoalition.bankingApi.repository.BillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private AccountService accountService;
@Autowired
    private AccountRepository accountRepository;


    private final Logger logger = LoggerFactory.getLogger(BillService.class);



    public void verifyAccount(Long accountId) throws ResourceNotFoundException {
        Optional<Bill> bill = billRepository.findById(accountId);
        if (bill.isEmpty()) {
            logger.error("Account Not Verified");
            throw new ResourceNotFoundException("Account with id " + accountId + " not found");
        }
    }
    public Bill createBill(Bill bill, Long accountId){
        try {
            var account = accountRepository.findById(accountId);
          if (account.isEmpty()){
              throw new ResourceNotFoundException("Account not found");
          }bill.setAccount(account.get());
            return billRepository.save(bill);
        } catch (Exception e) {
            logger.error("Error creating bill: Account not found", e);
            throw new ResourceNotFoundException("Error creating bill: Account not found");

        }
   /*     verifyAccount(accountId);
        logger.info("Account verified");
        try {
            logger.info("Bill created");
            bill.setAccount(accountRepository.findById(accountId).get());
            return billRepository.save(bill);
        } catch (Exception e) {
            logger.error("Error creating bill: Account not found", e);
            throw new ResourceNotFoundException("Error creating bill: Account not found");
        }

    */
    }

    public Iterable<Bill> getBills(){
        try {
            logger.info("Successfully retrieved bill");
            return billRepository.findAll();
        } catch (Exception e) {
            logger.error("Error fetching bills with Id", e);
            throw new BillNotFoundException("Error fetching bills with Id");
        }
    }

    public void verifyByCostumerId(Long customerId) throws ResourceNotFoundException {
        Optional<Bill> bill = billRepository.findById(customerId);
        if (bill.isEmpty()) {
            logger.error("Customer Not Verified");
            throw new ResourceNotFoundException("Costumer with id " + customerId + " not found");
        }
    }


    public Optional<Bill> getAllCustomerBills(Long customer_Id )throws ResourceNotFoundException{
        try {
            billRepository.findById(customer_Id);
        } catch (Exception e) {
            logger.error("Error fetching bills", e);
            throw new BillNotFoundException("Error fetching bills");
        }
        return billRepository.findById(customer_Id);
    }

    public Optional<Bill> getBillByAccount(Long account_id) {
        try {
            logger.info("Successfully retrieved bill by Account ID");
            return billRepository.findById(account_id);
        } catch (Exception e) {
            logger.error("Error fetching bills with id" + account_id, e);
            throw new BillNotFoundException("Error fetching bills with id" + account_id);
        }
    }

    public Optional<Bill> getBillById(Long billId) {
        logger.info("Trying to retrieve bill by Bill ID");

        Optional<Bill> optionalBill = billRepository.findById(billId);

        if (optionalBill.isEmpty()) {
            logger.error("Error fetching bill with id: " + billId);
            throw new BillNotFoundException("Error fetching bill with id: " + billId);
        }

        return optionalBill;
    }

    public Bill editBill(Long billId, Bill bill){
        Optional<Bill> existingBillOptional = billRepository.findById(billId);
        if (existingBillOptional.isEmpty()) {
            logger.error("Bill ID does not exist");
            throw new BillNotFoundException("Bill ID does not exist");
        }
        Bill existingBill = existingBillOptional.get();
        existingBill.setId(bill.getId());
        existingBill.setStatus(bill.getStatus());
        existingBill.setPayee(bill.getPayee());
        existingBill.setNickname(bill.getNickname());
        existingBill.setCreation_date(bill.getCreation_date());
        existingBill.setPayment_date(bill.getPayment_date());
        existingBill.setRecurring_date(bill.getRecurring_date());
        existingBill.setUpcoming_payment(bill.getUpcoming_payment());
        existingBill.setAccount(bill.getAccount());
        existingBill.setPayment_amount(bill.getPayment_amount());

        logger.info("Successfully updated Bill");
        billRepository.save(existingBill);
        return existingBill;
    }

    public void removeBill(Long id){
        if (!billRepository.existsById(id)) {
            logger.error("This id does not exist in bills");
            throw new BillNotFoundException("This id does not exist in bills");
        }
        logger.info("Bill was Successfully deleted");
        billRepository.deleteById(id);

    }

    }





//    public Bill createBill(Bill bill){
//        logger.info("Bill created");
//        return billRepository.save(bill);
//    }
//
//    public Iterable<Bill> getBills(){
//        logger.info("Successfully retrieved bill");
//        return billRepository.findAll();
//    }
//
//    public void verifyByCostumerId(Long customerId) throws ResourceNotFoundException {
//        Optional<Bill> bill = billRepository.findById(customerId);
//        if (bill.isEmpty()) {
//            logger.error("Customer Not Verified");
//            throw new ResourceNotFoundException("Costumer with id " + customerId + " not found");
//        }
//    }
//
//
//    public void getAllCustomerBills()throws ResourceNotFoundException{
//        billRepository.findAll();
//
//    }
//
//    public Optional<Bill> getBillByAccount(Long account_id){
//        logger.info(" Successfully retrieved bill by Account ID");
//        return billRepository.findById(account_id);
//    }
//
//    public Optional<Bill> getBillById(Long billId) {
//        logger.info("Successfully retrieved bill by Bill ID");
//        return billRepository.findById(billId);
//    }
//
//    public void editBill(Long billId, Bill bill){
//        Optional<Bill> existingBillOptional = billRepository.findById(billId);
//        if(existingBillOptional.isEmpty()) {
////            throw new ResponseStatusExceptionHandler("Bill with ID "+ billId + " not found")
//        }
//        Bill existingBill = existingBillOptional.get();
//        existingBill.setId(bill.getId());
//        existingBill.setStatus(bill.getStatus());
//        existingBill.setPayee(bill.getPayee());
//        existingBill.setNickname(bill.getNickname());
//        existingBill.setCreation_date(bill.getCreation_date());
//        existingBill.setPayment_date(bill.getPayment_date());
//        existingBill.setRecurring_date(bill.getRecurring_date());
//        existingBill.setUpcoming_payment(bill.getUpcoming_payment());
//        existingBill.setAccount_id(bill.getAccount_id());
//        existingBill.setPayment_amount(bill.getPayment_amount());
//
//        logger.info("Successfully updated Bill");
//        billRepository.save(existingBill);
//    }
//
//    public void removeBill(Long id){
//        logger.info("Bill was Successfully deleted");
//        billRepository.deleteById(id);
//
//    }
//
//
//}
//
//
