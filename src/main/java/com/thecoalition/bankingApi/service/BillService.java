package com.thecoalition.bankingApi.service;


import com.thecoalition.bankingApi.model.Bill;
import com.thecoalition.bankingApi.repository.BillRepository;

import java.util.Optional;


public class BillService {

    private BillRepository billRepository;
    public Bill createBill(Bill bill){
        return billRepository.save(bill);
    }

    public Iterable<Bill> getBills(){
        return billRepository.findAll();
    }
    public Optional<Bill> getBillByAccount(Long id){
        return billRepository.findById(id);
    }
    public Bill editBill(Long id, Bill bill){
        Optional<Bill> existingPollOptional = billRepository.findById(id);
        if(existingPollOptional.isEmpty()) {
//            throw new ResponseStatusExceptionHandler()
        }
        Bill existingBill = existingPollOptional.get();
        existingBill.setId(bill.getId());
//        existingBill.setStatus(bill.getStatus( ));
        existingBill.setAccount_id(bill.getAccount_id());
        existingBill.setNickname(bill.getNickname());
        existingBill.setPayee(bill.getPayee());
        existingBill.setCreation_date(bill.getCreation_date());
        existingBill.setPayment_amount(bill.getPayment_amount());
        existingBill.setPayment_date(bill.getPayment_date());
        existingBill.setRecurring_date(bill.getRecurring_date());
        return billRepository.save(existingBill);
    }

    public void removeBill(Long id){
        billRepository.deleteById(id);

    }
}

