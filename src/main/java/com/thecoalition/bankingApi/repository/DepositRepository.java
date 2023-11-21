package com.thecoalition.bankingApi.repository;
import com.thecoalition.bankingApi.model.Deposit;
import org.springframework.data.repository.CrudRepository;
public interface DepositRepository extends CrudRepository<Deposit, Long> {

    Iterable<Deposit> findByAccount(Long accountId);
}
