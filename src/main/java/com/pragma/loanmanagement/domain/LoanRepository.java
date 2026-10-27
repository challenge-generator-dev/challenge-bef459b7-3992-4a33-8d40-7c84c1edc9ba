package com.pragma.loanmanagement.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoanRepository {
    Loan save(Loan loan);
    Optional<Loan> findById(UUID id);
    List<Loan> findByCustomerId(UUID customerId);
    List<Loan> findByStatus(Loan.LoanStatus status);
    List<Loan> findAll();
    void deleteById(UUID id);
    List<Loan> findOverdueLoans(LocalDate currentDate);
}