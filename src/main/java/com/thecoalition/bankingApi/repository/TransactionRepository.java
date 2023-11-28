package com.thecoalition.bankingApi.repository;

import com.thecoalition.bankingApi.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface TransactionRepository extends CrudRepository<Long, Transaction> {
    @Query(value = "SELECT t FROM TRANSACTION t WHERE t.account_id = :payerId",nativeQuery = true)
    Optional<Transaction> findById(@Param("payerId") Long payerId);
}
