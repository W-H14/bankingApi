package com.thecoalition.bankingApi.service;

import com.thecoalition.bankingApi.handler.exceptions.DepositNotFoundException;
import com.thecoalition.bankingApi.handler.exceptions.ResourceNotFoundException;
import com.thecoalition.bankingApi.model.Account;
import com.thecoalition.bankingApi.model.Deposit;
import com.thecoalition.bankingApi.model.Withdrawal;
import com.thecoalition.bankingApi.repository.AccountRepository;
import com.thecoalition.bankingApi.repository.DepositRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class DepositService {
    @Autowired
    private DepositRepository depositRepository;

    @Autowired
   private AccountRepository accountRepository;
   private final Logger logger = LoggerFactory.getLogger(DepositService.class);

    //Create deposit
    public Deposit createDeposit(Long payee_id, Deposit deposit) throws DepositNotFoundException{
        logger.info("Deposit created ");
        Optional<Account> accountOptional = accountRepository.findById(payee_id);
        if(accountOptional.isEmpty()){
            logger.error("Error creating deposit: Account not found");
            throw new DepositNotFoundException("Error creating deposit: Account not found");
        }
         var account = accountOptional.get();
        deposit.setAccount(account);
        return depositRepository.save(deposit);
    }
    //get all from an account
    public Set<Deposit> getAllDeposits(Long accountId)  {
//        logger.info("Successfully retrieved all deposits");
//        if (accountId == null) {
//            logger.error("Account not found");
//            throw new DepositNotFoundException("Account not found");
//        }
//            return depositRepository.findByPayeeId(accountId);
        Iterable<Deposit> deposits = depositRepository.findAll();
        if (!deposits.iterator().hasNext()) {
            logger.error("Account not found");
            throw new ResourceNotFoundException("Account not found");
        }
        logger.info("Successfully gotten all deposits for account");
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
    public void deleteDeposit(Long depositId) throws DepositNotFoundException{
            logger.info("Attempting to delete Deposit by Id");

            if (!depositRepository.existsById(depositId)) {
                logger.error("This id does not exist in deposits");
                throw new DepositNotFoundException("This id does not exist in deposits");
            }else {

                logger.info("Successfully deleted Deposit by Id");
                depositRepository.deleteById(depositId);
            }
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
        d.setAmount(deposit.getAmount());
        d.setMedium(deposit.getMedium());
        d.setTransaction_date(deposit.getTransaction_date());
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
