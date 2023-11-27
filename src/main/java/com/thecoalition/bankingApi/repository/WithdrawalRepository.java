package com.thecoalition.bankingApi.repository;

import com.thecoalition.bankingApi.model.Deposit;
import com.thecoalition.bankingApi.model.Withdrawal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;


import java.util.Optional;
@Repository

public interface WithdrawalRepository extends CrudRepository<Withdrawal, Long> {

    @Query(value = "SELECT d FROM Withdrawal d WHERE d.payer.id = :accountId",nativeQuery = true)
    Optional<Deposit> findByPayerId(@Param("accountId") Long accountId);
}

