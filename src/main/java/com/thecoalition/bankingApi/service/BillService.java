package com.thecoalition.bankingApi.service;


import com.thecoalition.bankingApi.model.Bill;
import com.thecoalition.bankingApi.repository.BillRepository;
import com.thecoalition.bankingApi.utility.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;
    public Bill createBill(Bill bill){
        return billRepository.save(bill);
    }

    public Iterable<Bill> getBills(){
        return billRepository.findAll();
    }

    public Optional<Bill> getBillByAccount(Long account_id){
        return billRepository.findById(account_id);
    }

    public Optional<Bill> getBillById(Long billId) {
        return billRepository.findById(billId);
    }

    public void editBill(Long billId, Bill bill){
        Optional<Bill> existingBillOptional = billRepository.findById(billId);
        if(existingBillOptional.isEmpty()) {
//            throw new ResponseStatusExceptionHandler("Bill with ID "+ billId + " not found")
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
        existingBill.setAccount_id(bill.getAccount_id());
        existingBill.setPayment_amount(bill.getPayment_amount());
        billRepository.save(existingBill);
    }

    public void removeBill(Long id){
        billRepository.deleteById(id);

    }


}

