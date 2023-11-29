package com.thecoalition.bankingApi.controller;

import com.thecoalition.bankingApi.handler.exceptions.DepositNotFoundException;
import com.thecoalition.bankingApi.model.Account;
import com.thecoalition.bankingApi.model.Deposit;
import com.thecoalition.bankingApi.response.DepositResponse;
import com.thecoalition.bankingApi.service.AccountService;
import com.thecoalition.bankingApi.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class DepositController {
    @Autowired
    private DepositService depositService;

    @Autowired
    private DepositResponse depositResponse;

    @Autowired
    public DepositController(DepositResponse depositResponse){this.depositResponse = depositResponse;}

    /**
     * gets all deposit for an account
     * @param accountId
     * @return
     */
    @GetMapping("/accounts/{accountId}/deposits")
    public ResponseEntity<?> getAll(@PathVariable Long accountId) {
        return depositResponse.getAllDeposit(accountId);
    }


    /**
     * gets deposit by id
     * @param depositId
     * @return
     */
    @GetMapping("/deposits/{depositId}")
    public ResponseEntity<?> getById(@PathVariable Long depositId) throws DepositNotFoundException {
        return depositResponse.getDeposit(depositId);
    }

    /**
     * creates deposit
     * @param accountId
     * @param deposit
     * @return
     */
    @PostMapping("/accounts/{accountId}/deposits")
    public ResponseEntity<?> createDeposit(@PathVariable Long accountId, @RequestBody Deposit deposit) {
        return depositResponse.createDeposit(accountId, deposit);

    }

    /**
     * updates deposit
     * @param depositId
     * @param deposit
     * @return
     */
    @PutMapping("/deposits/{depositId}")
    public ResponseEntity<?> updateDeposit(@PathVariable Long depositId, @RequestBody Deposit deposit){
        return depositResponse.editDeposit(depositId,deposit);
    }


    /**
     * deletes deposit
     * @param depositId
     * @return
     */
    @DeleteMapping("/deposits/{depositId}")
    public ResponseEntity<?> deleteDeposit(@PathVariable Long depositId) throws DepositNotFoundException{
        return depositResponse.deleteDeposit(depositId);
    }
}
