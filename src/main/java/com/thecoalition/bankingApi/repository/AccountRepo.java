package com.thecoalition.bankingApi.repository;

import com.thecoalition.bankingApi.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepo  extends CrudRepository<Account, Long> {
}
