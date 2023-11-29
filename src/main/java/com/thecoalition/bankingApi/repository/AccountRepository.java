package com.thecoalition.bankingApi.repository;

import com.thecoalition.bankingApi.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository  extends JpaRepository<Account, Long> {
    Iterable<Account> findByCustomer_Id(Long customerId);




 //   @Query("SELECT a FROM Account a WHERE a.customer_id = :costumer_id")
   // Account getAllAccountsForCostumer(@Param("customer_id") long accountId);



}