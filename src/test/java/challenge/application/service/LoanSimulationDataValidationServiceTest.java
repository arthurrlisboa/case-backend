package challenge.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoanSimulationDataValidationServiceTest {

    private LoanSimulationDataValidationService validationService;

    @BeforeEach
    void setup(){
        validationService = new LoanSimulationDataValidationService();
    }

    @Test
    @DisplayName("Deve retornar true quando valor é menor que o mínimo")
    void isInvalidLoanAmountTestCase01() {
        var loanAmount = 49.99;
        assertTrue(
                validationService.isInvalidLoanAmount(loanAmount),
                "O valor do empréstimo deveria ser considerado inválido."
        );
    }

    @Test
    @DisplayName("Deve retornar false quando valor é maior ou igual ao mínimo")
    void isInvalidLoanAmountTestCase02() {
        var loanAmount = 51.99;
        assertFalse(
                validationService.isInvalidLoanAmount(loanAmount),
                "O valor do empréstimo deveria ser considerado válido."
        );
    }

    @Test
    @DisplayName("Deve retornar true quando valor é menor que o mínimo")
    void isInvalidPaymentTermTestCase01() {
        var paymentTermMonths = 1;
        assertTrue(
                validationService.isInvalidPaymentTerm(paymentTermMonths),
                "O prazo de pagamento deveria ser considerado inválido."
        );
    }

    @Test
    @DisplayName("Deve retornar false quando valor é maior ou igual ao mínimo")
    void isInvalidPaymentTermTestCase02() {
        var paymentTermMonths = 2;
        assertFalse(
                validationService.isInvalidPaymentTerm(paymentTermMonths),
                "O prazo de pagamento deveria ser considerado válido."
        );
    }
}