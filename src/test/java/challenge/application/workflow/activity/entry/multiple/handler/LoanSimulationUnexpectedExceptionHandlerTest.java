package challenge.application.workflow.activity.entry.multiple.handler;

import challenge.model.LoanSimulationData;
import challenge.model.LoanSimulationItemResponse;
import challenge.model.LoanSimulationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static challenge.domain.constants.MessageConstants.UNEXPECTED_LOAN_SIMULATION_ERROR_MESSAGE_TEMPLATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanSimulationUnexpectedExceptionHandlerTest {

    private LoanSimulationUnexpectedExceptionHandler unit;

    @Mock
    private LoanSimulationData data;

    @BeforeEach
    void setup(){
        unit = new LoanSimulationUnexpectedExceptionHandler();
    }

    @Test
    @DisplayName("Deve retornar resposta de simulação com erro")
    void handleExceptionTestCase() {
        var exception = new RuntimeException();
        var expectedErrorMessage = UNEXPECTED_LOAN_SIMULATION_ERROR_MESSAGE_TEMPLATE +
                exception.getClass().getSimpleName();
        var userEmail = "test@test.com";

        when(data.getUserEmail()).thenReturn(userEmail);

        var expectedResponse = new LoanSimulationResponse().userEmail(userEmail);

        var expected = new LoanSimulationItemResponse()
                .success(false)
                .errorMessage(expectedErrorMessage)
                .loanSimulationResponse(expectedResponse);

        assertEquals(expected, unit.handleException(data, exception));
    }
}