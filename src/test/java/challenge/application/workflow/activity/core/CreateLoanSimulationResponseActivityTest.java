package challenge.application.workflow.activity.core;

import challenge.application.workflow.input.LoanSimulationInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateLoanSimulationResponseActivityTest {

    private CreateLoanSimulationResponseActivity activity;

    @BeforeEach
    void setup(){
        activity = new CreateLoanSimulationResponseActivity();
    }

    @Test
    @DisplayName("Deve criar resposta de simulação com parcela mensal e input")
    void execute() {
        var installments = 12;
        var loanAmount = 1000.00;
        var input = LoanSimulationInput.builder()
                .paymentTermMonths(installments)
                .loanAmount(loanAmount)
                .build();
        var installmentAmount = 200.00;

        var result = activity.execute(input, installmentAmount);

        var expectedTotalPaymentAmount = installmentAmount * installments;
        var expectedTotalInterestPaid = expectedTotalPaymentAmount - loanAmount;

        assertAll(
                () -> assertEquals(
                        expectedTotalPaymentAmount,
                        result.getTotalPaymentAmount(),
                        0.01,
                        "O total pago está incorreto"
                ),
                () -> assertEquals(
                        expectedTotalInterestPaid,
                        result.getTotalInterestPaid(),
                        0.01,
                        "Os juros pagos estão incorretos"
                ),
                () ->assertEquals(
                        installmentAmount,
                        result.getMonthlyInstallmentAmount(),
                        0.01,
                        "O valor da parcela mensal está incorreto"
                )
        );
    }
}