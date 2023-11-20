package com.thecoalition.bankingApi.repository;

import com.thecoalition.bankingApi.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRespository extends CrudRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c JOIN c.address a WHERE a.id = :addressId")
    Customer findByAddressId(@Param("addressId") Long addressId);

    @Query(value = "SELECT c.* FROM customer c JOIN account a ON c.customer_id = a.customer_id WHERE a.account_id = :accountId", nativeQuery = true)
    Iterable<Customer> findAccountsByAccountId(@Param("accountId") Long accountId);
}
