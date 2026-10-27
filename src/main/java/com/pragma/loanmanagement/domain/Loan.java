package com.pragma.loanmanagement.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    private UUID id;

    @NotNull(message = "El cliente es obligatorio")
    private UUID customerId;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor que cero")
    private BigDecimal amount;

    @NotNull(message = "La tasa de interés es obligatoria")
    @DecimalMin(value = "0.01", message = "La tasa de interés debe ser mayor que cero")
    private BigDecimal interestRate;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate startDate;

    @NotNull(message = "La fecha de finalización es obligatoria")
    @Future(message = "La fecha de finalización debe ser futura")
    private LocalDate endDate;

    @NotNull(message = "El estado es obligatorio")
    private LoanStatus status;

    @Positive(message = "El plazo debe ser positivo")
    private int termMonths;

    public enum LoanStatus {
        PENDING, APPROVED, REJECTED, ACTIVE, PAID, DEFAULTED
    }

    public BigDecimal calculateTotalPayment() {
        BigDecimal monthlyInterest = interestRate.divide(BigDecimal.valueOf(12), 10, BigDecimal.ROUND_HALF_UP);
        BigDecimal monthlyPayment = amount.multiply(monthlyInterest)
                .divide(BigDecimal.ONE.subtract(BigDecimal.ONE.add(monthlyInterest).pow(-termMonths)), 10, BigDecimal.ROUND_HALF_UP);
        return monthlyPayment.multiply(BigDecimal.valueOf(termMonths));
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(endDate) && !status.equals(LoanStatus.PAID);
    }

    public BigDecimal calculateRemainingBalance() {
        if (status.equals(LoanStatus.PAID)) {
            return BigDecimal.ZERO;
        }
        BigDecimal totalPaid = calculateTotalPaid();
        return calculateTotalPayment().subtract(totalPaid);
    }

    private BigDecimal calculateTotalPaid() {
        // En una implementación real, esto vendría de un registro de pagos
        // Para este ejemplo, simulamos un pago parcial
        BigDecimal totalPayment = calculateTotalPayment();
        BigDecimal paidPercentage = BigDecimal.valueOf(0.3); // 30% pagado
        return totalPayment.multiply(paidPercentage);
    }
}