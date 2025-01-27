package challenge.application.workflow.definition.entry;

import challenge.application.workflow.activity.entry.ParseLoanSimulationDataToInputActivity;
import challenge.application.workflow.activity.entry.simple.ParseLoanResultToLoanSimulationResponseActivity;
import challenge.application.workflow.activity.entry.simple.ValidateLoanSimulationDataActivity;
import challenge.application.workflow.definition.core.LoanSimulationWorkflow;
import challenge.model.LoanSimulationData;
import challenge.model.LoanSimulationResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimpleLoanSimulationWorkflow{

    private final @NonNull ValidateLoanSimulationDataActivity validateLoanSimulationDataActivity;
    private final @NonNull ParseLoanSimulationDataToInputActivity parseLoanSimulationDataToInputActivity;
    private final @NonNull LoanSimulationWorkflow loanSimulationWorkflow;
    private final @NonNull ParseLoanResultToLoanSimulationResponseActivity
            parseLoanResultToLoanSimulationResponseActivity;

    public LoanSimulationResponse execute(LoanSimulationData loanSimulationData) {
        validateLoanSimulationDataActivity.execute(loanSimulationData);
        var loanSimulationInput = parseLoanSimulationDataToInputActivity.execute(loanSimulationData);
        var loanSimulationResult = loanSimulationWorkflow.execute(loanSimulationInput);
        return parseLoanResultToLoanSimulationResponseActivity.execute(
                loanSimulationResult,
                loanSimulationInput.getUserEmail()
        );
    }
}
