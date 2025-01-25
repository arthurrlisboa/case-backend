package challenge.application.workflow.item.entry;

import challenge.application.workflow.activity.entry.simple.ParseLoanResultToLoanSimulationResponseActivity;
import challenge.application.workflow.activity.entry.simple.ParseLoanSimulationDataToInputActivity;
import challenge.application.workflow.item.core.LoanSimulationWorkflow;
import com.challenge.backend.model.LoanSimulationData;
import com.challenge.backend.model.LoanSimulationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimpleLoanSimulationWorkflow{

    private final ParseLoanSimulationDataToInputActivity parseLoanSimulationDataToInputActivity;
    private final LoanSimulationWorkflow loanSimulationWorkflow;
    private final ParseLoanResultToLoanSimulationResponseActivity parseLoanResultToLoanSimulationResponseActivity;

    public LoanSimulationResponse execute(LoanSimulationData loanSimulationData) {
        var loanSimulationInput = parseLoanSimulationDataToInputActivity.execute(loanSimulationData);
        var loanSimulationResult = loanSimulationWorkflow.execute(loanSimulationInput);
        return parseLoanResultToLoanSimulationResponseActivity.execute(loanSimulationResult);
    }
}
