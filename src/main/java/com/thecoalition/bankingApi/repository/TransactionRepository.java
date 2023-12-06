package com.thecoalition.bankingApi.repository;

import com.thecoalition.bankingApi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long > {

}