package com.thecoalition.bankingApi.response;

import com.thecoalition.bankingApi.dto.Body;
import com.thecoalition.bankingApi.handler.exceptions.DepositNotFoundException;
import com.thecoalition.bankingApi.model.Account;
import com.thecoalition.bankingApi.model.Customer;
import com.thecoalition.bankingApi.model.Deposit;
import com.thecoalition.bankingApi.service.AccountService;
import com.thecoalition.bankingApi.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Component
public class DepositResponse {
    @Autowired
    private DepositService depositService;
    @Autowired
    private AccountService accountService;

    public ResponseEntity<?> createDeposit(Long accountId, Deposit deposit) {
        accountService.verifyCostumer(accountId);
        depositService.createDeposit(accountId, deposit);
        Body body = new Body();
        body.setData(deposit);
        body.setCode(HttpStatus.CREATED.value());
        body.setMessage("Created deposit and added it to the account");
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }
    public ResponseEntity<?> getAllDeposit(Long accountId) {
        Body body = new Body();
        body.setData(depositService.getAllDeposits(accountId));
        body.setCode(HttpStatus.OK.value());
        body.setMessage("Success");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

        public ResponseEntity<?> editDeposit(Long depositId, Deposit deposit){
            depositService.editDeposit(depositId, deposit);
            Body body = new Body();
            body.setData(deposit);
            body.setCode(HttpStatus.OK.value());
            body.setMessage("Accepted deposit modification");
            return new ResponseEntity<>(body, HttpStatus.OK);
        }
    public ResponseEntity<?> deleteDeposit(Long depositId) {
        depositService.deleteDeposit(depositId);
        Body body = new Body();
        body.setCode(HttpStatus.NO_CONTENT.value());
        body.setMessage("Deposit deleted successfully");
        return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> getDeposit(Long depositId) throws DepositNotFoundException {
        Body body = new Body();
        body.setData(depositService.getDeposit(depositId));
        body.setCode(HttpStatus.OK.value());
        body.setMessage("Success");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
    }
