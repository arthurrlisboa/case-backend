package challenge.application.workflow.definition.entry.asyncprocess.item;

import challenge.application.workflow.activity.entry.ParseLoanSimulationDataToInputActivity;
import challenge.application.workflow.activity.entry.multiple.handler.LoanSimulationUnexpectedExceptionHandler;
import challenge.application.workflow.activity.entry.multiple.item.ParseResultToLoanSimulationItemResponseActivity;
import challenge.application.workflow.definition.core.LoanSimulationWorkflow;
import challenge.application.workflow.input.LoanSimulationInput;
import challenge.application.workflow.output.LoanSimulationResult;
import challenge.model.LoanSimulationData;
import challenge.model.LoanSimulationItemResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessValidLoanSimulationWorkflowTest {

    @InjectMocks
    private ProcessValidLoanSimulationWorkflow unit;

    @Mock
    private ParseLoanSimulationDataToInputActivity parseLoanSimulationDataToInputActivity;

    @Mock
    private LoanSimulationWorkflow loanSimulationWorkflow;

    @Mock
    private ParseResultToLoanSimulationItemResponseActivity parseResultToLoanSimulationItemResponseActivity;

    @Mock
    private LoanSimulationUnexpectedExceptionHandler loanSimulationUnexpectedExceptionHandler;

    @Mock
    private LoanSimulationData loanSimulationData;

    @Mock
    private LoanSimulationResult loanSimulationResult;

    @Mock
    private LoanSimulationItemResponse loanSimulationResponse;

    @Mock
    private LoanSimulationInput loanSimulationInput;

    @Test
    @DisplayName("Deve retornar sucesso quando worflow é executado com sucesso")
    void executeTestCase01() {
        var userEmail = "user@email.com";
        when(loanSimulationInput.getUserEmail()).thenReturn(userEmail);

        when(parseLoanSimulationDataToInputActivity.execute(loanSimulationData)).thenReturn(loanSimulationInput);
        when( loanSimulationWorkflow.execute(loanSimulationInput)).thenReturn(loanSimulationResult);
        when(parseResultToLoanSimulationItemResponseActivity.execute(loanSimulationResult, userEmail))
                .thenReturn(loanSimulationResponse);

        assertEquals(loanSimulationResponse, unit.execute(loanSimulationData));
    }

    @Test
    @DisplayName("Deve retornar resposta do handler quando fluxo principal tem algum erro")
    void executeTestCase02() {
        var exception = new RuntimeException();
        when(parseLoanSimulationDataToInputActivity.execute(loanSimulationData)).thenThrow(exception);
        when(loanSimulationUnexpectedExceptionHandler.handleException(loanSimulationData, exception))
                .thenReturn(loanSimulationResponse);
        assertEquals(loanSimulationResponse, unit.execute(loanSimulationData));
    }
}