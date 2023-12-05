package com.thecoalition.bankingApi.repository;

import com.thecoalition.bankingApi.model.AccountActivity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountActivityRepository extends CrudRepository<AccountActivity, Long> {
    @Query("SELECT a FROM AccountActivity a")
    Iterable<AccountActivity> findAll();
}
