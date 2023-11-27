package com.thecoalition.bankingApi.repository;
import com.thecoalition.bankingApi.model.Deposit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DepositRepository extends CrudRepository<Deposit, Long> {

    @Query(value = "SELECT d FROM Deposit d WHERE d.payee.id = :accountId",nativeQuery = true)
    Optional<Deposit> findByPayeeId(@Param("accountId") Long accountId);
}
