package com.thecoalition.bankingApi.service;

import com.thecoalition.bankingApi.handler.exceptions.DepositNotFoundException;
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
    public Deposit createDeposit(Long payee_id, Deposit deposit) throws DepositNotFoundException{
        logger.info("Deposit created ");
        if(payee_id == null){
            logger.error("Error creating deposit: Account not found");
            throw new DepositNotFoundException("Error creating deposit: Account not found");
        }

        return depositRepository.save(deposit);
    }
    //get all from an account
    public Optional<Deposit> getAllDeposits(Long accountId) throws DepositNotFoundException {
        logger.info("Successfully retrieved all deposits");
        if (accountId == null) {
            logger.error("Account not found");
            throw new DepositNotFoundException("Account not found");
        }
            return depositRepository.findByPayeeId(accountId);
    }
    //Get deposit by id
    public Optional<Deposit>getDeposit(Long id){
        logger.info("Attempting to retrieved deposit by Id");
            Optional<Deposit> deposit = depositRepository.findById(id);
            // You may want to add more validation logic here
            if (deposit.isEmpty()) {
                logger.error("Error fetching deposit with id: " + id);
                throw new DepositNotFoundException("Error fetching deposit with id: " + id);
            }
            return deposit;


    }//

    //Delete
    public void deleteDeposit(Long id){
            logger.info("Attempting to delete Deposit by Id");

            if (!depositRepository.existsById(id)) {
                logger.error("This id does not exist in deposits");
                throw new DepositNotFoundException("This id does not exist in deposits");
        }

        logger.info("Successfully deleted Deposit by Id");
        depositRepository.deleteById(id);

    }
    //edit a deposit
    public Deposit editDeposit(Long depositId, Deposit deposit) {
        logger.info("Attempting to update Deposit");
        Optional<Deposit> tempDeposit = getDeposit(depositId);

        if (tempDeposit.isEmpty()) {
            logger.error("Deposit ID does not exist");
            throw new DepositNotFoundException("Deposit ID does not exist");
        }

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
