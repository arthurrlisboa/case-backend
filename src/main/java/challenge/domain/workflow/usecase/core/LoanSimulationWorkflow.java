package challenge.domain.workflow.usecase.core;

import challenge.domain.workflow.activity.core.simple.CalculateMonthlyInstallmentAmountActivity;
import challenge.domain.workflow.activity.core.simple.CreateLoanSimulationResponseActivity;
import challenge.domain.workflow.activity.core.simple.GetClientLoanRateActivity;
import challenge.domain.workflow.input.LoanSimulationInput;
import challenge.domain.workflow.output.LoanSimulationResult;
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
