package challenge.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoanCalculationServiceTest {

    private static final LoanCalculationService loanCalculationService = new LoanCalculationService();

    @Test
    @DisplayName("Deve realizar cálculo de parcela mensal")
    void calculateInstallmentAmount() {
        var numberOfInstallments = 12;
        var loanAmount = 10000.0;
        var monthlyInterestRate = 0.03;
        var expectedAmount = 1004.63;

        var result = loanCalculationService.calculateInstallmentAmount(
                numberOfInstallments,
                loanAmount,
                monthlyInterestRate
        );

        assertEquals(expectedAmount, result,0.01, "O valor da parcela calculado está incorreto");
    }
}