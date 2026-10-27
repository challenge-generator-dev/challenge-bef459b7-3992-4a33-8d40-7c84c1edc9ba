package com.pragma.loanmanagement.infrastructure.exception;

import java.util.UUID;

public class LoanNotFoundException extends RuntimeException {
    private final UUID loanId;
    private final String errorCode;
    private final String category;
    private final long timestamp;

    public LoanNotFoundException(UUID loanId) {
        super("Préstamo no encontrado con ID: " + loanId);
        if (loanId == null) {
            throw new IllegalArgumentException("El ID del préstamo no puede ser null");
        }
        this.loanId = loanId;
        this.errorCode = "LOAN_NOT_FOUND";
        this.category = "RESOURCE_NOT_FOUND";
        this.timestamp = System.currentTimeMillis();
    }

    public LoanNotFoundException(UUID loanId, String message) {
        super(message);
        if (loanId == null) {
            throw new IllegalArgumentException("El ID del préstamo no puede ser null");
        }
        this.loanId = loanId;
        this.errorCode = "LOAN_NOT_FOUND";
        this.category = "RESOURCE_NOT_FOUND";
        this.timestamp = System.currentTimeMillis();
    }

    public UUID getLoanId() {
        return loanId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getCategory() {
        return category;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getDetailedMessage() {
        return String.format("[%s] Préstamo no encontrado: ID=%s, Categoría=%s, Código=%s",
                timestamp, loanId, category, errorCode);
    }

    public boolean hasLoanId() {
        return this.loanId != null;
    }
}