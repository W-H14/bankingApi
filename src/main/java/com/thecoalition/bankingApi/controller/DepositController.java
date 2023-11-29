package com.thecoalition.bankingApi.controller;

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

    /**
     * gets all deposit for an account
     * @param payeeId
     * @return
     */
    @RequestMapping(value = "/accounts/{accountId}/deposits", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@PathVariable Long payeeId) {
        return new ResponseEntity<>(depositResponse.getAllDeposit(payeeId), HttpStatus.OK);
    }


    /**
     * gets deposit by id
     * @param depositId
     * @return
     */
    @GetMapping("/deposits/{depositId}")
    public ResponseEntity<?> getById(@PathVariable Long depositId) {
        return new ResponseEntity<>(depositResponse.getDeposit(depositId), HttpStatus.OK);
    }

    /**
     * creates deposit
     * @param accountId
     * @param deposit
     * @return
     */
    @PostMapping(value = "/accounts/{accountId}/deposits")
    public ResponseEntity<?> createDeposit(@PathVariable Long accountId, @RequestBody Deposit deposit) {
        return new ResponseEntity<> (depositResponse.createDeposit(accountId, deposit), HttpStatus.CREATED);

    }

    /**
     * updates deposit
     * @param depositId
     * @param deposit
     * @return
     */
    @PutMapping("/deposits/{depositId}")
    public ResponseEntity<?> updateDeposit(@PathVariable Long depositId, @RequestBody Deposit deposit){
        return new ResponseEntity<> (depositResponse.editDeposit(depositId,deposit), HttpStatus.OK);
    }


    /**
     * deletes deposit
     * @param depositId
     * @return
     */
    @DeleteMapping(value = "/deposit/{depositId}")
    public ResponseEntity<?> deleteDeposit(@PathVariable Long depositId) {
        return new ResponseEntity<>(depositResponse.deleteDeposit(depositId), HttpStatus.NO_CONTENT);
    }
}
