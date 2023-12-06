package com.thecoalition.bankingApi.repository;

import com.thecoalition.bankingApi.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
