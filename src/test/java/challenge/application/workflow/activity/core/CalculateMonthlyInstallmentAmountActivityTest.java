package challenge.application.workflow.activity.core;

import challenge.application.service.LoanCalculationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculateMonthlyInstallmentAmountActivityTest {

    @InjectMocks
    private CalculateMonthlyInstallmentAmountActivity unit;

    @Mock
    private LoanCalculationService loanCalculationService;

    @Test
    @DisplayName("Deve retornar cálculo de parcela mensal")
    void executeTestCase() {
        var paymentTermMonths = 10;
        var loanAmount = 1000.00;
        var monthlyInterestRate = 0.03;
        var expectedResult = 1290.89;

        when(loanCalculationService.calculateInstallmentAmount(paymentTermMonths, loanAmount, monthlyInterestRate))
                .thenReturn(expectedResult);

        var output = unit.execute(paymentTermMonths, loanAmount, monthlyInterestRate);

        assertEquals(expectedResult, output);
    }
}