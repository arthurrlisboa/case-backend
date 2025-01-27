package challenge.application.workflow.definition.core;

import challenge.application.workflow.activity.core.CalculateMonthlyInstallmentAmountActivity;
import challenge.application.workflow.activity.core.CreateLoanSimulationResponseActivity;
import challenge.application.workflow.activity.core.GetClientLoanRateActivity;
import challenge.application.workflow.input.LoanSimulationInput;
import challenge.application.workflow.output.LoanSimulationResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoanSimulationWorkflow {

    private final @NonNull GetClientLoanRateActivity getClientInterestRateActivity;
    private final @NonNull CalculateMonthlyInstallmentAmountActivity calculateMonthlyInstallmentAmountActivity;
    private final @NonNull CreateLoanSimulationResponseActivity createLoanSimulationResponseActivity;

    public LoanSimulationResult execute(LoanSimulationInput loanSimulationInput) {
        var clientMonthlyInterestRate = getClientInterestRateActivity.execute(loanSimulationInput.getClientBirthDate());
        var clientMonthlyInstallmentAmount = calculateMonthlyInstallmentAmountActivity.execute(
                loanSimulationInput.getPaymentTermMonths(),
                loanSimulationInput.getLoanAmount(),
                clientMonthlyInterestRate
        );
        return createLoanSimulationResponseActivity.execute(loanSimulationInput, clientMonthlyInstallmentAmount);
    }
}
