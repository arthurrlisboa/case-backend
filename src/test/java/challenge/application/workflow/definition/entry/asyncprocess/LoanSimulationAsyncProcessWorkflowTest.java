package challenge.application.workflow.definition.entry.asyncprocess;

import challenge.application.workflow.activity.entry.multiple.item.CreateLoanSimulationErrorResponseActivity;
import challenge.application.workflow.activity.entry.multiple.item.ValidateLoanSimulationDataAsyncProcessActivity;
import challenge.application.workflow.definition.entry.asyncprocess.item.ProcessValidLoanSimulationWorkflow;
import challenge.model.LoanSimulationData;
import challenge.model.LoanSimulationItemResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanSimulationAsyncProcessWorkflowTest {

    @InjectMocks
    private LoanSimulationAsyncProcessWorkflow unit;

    @Mock
    private ValidateLoanSimulationDataAsyncProcessActivity validateLoanSimulationDataAsyncProcessActivity;

    @Mock
    private ProcessValidLoanSimulationWorkflow processValidLoanSimulationWorkflow;

    @Mock
    private CreateLoanSimulationErrorResponseActivity createLoanSimulationErrorResponseActivity;

    @Mock
    private LoanSimulationData loanSimulationData;

    @Mock
    private LoanSimulationItemResponse loanSimulationItemResponse;

    @Test
    @DisplayName("Deve resposta de processamento quando input é válido")
    void executeTestCase01() {
        when(validateLoanSimulationDataAsyncProcessActivity.execute(loanSimulationData)).thenReturn(new ArrayList<>());
        when(processValidLoanSimulationWorkflow.execute(loanSimulationData)).thenReturn(loanSimulationItemResponse);

        assertEquals(loanSimulationItemResponse, unit.execute(loanSimulationData));
    }

    @Test
    @DisplayName("Deve resposta de erro de simulação quando input é inválido")
    void executeTestCase02() {
        var invalidParametersList = List.of("Erro validação");
        when(validateLoanSimulationDataAsyncProcessActivity.execute(loanSimulationData))
                .thenReturn(invalidParametersList);
        when(createLoanSimulationErrorResponseActivity.execute(loanSimulationData, invalidParametersList))
                .thenReturn(loanSimulationItemResponse);

        assertEquals(loanSimulationItemResponse, unit.execute(loanSimulationData));
    }
}