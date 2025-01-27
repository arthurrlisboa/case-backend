package challenge.application.workflow.activity.entry.multiple.item;

import challenge.model.LoanSimulationData;
import challenge.model.LoanSimulationItemResponse;
import challenge.model.LoanSimulationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static challenge.domain.constants.MessageConstants.INVALID_LOAN_SIMULATION_DATA_MESSAGE_TEMPLATE;
import static challenge.domain.constants.MessageConstants.MESSAGE_DELIMITER;
import static challenge.domain.enums.LoanSimulationInvalidParametersEnum.INVALID_CLIENT_BIRTH_DATE;
import static challenge.domain.enums.LoanSimulationInvalidParametersEnum.INVALID_LOAN_AMOUNT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateLoanSimulationErrorResponseActivityTest {

    private CreateLoanSimulationErrorResponseActivity unit;

    @Mock
    private LoanSimulationData data;

    @BeforeEach
    void setup(){
        unit = new CreateLoanSimulationErrorResponseActivity();
    }

    @Test
    @DisplayName("Deve retornar resposta de simulação com erro de parâmetros inválidos")
    void handleExceptionTestCase() {
        var invalidParametersList  = List.of(
                INVALID_CLIENT_BIRTH_DATE.getMessage(), INVALID_LOAN_AMOUNT.getMessage()
        );

        var userEmail = "test@test.com";
        when(data.getUserEmail()).thenReturn(userEmail);

        var expectedResponse = new LoanSimulationResponse().userEmail(userEmail);
        var expectedErrorMessage = INVALID_LOAN_SIMULATION_DATA_MESSAGE_TEMPLATE +
                String.join(MESSAGE_DELIMITER, invalidParametersList);

        var expected = new LoanSimulationItemResponse()
                .success(false)
                .errorMessage(expectedErrorMessage)
                .loanSimulationResponse(expectedResponse);

        assertEquals(expected, unit.execute(data, invalidParametersList));
    }
}