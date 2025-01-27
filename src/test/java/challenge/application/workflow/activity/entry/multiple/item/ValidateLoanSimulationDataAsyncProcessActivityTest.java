package challenge.application.workflow.activity.entry.multiple.item;

import challenge.application.service.LoanSimulationDataValidationService;
import challenge.model.LoanSimulationData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateLoanSimulationDataAsyncProcessActivityTest {

    @InjectMocks
    private ValidateLoanSimulationDataAsyncProcessActivity unit;

    @Mock
    private LoanSimulationDataValidationService loanSimulationDataValidationService;

    @Test
    @DisplayName("Deve retornar lista vazia quando todos papâmetros são válidos")
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

        var output = unit.execute(validLoanSimulationData);

        assertTrue(output.isEmpty());
    }

    @Test
    @DisplayName("Deve retornar lista com todas mensagens de erro quando todos papâmetros são inválidos, com dados vazios")
    void executeTestCase02() {
        var validEmail = "";
        var validClientBirthDate = " ";
        var validLoanAmount = 10.0;
        var validPaymentTermMonths = 1;

        var validLoanSimulationData = new LoanSimulationData(
                validEmail,
                validLoanAmount,
                validClientBirthDate,
                validPaymentTermMonths
        );

        when(loanSimulationDataValidationService.isInvalidLoanAmount(validLoanAmount)).thenReturn(true);
        when(loanSimulationDataValidationService.isInvalidPaymentTerm(validPaymentTermMonths)).thenReturn(true);

        var output = unit.execute(validLoanSimulationData);

        assertFalse(output.isEmpty());
    }

    @Test
    @DisplayName("Deve retornar lista com todas mensagens de erro quando todos papâmetros são preenchidos mas são inválidos")
    void executeTestCase03() {
        var validEmail = "emailteste.com";
        var validClientBirthDate = "10-10-1998";
        var validLoanAmount = 10.0;
        var validPaymentTermMonths = 1;

        var validLoanSimulationData = new LoanSimulationData(
                validEmail,
                validLoanAmount,
                validClientBirthDate,
                validPaymentTermMonths
        );

        when(loanSimulationDataValidationService.isInvalidLoanAmount(validLoanAmount)).thenReturn(true);
        when(loanSimulationDataValidationService.isInvalidPaymentTerm(validPaymentTermMonths)).thenReturn(true);

        var output = unit.execute(validLoanSimulationData);

        assertFalse(output.isEmpty());
    }
}