package com.thecoalition.bankingApi.response;

import com.thecoalition.bankingApi.dto.Body;
import com.thecoalition.bankingApi.model.AccountActivity;
import com.thecoalition.bankingApi.service.AccountActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountActivityResponse {

    @Autowired
    private final AccountActivityService accountActivityService;


    public AccountActivityResponse(AccountActivityService accountActivityService) {
        this.accountActivityService = accountActivityService;
    }

    public ResponseEntity<?> getAllActivities() {
        Body body = new Body();
        //body.setData(accountActivityService.getAllActivities());
        body.setCode(HttpStatus.OK.value());
        body.setMessage("Success");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
