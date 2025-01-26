package challenge.domain.workflow.definition.entry;

import challenge.domain.workflow.activity.entry.ParseLoanSimulationDataToInputActivity;
import challenge.domain.workflow.activity.entry.simple.ParseLoanResultToLoanSimulationResponseActivity;
import challenge.domain.workflow.activity.entry.simple.ValidateLoanSimulationDataActivity;
import challenge.domain.workflow.definition.usecase.LoanSimulationWorkflow;
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
    private final @NonNull ParseLoanResultToLoanSimulationResponseActivity parseLoanResultToLoanSimulationResponseActivity;

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
