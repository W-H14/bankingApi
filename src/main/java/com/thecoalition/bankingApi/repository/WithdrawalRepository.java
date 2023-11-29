package com.thecoalition.bankingApi.repository;

import com.thecoalition.bankingApi.model.Deposit;
import com.thecoalition.bankingApi.model.Withdrawal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.Set;

@Repository

public interface WithdrawalRepository extends CrudRepository<Withdrawal, Long> {

    @Query(value = "SELECT w.* FROM Withdrawal w JOIN account a ON a.account_id WHERE a.account_id = ?1", nativeQuery = true)
    Set<Withdrawal> findByPayerId( Long accountId);
}
