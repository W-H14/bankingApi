package com.thecoalition.bankingApi.controller;

import com.thecoalition.bankingApi.model.Deposit;
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

    /**
     * gets all deposits for an account
     * @param accountId
     * @return
     */
    @RequestMapping(value="/accounts/{accountId}/deposits", method= RequestMethod.GET)
    public ResponseEntity<Optional<Deposit>> getAll(@PathVariable Long accountId){
        return new ResponseEntity<>(depositService.getAllDeposits(accountId), HttpStatus.OK);
    }

    /**
     * get deposit by Id
     * @param depositId
     * @return
     */
    @GetMapping("/deposits/{depositId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Deposit> getById(@PathVariable Long depositId){
        return depositService.getDeposit(depositId);
    }

    /**
     * creates deposit
     * @param accountId
     * @param deposit
     */
    @PostMapping("/accounts/{accountId}/deposits")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDeposit(@PathVariable Long accountId, @RequestBody Deposit deposit){
       depositService.createDeposit(accountId, deposit);
    }

    /**
     * edits a deposit
     * @param depositId
     * @param deposit
     * @return
     */
    @PutMapping("/deposits/{depositId}")
    public Deposit editDeposit(@PathVariable Long depositId, @RequestBody Deposit deposit){
        return depositService.editDeposit(depositId, deposit);
    }

    /**
     * deletes a deposit
     * @param depositId
     */
    @DeleteMapping("/deposit/{depositId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Use HttpStatus.CREATED for 201 status
    public void deleteDeposit(@Valid @PathVariable Long depositId) {
        depositService.deleteDeposit(depositId);
    }
}
