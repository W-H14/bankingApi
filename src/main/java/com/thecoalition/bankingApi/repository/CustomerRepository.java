package com.thecoalition.bankingApi.repository;

import com.thecoalition.bankingApi.model.Account;
import com.thecoalition.bankingApi.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
  //  Iterable<Customer> findCustomersByAccountId(Long accountId);

//   phone notes
//    List<Customer> findByFirstName(String firstName);
//    List<Customer>findByLastName(String lastName);
//    List<Customer>findByFirstNameAndLastName(String firstName, String lastName);
//    List<Customer> findByAddress(String city);
@Query(value = "SELECT c FROM Customer c WHERE c.accountId = :accountId", nativeQuery = true)
Iterable<Customer> findCustomersByAccountId(@Param("accountId") Long accountId);
}
