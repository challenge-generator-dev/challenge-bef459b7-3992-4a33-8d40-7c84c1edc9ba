package com.pragma.loanmanagement.infrastructure;


import com.pragma.loanmanagement.domain.LoanStatus;
import com.pragma.loanmanagement.application.LoanService;
import com.pragma.loanmanagement.domain.Loan;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {

    private static final Logger logger = LoggerFactory.getLogger(LoanController.class);

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@Valid @RequestBody CreateLoanRequest request) {
        logger.info("Recibida solicitud para crear préstamo - Cliente: {}, Monto: {}", 
                   request.customerId(), request.amount());

        try {
            Loan loan = loanService.createLoan(
                request.customerId(),
                request.amount(),
                request.interestRate(),
                request.termMonths(),
                request.startDate()
            );

            logger.info("Préstamo creado exitosamente - ID: {}", loan.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(loan);
        } catch (IllegalArgumentException e) {
            logger.error("Error al crear préstamo: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<Loan> getLoan(@PathVariable UUID loanId) {
        logger.info("Recibida solicitud para obtener préstamo: {}", loanId);

        try {
            Loan loan = loanService.getLoanById(loanId);
            return ResponseEntity.ok(loan);
        } catch (Exception e) {
            logger.error("Error al obtener préstamo {}: {}", loanId, e.getMessage());
            throw e;
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Loan>> getLoansByCustomer(@PathVariable UUID customerId) {
        logger.info("Recibida solicitud para obtener préstamos del cliente: {}", customerId);

        List<Loan> loans = loanService.getLoansByCustomer(customerId);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Loan>> getLoansByStatus(@PathVariable Loan.LoanStatus status) {
        logger.info("Recibida solicitud para obtener préstamos con estado: {}", status);

        List<Loan> loans = loanService.getLoansByStatus(status);
        return ResponseEntity.ok(loans);
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        logger.info("Recibida solicitud para obtener todos los préstamos");

        List<Loan> loans = loanService.getAllLoans();
        return ResponseEntity.ok(loans);
    }

    @PutMapping("/{loanId}/status")
    public ResponseEntity<Loan> updateLoanStatus(
            @PathVariable UUID loanId,
            @RequestBody UpdateStatusRequest request) {
        logger.info("Recibida solicitud para actualizar estado del préstamo {} a {}", 
                   loanId, request.status());

        try {
            Loan loan = loanService.updateLoanStatus(loanId, request.status());
            return ResponseEntity.ok(loan);
        } catch (Exception e) {
            logger.error("Error al actualizar estado del préstamo {}: {}", loanId, e.getMessage());
            throw e;
        }
    }

    @PutMapping("/{loanId}")
    public ResponseEntity<Loan> updateLoan(
            @PathVariable UUID loanId,
            @RequestBody UpdateLoanRequest request) {
        logger.info("Recibida solicitud para actualizar préstamo: {}", loanId);

        try {
            Loan loan = loanService.updateLoan(
                loanId,
                request.amount(),
                request.interestRate(),
                request.termMonths()
            );
            return ResponseEntity.ok(loan);
        } catch (Exception e) {
            logger.error("Error al actualizar préstamo {}: {}", loanId, e.getMessage());
            throw e;
        }
    }

    @DeleteMapping("/{loanId}")
    public ResponseEntity<Void> deleteLoan(@PathVariable UUID loanId) {
        logger.info("Recibida solicitud para eliminar préstamo: {}", loanId);

        try {
            loanService.deleteLoan(loanId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error al eliminar préstamo {}: {}", loanId, e.getMessage());
            throw e;
        }
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<Loan>> getOverdueLoans() {
        logger.info("Recibida solicitud para obtener préstamos vencidos");

        List<Loan> loans = loanService.getOverdueLoans();
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{loanId}/total-payment")
    public ResponseEntity<BigDecimal> calculateTotalPayment(@PathVariable UUID loanId) {
        logger.info("Recibida solicitud para calcular pago total del préstamo: {}", loanId);

        BigDecimal totalPayment = loanService.calculateTotalPayment(loanId);
        return ResponseEntity.ok(totalPayment);
    }

    @GetMapping("/{loanId}/remaining-balance")
    public ResponseEntity<BigDecimal> calculateRemainingBalance(@PathVariable UUID loanId) {
        logger.info("Recibida solicitud para calcular saldo pendiente del préstamo: {}", loanId);

        BigDecimal remainingBalance = loanService.calculateRemainingBalance(loanId);
        return ResponseEntity.ok(remainingBalance);
    }

    public record CreateLoanRequest(
        @NotNull UUID customerId,
        @NotNull BigDecimal amount,
        @NotNull BigDecimal interestRate,
        @NotNull Integer termMonths,
        @NotNull LocalDate startDate
    ) {}

    public record UpdateLoanRequest(
        BigDecimal amount,
        BigDecimal interestRate,
        Integer termMonths
    ) {}

    public record UpdateStatusRequest(
        @NotNull Loan.LoanStatus status
    ) {}
}