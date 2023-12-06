package com.thecoalition.bankingApi.controller;

import com.thecoalition.bankingApi.response.AccountActivityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account-activity")
public class AccountActivityController {

    private final AccountActivityResponse accountActivityResponse;

    @Autowired
    public AccountActivityController(AccountActivityResponse accountActivityResponse) {
        this.accountActivityResponse = accountActivityResponse;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllActivities() {
        return accountActivityResponse.getAllActivities();
    }


}
