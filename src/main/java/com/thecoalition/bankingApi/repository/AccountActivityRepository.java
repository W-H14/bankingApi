package com.thecoalition.bankingApi.repository;

import com.thecoalition.bankingApi.model.AccountActivity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AccountActivityRepository extends CrudRepository<AccountActivity, Long> {



    @Query(value = "SELECT * FROM account_activity WHERE account_id = :accountId", nativeQuery = true)
    List<AccountActivity> findAllByAccountId(@Param("accountId") Long accountId);
}
