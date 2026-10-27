package com.pragma.loanmanagement.infrastructure;


import com.pragma.loanmanagement.domain.LoanStatus;
import com.pragma.loanmanagement.domain.Loan;
import com.pragma.loanmanagement.domain.LoanRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public interface LoanJpaRepository extends JpaRepository<LoanEntity, UUID>, LoanRepository {

    @Override
    default Loan save(Loan loan) {
        LoanEntity entity = LoanEntity.fromDomain(loan);
        LoanEntity savedEntity = save(entity);
        return savedEntity.toDomain();
    }

    @Override
    default Optional<Loan> findById(UUID id) {
        return findById(id).map(LoanEntity::toDomain);
    }

    @Override
    default List<Loan> findByCustomerId(UUID customerId) {
        return findByCustomerId(customerId).stream()
                .map(LoanEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    default List<Loan> findByStatus(Loan.LoanStatus status) {
        return findByStatus(status).stream()
                .map(LoanEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    default List<Loan> findAll() {
        return findAll().stream()
                .map(LoanEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    default void deleteById(UUID id) {
        deleteById(id);
    }

    @Override
    default List<Loan> findOverdueLoans(LocalDate currentDate) {
        return findByEndDateBeforeAndStatusNot(currentDate, Loan.LoanStatus.PAID).stream()
                .map(LoanEntity::toDomain)
                .collect(Collectors.toList());
    }

    List<LoanEntity> findByCustomerId(UUID customerId);
    List<LoanEntity> findByStatus(Loan.LoanStatus status);
    List<LoanEntity> findByEndDateBeforeAndStatusNot(LocalDate endDate, Loan.LoanStatus status);
}