package com.thecoalition.bankingApi.service;

import com.thecoalition.bankingApi.handler.exceptions.ResourceNotFoundException;
import com.thecoalition.bankingApi.model.Account;
import com.thecoalition.bankingApi.model.Activity;
import com.thecoalition.bankingApi.model.Deposit;
import com.thecoalition.bankingApi.model.Withdrawal;
import com.thecoalition.bankingApi.repository.ActivityRepository;
import com.thecoalition.bankingApi.repository.AccountRepository;
import com.thecoalition.bankingApi.repository.DepositRepository;
import com.thecoalition.bankingApi.repository.WithdrawalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private DepositService depositService;
    @Autowired
    private WithdrawalService withdrawalService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private WithdrawalRepository withdrawalRepository;


    private final Logger logger = LoggerFactory.getLogger(ActivityService.class);


//    public Activity createAccountActivity(Long accountId, Double amount, boolean isDeposit, Deposit deposit, Withdrawal withdrawal) {
//        try {
//            Optional<Account> accountOptional = accountRepository.findById(accountId);
//            if (accountOptional.isPresent()) {
//                Account account = accountOptional.get();
//
//                Activity activity = new Activity();
//                activity.setActivityDate(new Date());
//                activity.setAmount(amount);
//
//                //activity.setAccount(account);
//
//                if (isDeposit) {
//                    Deposit newDeposit = createDeposit(account, deposit);
//                    activity.setDeposit(newDeposit);
//                } else {
//                    Withdrawal newWithdrawal = createWithdrawal(account, withdrawal);
//                    activity.setWithdrawal(newWithdrawal);
//                }
//                logger.info("Successfully created Account activity");
//                return activityRepository.save(activity);
//            } else {
//                logger.error("Error creating Account Activity account not found");
//                throw new ResourceNotFoundException("Error creating AccountActivity: Account not found");
//            }
//
//        } catch (Exception e) {
//            logger.error("Error creating AccountActivity", e);
//            throw new RuntimeException("Error creating AccountActivity");
//        }
//    }

//    public Iterable<AccountActivity> getAllActivities() {
//        return accountActivityRepository.findAll();
//    }
private Deposit createDeposit(Account account, Deposit deposit) {
    Deposit deposit1 = new Deposit();
    deposit1.setAccount(deposit.getAccount());
    deposit1.setAmount(deposit.getAmount());
    deposit1.setType(deposit.getType());
    deposit1.setTransaction_date(deposit.getTransaction_date());
    deposit1.setStatus(deposit.getStatus());
    deposit1.setMedium(deposit.getMedium());
    deposit1.setDescription(deposit.getDescription());

    return depositService.createDeposit(account.getAccountId(), deposit1);
}

    private Withdrawal createWithdrawal(Account account, Withdrawal withdrawal) {
        Withdrawal withdrawal1 = new Withdrawal();
        withdrawal1.setAccount(account);
        withdrawal1.setAmount(withdrawal.getAmount());
        withdrawal1.setType(withdrawal.getType());
        withdrawal1.setTransaction_date(withdrawal.getTransaction_date());
        withdrawal1.setStatus(withdrawal.getStatus());
        withdrawal1.setMedium(withdrawal.getMedium());
        withdrawal1.setDescription(withdrawal.getDescription());

        return withdrawalService.createWithdrawal(account.getAccountId(), withdrawal1);
    }
    public Iterable<Activity> getAllActivities() {
        return activityRepository.findAll();
    }
}
