package com.pragma.loanmanagement.infrastructure;


import com.pragma.loanmanagement.domain.LoanStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.loanmanagement.application.LoanService;
import com.pragma.loanmanagement.domain.Loan;
import com.pragma.loanmanagement.infrastructure.exception.LoanNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoanController.class)
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
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
    void createLoan_ValidRequest_ReturnsCreatedStatus() throws Exception {
        when(loanService.createLoan(any(Loan.class))).thenReturn(sampleLoan);

        mockMvc.perform(post("/api/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleLoan)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(sampleLoanId.toString()))
                .andExpect(jsonPath("$.customerId").value(sampleCustomerId.toString()))
                .andExpect(jsonPath("$.status").value("ACTIVE"));

        verify(loanService, times(1)).createLoan(any(Loan.class));
    }

    @Test
    void getLoanById_ExistingLoan_ReturnsOkStatus() throws Exception {
        when(loanService.getLoanById(sampleLoanId)).thenReturn(sampleLoan);

        mockMvc.perform(get("/api/loans/{id}", sampleLoanId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(sampleLoanId.toString()))
                .andExpect(jsonPath("$.customerId").value(sampleCustomerId.toString()));

        verify(loanService, times(1)).getLoanById(sampleLoanId);
    }

    @Test
    void getLoanById_NonExistingLoan_ReturnsNotFoundStatus() throws Exception {
        UUID nonExistingId = UUID.randomUUID();
        when(loanService.getLoanById(nonExistingId)).thenThrow(new LoanNotFoundException("Loan not found"));

        mockMvc.perform(get("/api/loans/{id}", nonExistingId))
                .andExpect(status().isNotFound());

        verify(loanService, times(1)).getLoanById(nonExistingId);
    }

    @Test
    void getLoansByCustomerId_ExistingCustomer_ReturnsOkStatus() throws Exception {
        List<Loan> loans = new ArrayList<>();
        loans.add(sampleLoan);
        when(loanService.getLoansByCustomerId(sampleCustomerId)).thenReturn(loans);

        mockMvc.perform(get("/api/loans/customer/{customerId}", sampleCustomerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].customerId").value(sampleCustomerId.toString()));

        verify(loanService, times(1)).getLoansByCustomerId(sampleCustomerId);
    }

    @Test
    void getLoansByCustomerId_NoLoans_ReturnsEmptyList() throws Exception {
        when(loanService.getLoansByCustomerId(sampleCustomerId)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/loans/customer/{customerId}", sampleCustomerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

        verify(loanService, times(1)).getLoansByCustomerId(sampleCustomerId);
    }

    @Test
    void getLoansByStatus_ValidStatus_ReturnsOkStatus() throws Exception {
        List<Loan> loans = new ArrayList<>();
        loans.add(sampleLoan);
        when(loanService.getLoansByStatus(Loan.LoanStatus.ACTIVE)).thenReturn(loans);

        mockMvc.perform(get("/api/loans/status/{status}", "ACTIVE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].status").value("ACTIVE"));

        verify(loanService, times(1)).getLoansByStatus(Loan.LoanStatus.ACTIVE);
    }

    @Test
    void getAllLoans_ReturnsOkStatus() throws Exception {
        List<Loan> loans = new ArrayList<>();
        loans.add(sampleLoan);
        when(loanService.getAllLoans()).thenReturn(loans);

        mockMvc.perform(get("/api/loans"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(loanService, times(1)).getAllLoans();
    }

    @Test
    void updateLoanStatus_ValidRequest_ReturnsOkStatus() throws Exception {
        when(loanService.updateLoanStatus(sampleLoanId, Loan.LoanStatus.PAID)).thenReturn(sampleLoan);

        mockMvc.perform(put("/api/loans/{id}/status", sampleLoanId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"PAID\""))
                .andExpect(status().isOk());

        verify(loanService, times(1)).updateLoanStatus(sampleLoanId, Loan.LoanStatus.PAID);
    }

    @Test
    void deleteLoan_ExistingLoan_ReturnsNoContentStatus() throws Exception {
        doNothing().when(loanService).deleteLoan(sampleLoanId);

        mockMvc.perform(delete("/api/loans/{id}", sampleLoanId))
                .andExpect(status().isNoContent());

        verify(loanService, times(1)).deleteLoan(sampleLoanId);
    }

    @Test
    void createLoan_InvalidRequest_ReturnsBadRequestStatus() throws Exception {
        Loan invalidLoan = new Loan();
        invalidLoan.setId(sampleLoanId);

        mockMvc.perform(post("/api/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidLoan)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getLoanById_InvalidUuid_ReturnsBadRequestStatus() throws Exception {
        mockMvc.perform(get("/api/loans/{id}", "invalid-uuid"))
                .andExpect(status().isBadRequest());
    }
}