package challenge.application.workflow.definition.core;

import challenge.application.workflow.activity.core.CalculateMonthlyInstallmentAmountActivity;
import challenge.application.workflow.activity.core.CreateLoanSimulationResponseActivity;
import challenge.application.workflow.activity.core.GetClientLoanRateActivity;
import challenge.application.workflow.input.LoanSimulationInput;
import challenge.application.workflow.output.LoanSimulationResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanSimulationWorkflowTest {

    @InjectMocks
    private LoanSimulationWorkflow unit;

    @Mock
    private GetClientLoanRateActivity getClientInterestRateActivity;

    @Mock
    private CalculateMonthlyInstallmentAmountActivity calculateMonthlyInstallmentAmountActivity;

    @Mock
    private CreateLoanSimulationResponseActivity createLoanSimulationResponseActivity;

    @Mock
    private LoanSimulationInput loanSimulationInput;

    @Mock
    private LoanSimulationResult result;

    @Test
    void executeWorkflowTest() {
        var clientMonthlyInterestRate = 0.03;
        var clientBirthDate = "10/12/1996";
        var months = 10;
        var loanAmount = 1000.00;
        var installmentAmount = 230.00;

        when(loanSimulationInput.getLoanAmount()).thenReturn(loanAmount);
        when(loanSimulationInput.getClientBirthDate()).thenReturn(clientBirthDate);
        when(loanSimulationInput.getPaymentTermMonths()).thenReturn(months);

        when(getClientInterestRateActivity.execute(clientBirthDate)).thenReturn(clientMonthlyInterestRate);
        when(calculateMonthlyInstallmentAmountActivity.execute(months, loanAmount, clientMonthlyInterestRate))
                .thenReturn(installmentAmount);
        when(createLoanSimulationResponseActivity.execute(loanSimulationInput, installmentAmount))
                .thenReturn(result);

        assertEquals(result, unit.execute(loanSimulationInput));
    }
}