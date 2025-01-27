package challenge.application.workflow.activity.core;

import challenge.application.workflow.input.LoanSimulationInput;
import challenge.application.workflow.output.LoanSimulationResult;
import org.springframework.stereotype.Component;

@Component
public class CreateLoanSimulationResponseActivity {

    public LoanSimulationResult execute(LoanSimulationInput loanSimulationInput, Double installmentAmount) {
        return getLoanSimulationResponse(
                loanSimulationInput.getLoanAmount(),
                loanSimulationInput.getPaymentTermMonths(),
                installmentAmount
        );
    }

    private LoanSimulationResult getLoanSimulationResponse(
            Double loanAmount,
            Integer paymentTermMonths,
            Double installmentAmount
    ) {
        var totalLoanAmount = getTotalLoanAmount(installmentAmount, paymentTermMonths);
        return LoanSimulationResult.builder()
                .monthlyInstallmentAmount(installmentAmount)
                .totalPaymentAmount(totalLoanAmount)
                .totalInterestPaid(getTotalInterestPaid(totalLoanAmount, loanAmount))
                .build();
    }

    private Double getTotalInterestPaid(Double totalLoanAmount, Double loanAmount) {
        return totalLoanAmount - loanAmount;
    }

    private static double getTotalLoanAmount(Double installmentAmount, Integer paymentTermMonths) {
        return installmentAmount * paymentTermMonths;
    }
}
