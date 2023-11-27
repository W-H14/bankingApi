package com.thecoalition.bankingApi.repository;

import com.thecoalition.bankingApi.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository  extends CrudRepository<Account, Long> {
    Iterable<Account> findByCustomerId(Long customerId);
}