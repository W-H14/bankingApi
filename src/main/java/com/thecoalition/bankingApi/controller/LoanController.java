package com.thecoalition.bankingApi.controller;


import com.thecoalition.bankingApi.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/lookup/{customerId}")
    public ResponseEntity<String> lookForLoan(@PathVariable Long customerId) {
        String response = loanService.lookForLoan(customerId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/apply/{customerId}/{accountId}/{loanAmount}")
    public ResponseEntity<String> applyForLoan(@PathVariable Long customerId, @PathVariable Long accountId, @PathVariable double loanAmount) {
        String response = loanService.applyForLoan(customerId, accountId, loanAmount);
        return ResponseEntity.ok(response);
    }
}