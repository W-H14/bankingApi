package com.thecoalition.bankingApi.service;

import com.thecoalition.bankingApi.model.Deposit;
import com.thecoalition.bankingApi.repository.DepositRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepositService {
    @Autowired
    private DepositRepository depositRepository;
   private final Logger logger = LoggerFactory.getLogger(DepositService.class);

    //Create deposit
    public Deposit createDeposit(Long payee_id, Deposit deposit){
        logger.info("Deposit created ");

        return depositRepository.save(deposit);
    }
    //get all from an account
    public Optional<Deposit> getAllDeposits(Long accountId){
        logger.info("Successfully retrieved all deposits");

        return depositRepository.findByPayeeId(accountId);
    }
    //Get deposit by id
    public Optional<Deposit> getDeposit(Long id){
        logger.info("Successfully retrieved deposit by Id");
        return depositRepository.findById(id);

    }//

    //Delete
    public void deleteDeposit(Long id){

        logger.info("Successfully deleted Deposit by Id");
        depositRepository.deleteById(id);

    }
    //edit a deposit
    public Deposit editDeposit(Long depositId, Deposit deposit) {
        Optional<Deposit> tempDeposit = getDeposit(depositId);
        Deposit d = tempDeposit.get();
        d.setStatus(deposit.getStatus());
        d.setDescription(deposit.getDescription());

        logger.info("Successfully updated Deposit");
        depositRepository.save(d);
        return d;
    }
}
//    @Autowired
//    private DepositRepository depositRepository;
//
//    //Create deposit
//    public Deposit createDeposit(Long payee_id, Deposit deposit){
//        return depositRepository.save(deposit);
//    }
//    //get all from an account
//    public Optional<Deposit> getAllDeposits(Long accountId){
//
//        return depositRepository.findByPayeeId(accountId);
//    }
//    //Get deposit by id
//    public Optional<Deposit> getDeposit(Long id){
//        return depositRepository.findById(id);
//
//    }//
//
//    //Delete
//    public void deleteDeposit(Long id){
//
//        depositRepository.deleteById(id);
//
//    }
//    //edit a deposit
//    public Deposit editDeposit(Long depositId, Deposit deposit) {
//        Optional<Deposit> tempDeposit = getDeposit(depositId);
//        Deposit d = tempDeposit.get();
//        d.setStatus(deposit.getStatus());
//        d.setDescription(deposit.getDescription());
//
//        depositRepository.save(d);
//        return d;
//    }
