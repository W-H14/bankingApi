package com.thecoalition.bankingApi.repository;
import com.thecoalition.bankingApi.model.Deposit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface DepositRepository extends CrudRepository<Deposit, Long> {

    @Query(value = "SELECT d.* FROM deposit d JOIN account a ON d.account_id WHERE a.account_id = ?1", nativeQuery = true)
    Set<Deposit> findByPayeeId(Long accountId);
}
