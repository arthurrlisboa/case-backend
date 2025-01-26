package challenge.domain.workflow.item.entry;

import challenge.domain.workflow.activity.entry.simple.ParseLoanResultToLoanSimulationResponseActivity;
import challenge.domain.workflow.activity.entry.simple.ParseLoanSimulationDataToInputActivity;
import challenge.domain.workflow.item.core.LoanSimulationWorkflow;
import com.challenge.backend.model.LoanSimulationData;
import com.challenge.backend.model.LoanSimulationResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimpleLoanSimulationWorkflow{

    private final @NonNull ParseLoanSimulationDataToInputActivity parseLoanSimulationDataToInputActivity;
    private final @NonNull LoanSimulationWorkflow loanSimulationWorkflow;
    private final @NonNull ParseLoanResultToLoanSimulationResponseActivity parseLoanResultToLoanSimulationResponseActivity;

    public LoanSimulationResponse execute(LoanSimulationData loanSimulationData) {
        var loanSimulationInput = parseLoanSimulationDataToInputActivity.execute(loanSimulationData);
        var loanSimulationResult = loanSimulationWorkflow.execute(loanSimulationInput);
        return parseLoanResultToLoanSimulationResponseActivity.execute(loanSimulationResult);
    }
}
