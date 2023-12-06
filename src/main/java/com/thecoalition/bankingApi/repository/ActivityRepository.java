package com.thecoalition.bankingApi.repository;

import com.thecoalition.bankingApi.model.Activity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Long> {
    @Query("SELECT a FROM Activity a")
    Iterable<Activity> findAll();

    Activity findByWithdrawal_Id(Long withdrawalId);
}
