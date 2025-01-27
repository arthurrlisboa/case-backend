package challenge.application.workflow.definition.entry;

import challenge.application.workflow.activity.entry.ParseLoanSimulationDataToInputActivity;
import challenge.application.workflow.activity.entry.simple.ParseLoanResultToLoanSimulationResponseActivity;
import challenge.application.workflow.activity.entry.simple.ValidateLoanSimulationDataActivity;
import challenge.application.workflow.definition.core.LoanSimulationWorkflow;
import challenge.application.workflow.input.LoanSimulationInput;
import challenge.application.workflow.output.LoanSimulationResult;
import challenge.model.LoanSimulationData;
import challenge.model.LoanSimulationResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SimpleLoanSimulationWorkflowTest {

    @InjectMocks
    private SimpleLoanSimulationWorkflow unit;

    @Mock
    private ValidateLoanSimulationDataActivity validateLoanSimulationDataActivity;

    @Mock
    private ParseLoanSimulationDataToInputActivity parseLoanSimulationDataToInputActivity;

    @Mock
    private LoanSimulationWorkflow loanSimulationWorkflow;

    @Mock
    private ParseLoanResultToLoanSimulationResponseActivity parseLoanResultToLoanSimulationResponseActivity;

    @Mock
    private LoanSimulationData loanSimulationData;

    @Mock
    private LoanSimulationInput input;

    @Mock
    private LoanSimulationResult result;

    @Mock
    private LoanSimulationResponse response;

    @Test
    @DisplayName("Deve serguir o fluxo de execução e retornar o response")
    void executeTestCase() {
        var userEmail = "user@email.com";
        when(input.getUserEmail()).thenReturn(userEmail);

        doNothing().when(validateLoanSimulationDataActivity).execute(loanSimulationData);
        when(parseLoanSimulationDataToInputActivity.execute(loanSimulationData)).thenReturn(input);
        when(loanSimulationWorkflow.execute(input)).thenReturn(result);
        when(parseLoanResultToLoanSimulationResponseActivity.execute(
                result,
                userEmail
        )).thenReturn(response);

        assertEquals(response, unit.execute(loanSimulationData));
    }
}