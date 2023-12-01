package com.thecoalition.bankingApi.repository;

import com.thecoalition.bankingApi.model.Transaction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long > {
    @Query(value = "SELECT t FROM TRANSACTION t WHERE t.account_id = :payerId",nativeQuery = true)
    Optional<Transaction> findById(@Param("payerId") Long payerId);

    @Transactional
    @Modifying
    @Query("UPDATE Transaction t SET t.balance = :updatedBalance WHERE t.id = :transactionId")
    int updateBalance(@Param("transactionId") Long transactionId, @Param("updatedBalance") double updatedBalance);
}

