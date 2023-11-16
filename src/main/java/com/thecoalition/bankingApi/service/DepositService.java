package com.thecoalition.bankingApi.service;

import com.thecoalition.bankingApi.model.Deposit;
import com.thecoalition.bankingApi.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepositService {
    @Autowired
    DepositRepository depositRepository;
    //Create deposit
    public Deposit createDeposit(Long acountId, Deposit deposit){
        return depositRepository.save(deposit);
    }
    //get all from an account
    public Iterable<Deposit> getAllDeposits(Long accountId){
        Iterable<Deposit> allDeposits = depositRepository.findAll();
        return depositRepository.findByAccount(accountId);
    }
    //Get deposit by id
    public Optional<Deposit> getDeposit(Long id){
        return depositRepository.findById(id);

    }//

    //Delete
    public void deleteDeposit(Long id){

        depositRepository.deleteById(id);

    }
    //edit a deposit
    public Deposit editDeposit(Long id, Deposit deposit) {
        Optional<Deposit> tempDeposit = getDeposit(id);
        Deposit d = tempDeposit.get();
        d.setStatus(deposit.getStatus());
        d.setDescription(deposit.getDescription());

        depositRepository.save(d);
        return d;
    }
}

