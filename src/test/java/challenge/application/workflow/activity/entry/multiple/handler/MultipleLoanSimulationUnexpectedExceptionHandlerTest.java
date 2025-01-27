package challenge.application.workflow.activity.entry.multiple.handler;

import challenge.application.exceptions.MultipleLoanSimulationInternalServerErrorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static challenge.domain.constants.MessageConstants.UNEXPECTED_LOAN_SIMULATION_ERROR_MESSAGE_TEMPLATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MultipleLoanSimulationUnexpectedExceptionHandlerTest {

    private MultipleLoanSimulationUnexpectedExceptionHandler unit;

    @BeforeEach
    void setUp() {
        unit = new MultipleLoanSimulationUnexpectedExceptionHandler();
    }

    @Test
    @DisplayName("Deve lançar 'MultipleLoanSimulationInternalServerErrorException' com mensagem de erro esperada")
    void handleExceptionTest() {
        var exception = new RuntimeException();
        var expectedErrorMessage = UNEXPECTED_LOAN_SIMULATION_ERROR_MESSAGE_TEMPLATE +
                exception.getClass().getSimpleName();
        var output = assertThrows(MultipleLoanSimulationInternalServerErrorException.class,
                () -> unit.handleException(exception));

        assertEquals(expectedErrorMessage, output.getMessage());
    }
}