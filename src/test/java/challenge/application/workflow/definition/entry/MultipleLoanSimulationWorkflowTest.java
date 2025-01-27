package challenge.application.workflow.definition.entry;

import challenge.application.exceptions.MultipleLoanSimulationInternalServerErrorException;
import challenge.application.workflow.activity.entry.multiple.handler.MultipleLoanSimulationUnexpectedExceptionHandler;
import challenge.application.workflow.definition.entry.asyncprocess.LoanSimulationAsyncProcessWorkflow;
import challenge.model.LoanSimulationData;
import challenge.model.LoanSimulationItemResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MultipleLoanSimulationWorkflowTest {

    @InjectMocks
    private MultipleLoanSimulationWorkflow unit;

    @Mock
    private LoanSimulationAsyncProcessWorkflow loanSimulationAsyncProcessWorkflow;

    @Mock
    private MultipleLoanSimulationUnexpectedExceptionHandler multipleLoanSimulationUnexpectedExceptionHandler;

    @Mock
    private LoanSimulationData loanSimulationData1;

    @Mock
    private LoanSimulationData loanSimulationData2;

    @Mock
    private LoanSimulationItemResponse itemResponse1;

    @Mock
    private LoanSimulationItemResponse itemResponse2;

    @Test
    @DisplayName("Deve executar o fluxo async pra todos itens da lista de entrada e retornar respostas")
    void executeAsyncTestCase01() {
        var dataList = List.of(loanSimulationData1, loanSimulationData2);

        when(loanSimulationAsyncProcessWorkflow.execute(loanSimulationData1)).thenReturn(itemResponse1);
        when(loanSimulationAsyncProcessWorkflow.execute(loanSimulationData2)).thenReturn(itemResponse2);

        var expectedResponse = List.of(itemResponse1, itemResponse2);

        assertEquals(expectedResponse, unit.executeAsync(dataList));
    }

    @Test
    @DisplayName(
            "Deve executar re-lançar excpetion que o método de handler lançar, " +
                    "quando uma das chamadas async lança exception inesperada"
    )
    void executeAsyncTestCase02() {
        var dataList = List.of(loanSimulationData1, loanSimulationData2);
        var exception = new RuntimeException();
        var exceptionFromHandler = new MultipleLoanSimulationInternalServerErrorException("test");

        when(loanSimulationAsyncProcessWorkflow.execute(loanSimulationData1)).thenThrow(exception);
        when(loanSimulationAsyncProcessWorkflow.execute(loanSimulationData2)).thenReturn(itemResponse2);

        when(multipleLoanSimulationUnexpectedExceptionHandler.handleException(any())).thenThrow(exceptionFromHandler);
        assertThrows(exceptionFromHandler.getClass(), () -> unit.executeAsync(dataList));
    }
}