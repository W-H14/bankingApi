package com.thecoalition.bankingApi.repository;

import com.thecoalition.bankingApi.model.Withdrawal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface WithdrawalRepository extends CrudRepository<Withdrawal, Long> {

    Iterable<Withdrawal> findAllById(Long accountId);
}
