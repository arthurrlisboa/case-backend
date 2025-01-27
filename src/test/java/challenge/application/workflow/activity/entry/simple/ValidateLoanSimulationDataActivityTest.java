package challenge.application.workflow.activity.entry.simple;

import challenge.application.exceptions.InvalidLoanSimulationRequestDataException;
import challenge.application.service.LoanSimulationDataValidationService;
import challenge.model.LoanSimulationData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static challenge.application.util.CurrencyUtil.formatToBRL;
import static challenge.domain.constants.AppConstants.LoanSimulationDataConstants.MIN_LOAN_AMOUNT;
import static challenge.domain.constants.AppConstants.LoanSimulationDataConstants.MIN_PAYMENT_TERM_MONTHS;
import static challenge.domain.constants.MessageConstants.INVALID_LOAN_SIMULATION_DATA_MESSAGE_TEMPLATE;
import static challenge.domain.constants.MessageConstants.MESSAGE_DELIMITER;
import static challenge.domain.enums.LoanSimulationInvalidParametersEnum.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateLoanSimulationDataActivityTest {

    @InjectMocks
    private ValidateLoanSimulationDataActivity unit;

    @Mock
    private LoanSimulationDataValidationService loanSimulationDataValidationService;

    @Test
    @DisplayName("Deve retornar sem lançar exception quando todos papâmetros são válidos")
    void executeTestCase01() {
        var validEmail = "test@test.com";
        var validClientBirthDate = "10/12/1990";
        var validLoanAmount = 100.0;
        var validPaymentTermMonths = 10;

        var validLoanSimulationData = new LoanSimulationData(
                validEmail,
                validLoanAmount,
                validClientBirthDate,
                validPaymentTermMonths
        );

        when(loanSimulationDataValidationService.isInvalidLoanAmount(validLoanAmount)).thenReturn(false);
        when(loanSimulationDataValidationService.isInvalidPaymentTerm(validPaymentTermMonths)).thenReturn(false);

        assertDoesNotThrow(() -> unit.execute(validLoanSimulationData));
    }

    @Test
    @DisplayName(
            "Deve lançar 'InvalidLoanSimulationRequestDataException' " +
                    "quando todos papâmetros são inválidos, com dados vazios"
    )
    void executeTestCase02() {
        var validEmail = "";
        var validClientBirthDate = " ";
        var validLoanAmount = 10.0;
        var validPaymentTermMonths = 1;

        var invalidMessageList = List.of(
                INVALID_USER_EMAIL.getMessage(), INVALID_CLIENT_BIRTH_DATE.getMessage(),
                String.format(INVALID_LOAN_AMOUNT.getMessage(), formatToBRL(MIN_LOAN_AMOUNT)),
                String.format(INVALID_PAYMENT_TERM_MONTH.getMessage(), MIN_PAYMENT_TERM_MONTHS)
        );
        var expectedErrorMessage = INVALID_LOAN_SIMULATION_DATA_MESSAGE_TEMPLATE +
                String.join(MESSAGE_DELIMITER, invalidMessageList);

        var validLoanSimulationData = new LoanSimulationData(
                validEmail,
                validLoanAmount,
                validClientBirthDate,
                validPaymentTermMonths
        );

        when(loanSimulationDataValidationService.isInvalidLoanAmount(validLoanAmount)).thenReturn(true);
        when(loanSimulationDataValidationService.isInvalidPaymentTerm(validPaymentTermMonths)).thenReturn(true);

        var outputException = assertThrows(InvalidLoanSimulationRequestDataException.class,
                () -> unit.execute(validLoanSimulationData));

        assertEquals(expectedErrorMessage, outputException.getMessage());

    }

    @Test
    @DisplayName(
            "Deve lançar 'InvalidLoanSimulationRequestDataException' quando" +
            " todos papâmetros são preenchidos mas são inválidos"
    )
    void executeTestCase03() {
        var validEmail = "emailteste.com";
        var validClientBirthDate = "10-10-1998";
        var validLoanAmount = 10.0;
        var validPaymentTermMonths = 1;

        var invalidMessageList = List.of(
                INVALID_USER_EMAIL.getMessage(), INVALID_CLIENT_BIRTH_DATE.getMessage(),
                String.format(INVALID_LOAN_AMOUNT.getMessage(), formatToBRL(MIN_LOAN_AMOUNT)),
                String.format(INVALID_PAYMENT_TERM_MONTH.getMessage(), MIN_PAYMENT_TERM_MONTHS)
        );
        var expectedErrorMessage = INVALID_LOAN_SIMULATION_DATA_MESSAGE_TEMPLATE +
                String.join(MESSAGE_DELIMITER, invalidMessageList);

        var validLoanSimulationData = new LoanSimulationData(
                validEmail,
                validLoanAmount,
                validClientBirthDate,
                validPaymentTermMonths
        );

        when(loanSimulationDataValidationService.isInvalidLoanAmount(validLoanAmount)).thenReturn(true);
        when(loanSimulationDataValidationService.isInvalidPaymentTerm(validPaymentTermMonths)).thenReturn(true);

        var outputException = assertThrows(InvalidLoanSimulationRequestDataException.class,
                () -> unit.execute(validLoanSimulationData));

        assertEquals(expectedErrorMessage, outputException.getMessage());
    }
}