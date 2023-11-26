package com.thecoalition.bankingApi.repository;

import com.thecoalition.bankingApi.model.AccountActivity;
import org.springframework.data.repository.CrudRepository;

public interface AccountActivityRepository extends CrudRepository<AccountActivity, Long> {

    Iterable<AccountActivity> findAllByAccountId(Long accountId);
}
