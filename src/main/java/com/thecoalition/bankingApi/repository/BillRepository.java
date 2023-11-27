package com.thecoalition.bankingApi.repository;

import com.thecoalition.bankingApi.model.Account;
import com.thecoalition.bankingApi.model.Bill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BillRepository extends CrudRepository<Bill,Long> {
    @Query(value = "SELECT b FROM Bill b WHERE b.account_id = :accountId",nativeQuery = true)
    List<Bill> findBillsByAccountId(@Param("accountId") Long accountId);

    /*
    @Query(value = "SELECT d FROM Deposit d WHERE d.payee.id = :accountId",nativeQuery = true)
    Optional<Deposit> findByPayeeId(@Param("accountId") Long accountId);
     */
}
