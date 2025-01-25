package challenge.application.workflow.item.core;

import challenge.application.workflow.activity.core.simple.CalculateMonthlyInstallmentAmountActivity;
import challenge.application.workflow.activity.core.simple.CreateLoanSimulationResponseActivity;
import challenge.application.workflow.activity.core.simple.GetClientLoanRateActivity;
import challenge.application.workflow.input.LoanSimulationInput;
import challenge.application.workflow.output.LoanSimulationResult;
import challenge.domain.usecases.LoanSimulation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoanSimulationWorkflow implements LoanSimulation {

    private final GetClientLoanRateActivity getClientLoanRateActivity;
    private final CalculateMonthlyInstallmentAmountActivity calculateMonthlyInstallmentAmountActivity;
    private final CreateLoanSimulationResponseActivity createLoanSimulationResponseActivity;

    public LoanSimulationResult execute(LoanSimulationInput loanSimulationInput) {
        var clientLoanRate = getClientLoanRateActivity.execute(loanSimulationInput.getClientBirthDate());
        var clientMonthlyInstallmentAmount =
                calculateMonthlyInstallmentAmountActivity.execute(loanSimulationInput, clientLoanRate);
        return createLoanSimulationResponseActivity.execute(clientMonthlyInstallmentAmount);
    }
}
