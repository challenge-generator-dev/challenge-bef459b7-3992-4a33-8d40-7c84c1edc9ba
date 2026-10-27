package com.pragma.loanmanagement.application;


import com.pragma.loanmanagement.domain.LoanStatus;
import com.pragma.loanmanagement.domain.Loan;
import com.pragma.loanmanagement.domain.LoanRepository;
import com.pragma.loanmanagement.infrastructure.exception.LoanNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    private Loan sampleLoan;
    private UUID sampleCustomerId;
    private UUID sampleLoanId;

    @BeforeEach
    void setUp() {
        sampleCustomerId = UUID.randomUUID();
        sampleLoanId = UUID.randomUUID();
        sampleLoan = new Loan();
        sampleLoan.setId(sampleLoanId);
        sampleLoan.setCustomerId(sampleCustomerId);
        sampleLoan.setAmount(new BigDecimal("10000.00"));
        sampleLoan.setInterestRate(new BigDecimal("0.15"));
        sampleLoan.setTermMonths(12);
        sampleLoan.setStartDate(LocalDate.now());
        sampleLoan.setEndDate(LocalDate.now().plusMonths(12));
        sampleLoan.setStatus(Loan.LoanStatus.ACTIVE);
    }

    @Test
    void createLoan_Success_ReturnsCreatedLoan() {
        when(loanRepository.save(any(Loan.class))).thenReturn(sampleLoan);

        Loan result = loanService.createLoan(sampleLoan);

        assertNotNull(result);
        assertEquals(sampleLoanId, result.getId());
        assertEquals(sampleCustomerId, result.getCustomerId());
        assertEquals(Loan.LoanStatus.ACTIVE, result.getStatus());
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    void getLoanById_ExistingLoan_ReturnsLoan() {
        when(loanRepository.findById(sampleLoanId)).thenReturn(Optional.of(sampleLoan));

        Loan result = loanService.getLoanById(sampleLoanId);

        assertNotNull(result);
        assertEquals(sampleLoanId, result.getId());
        verify(loanRepository, times(1)).findById(sampleLoanId);
    }

    @Test
    void getLoanById_NonExistingLoan_ThrowsException() {
        UUID nonExistingId = UUID.randomUUID();
        when(loanRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(LoanNotFoundException.class, () -> loanService.getLoanById(nonExistingId));
        verify(loanRepository, times(1)).findById(nonExistingId);
    }

    @Test
    void getLoansByCustomerId_ExistingCustomer_ReturnsLoanList() {
        List<Loan> loans = new ArrayList<>();
        loans.add(sampleLoan);
        when(loanRepository.findByCustomerId(sampleCustomerId)).thenReturn(loans);

        List<Loan> result = loanService.getLoansByCustomerId(sampleCustomerId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(sampleCustomerId, result.get(0).getCustomerId());
        verify(loanRepository, times(1)).findByCustomerId(sampleCustomerId);
    }

    @Test
    void getLoansByCustomerId_NoLoans_ReturnsEmptyList() {
        when(loanRepository.findByCustomerId(sampleCustomerId)).thenReturn(new ArrayList<>());

        List<Loan> result = loanService.getLoansByCustomerId(sampleCustomerId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(loanRepository, times(1)).findByCustomerId(sampleCustomerId);
    }

    @Test
    void getLoansByStatus_ActiveStatus_ReturnsLoanList() {
        List<Loan> loans = new ArrayList<>();
        loans.add(sampleLoan);
        when(loanRepository.findByStatus(Loan.LoanStatus.ACTIVE)).thenReturn(loans);

        List<Loan> result = loanService.getLoansByStatus(Loan.LoanStatus.ACTIVE);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Loan.LoanStatus.ACTIVE, result.get(0).getStatus());
        verify(loanRepository, times(1)).findByStatus(Loan.LoanStatus.ACTIVE);
    }

    @Test
    void updateLoanStatus_ExistingLoan_UpdatesStatus() {
        when(loanRepository.findById(sampleLoanId)).thenReturn(Optional.of(sampleLoan));
        when(loanRepository.save(any(Loan.class))).thenReturn(sampleLoan);

        Loan result = loanService.updateLoanStatus(sampleLoanId, Loan.LoanStatus.PAID);

        assertNotNull(result);
        verify(loanRepository, times(1)).findById(sampleLoanId);
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    void deleteLoan_ExistingLoan_DeletesSuccessfully() {
        doNothing().when(loanRepository).deleteById(sampleLoanId);

        loanService.deleteLoan(sampleLoanId);

        verify(loanRepository, times(1)).deleteById(sampleLoanId);
    }

    @Test
    void getAllLoans_ReturnsAllLoans() {
        List<Loan> loans = new ArrayList<>();
        loans.add(sampleLoan);
        when(loanRepository.findAll()).thenReturn(loans);

        List<Loan> result = loanService.getAllLoans();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(loanRepository, times(1)).findAll();
    }

    @Test
    void calculateTotalPayment_ReturnsCorrectAmount() {
        BigDecimal totalPayment = sampleLoan.calculateTotalPayment();

        assertNotNull(totalPayment);
        assertTrue(totalPayment.compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void isOverdue_OverdueLoan_ReturnsTrue() {
        sampleLoan.setEndDate(LocalDate.now().minusDays(1));

        boolean isOverdue = sampleLoan.isOverdue();

        assertTrue(isOverdue);
    }

    @Test
    void isOverdue_ActiveLoan_ReturnsFalse() {
        sampleLoan.setEndDate(LocalDate.now().plusDays(30));

        boolean isOverdue = sampleLoan.isOverdue();

        assertFalse(isOverdue);
    }
}