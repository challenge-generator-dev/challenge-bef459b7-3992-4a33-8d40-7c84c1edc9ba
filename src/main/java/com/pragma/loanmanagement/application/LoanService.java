package com.pragma.loanmanagement.application;


import com.pragma.loanmanagement.domain.LoanStatus;
import com.pragma.loanmanagement.domain.Loan;
import com.pragma.loanmanagement.domain.LoanRepository;
import com.pragma.loanmanagement.infrastructure.exception.LoanNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoanService {

    private static final Logger logger = LoggerFactory.getLogger(LoanService.class);

    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan createLoan(UUID customerId, BigDecimal amount, BigDecimal interestRate, 
                          int termMonths, LocalDate startDate) {
        logger.info("Iniciando creación de préstamo para cliente: {}", customerId);

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            logger.error("Monto del préstamo inválido: {}", amount);
            throw new IllegalArgumentException("El monto del préstamo debe ser mayor a cero");
        }

        if (interestRate == null || interestRate.compareTo(BigDecimal.ZERO) < 0) {
            logger.error("Tasa de interés inválida: {}", interestRate);
            throw new IllegalArgumentException("La tasa de interés no puede ser negativa");
        }

        if (termMonths <= 0 || termMonths > 360) {
            logger.error("Plazo inválido: {} meses", termMonths);
            throw new IllegalArgumentException("El plazo del préstamo debe estar entre 1 y 360 meses");
        }

        if (startDate == null) {
            logger.error("Fecha de inicio no proporcionada");
            throw new IllegalArgumentException("La fecha de inicio del préstamo es obligatoria");
        }

        Loan loan = new Loan();
        loan.setId(UUID.randomUUID());
        loan.setCustomerId(customerId);
        loan.setAmount(amount);
        loan.setInterestRate(interestRate);
        loan.setTermMonths(termMonths);
        loan.setStartDate(startDate);
        loan.setEndDate(startDate.plusMonths(termMonths));
        loan.setStatus(Loan.LoanStatus.ACTIVE);

        Loan savedLoan = loanRepository.save(loan);
        logger.info("Préstamo creado exitosamente con ID: {} para cliente: {}", 
                   savedLoan.getId(), customerId);

        return savedLoan;
    }

    public Loan getLoanById(UUID loanId) {
        logger.info("Buscando préstamo con ID: {}", loanId);

        Optional<Loan> loan = loanRepository.findById(loanId);
        if (loan.isEmpty()) {
            logger.warn("Préstamo no encontrado con ID: {}", loanId);
            throw new LoanNotFoundException("Préstamo no encontrado con ID: " + loanId);
        }

        logger.info("Préstamo encontrado: {}", loan.get().getId());
        return loan.get();
    }

    public List<Loan> getLoansByCustomer(UUID customerId) {
        logger.info("Buscando préstamos para cliente: {}", customerId);

        List<Loan> loans = loanRepository.findByCustomerId(customerId);
        logger.info("Se encontraron {} préstamos para el cliente: {}", loans.size(), customerId);

        return loans;
    }

    public List<Loan> getLoansByStatus(Loan.LoanStatus status) {
        logger.info("Buscando préstamos con estado: {}", status);

        List<Loan> loans = loanRepository.findByStatus(status);
        logger.info("Se encontraron {} préstamos con estado: {}", loans.size(), status);

        return loans;
    }

    public List<Loan> getAllLoans() {
        logger.info("Obteniendo todos los préstamos del sistema");

        List<Loan> loans = loanRepository.findAll();
        logger.info("Total de préstamos en el sistema: {}", loans.size());

        return loans;
    }

    public Loan updateLoanStatus(UUID loanId, Loan.LoanStatus newStatus) {
        logger.info("Actualizando estado del préstamo {} a {}", loanId, newStatus);

        Loan loan = getLoanById(loanId);
        Loan.LoanStatus oldStatus = loan.getStatus();
        loan.setStatus(newStatus);

        Loan updatedLoan = loanRepository.save(loan);
        logger.info("Estado del préstamo {} actualizado de {} a {}", 
                   loanId, oldStatus, newStatus);

        return updatedLoan;
    }

    public Loan updateLoan(UUID loanId, BigDecimal newAmount, BigDecimal newInterestRate, 
                          Integer newTermMonths) {
        logger.info("Actualizando préstamo con ID: {}", loanId);

        Loan loan = getLoanById(loanId);

        if (newAmount != null && newAmount.compareTo(BigDecimal.ZERO) > 0) {
            loan.setAmount(newAmount);
            logger.debug("Monto actualizado a: {}", newAmount);
        }

        if (newInterestRate != null && newInterestRate.compareTo(BigDecimal.ZERO) >= 0) {
            loan.setInterestRate(newInterestRate);
            logger.debug("Tasa de interés actualizada a: {}", newInterestRate);
        }

        if (newTermMonths != null && newTermMonths > 0 && newTermMonths <= 360) {
            loan.setTermMonths(newTermMonths);
            loan.setEndDate(loan.getStartDate().plusMonths(newTermMonths));
            logger.debug("Plazo actualizado a {} meses", newTermMonths);
        }

        Loan updatedLoan = loanRepository.save(loan);
        logger.info("Préstamo {} actualizado exitosamente", loanId);

        return updatedLoan;
    }

    public void deleteLoan(UUID loanId) {
        logger.info("Eliminando préstamo con ID: {}", loanId);

        Loan loan = getLoanById(loanId);
        loanRepository.deleteById(loanId);

        logger.info("Préstamo {} eliminado exitosamente", loanId);
    }

    public List<Loan> getOverdueLoans() {
        logger.info("Buscando préstamos vencidos");

        LocalDate currentDate = LocalDate.now();
        List<Loan> overdueLoans = loanRepository.findOverdueLoans(currentDate);

        logger.info("Se encontraron {} préstamos vencidos", overdueLoans.size());
        return overdueLoans;
    }

    public BigDecimal calculateTotalPayment(UUID loanId) {
        logger.info("Calculando pago total para préstamo: {}", loanId);

        Loan loan = getLoanById(loanId);
        BigDecimal totalPayment = loan.calculateTotalPayment();

        logger.info("Pago total calculado para préstamo {}: {}", loanId, totalPayment);
        return totalPayment;
    }

    public BigDecimal calculateRemainingBalance(UUID loanId) {
        logger.info("Calculando saldo pendiente para préstamo: {}", loanId);

        Loan loan = getLoanById(loanId);
        BigDecimal remainingBalance = loan.calculateRemainingBalance();

        logger.info("Saldo pendiente para préstamo {}: {}", loanId, remainingBalance);
        return remainingBalance;
    }
}