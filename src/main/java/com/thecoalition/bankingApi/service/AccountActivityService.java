package com.thecoalition.bankingApi.service;

import com.thecoalition.bankingApi.model.AccountActivity;
import com.thecoalition.bankingApi.repository.AccountActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountActivityService {

    private final AccountActivityRepository accountActivityRepository;

    @Autowired
    public AccountActivityService(AccountActivityRepository accountActivityRepository) {
        this.accountActivityRepository = accountActivityRepository;
    }

    public Iterable<AccountActivity> getAllActivities() {
        return accountActivityRepository.findAll();
    }
}
