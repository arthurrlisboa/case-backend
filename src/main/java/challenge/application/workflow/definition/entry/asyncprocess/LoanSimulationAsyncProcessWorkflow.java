package challenge.application.workflow.definition.entry.asyncprocess;

import challenge.application.workflow.activity.entry.multiple.item.CreateLoanSimulationErrorResponseActivity;
import challenge.application.workflow.activity.entry.multiple.item.ValidateLoanSimulationDataAsyncProcessActivity;
import challenge.application.workflow.definition.entry.asyncprocess.item.ProcessValidLoanSimulationWorkflow;
import challenge.model.LoanSimulationData;
import challenge.model.LoanSimulationItemResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LoanSimulationAsyncProcessWorkflow {

    private final @NonNull ValidateLoanSimulationDataAsyncProcessActivity
            validateLoanSimulationDataAsyncProcessActivity;

    private final @NonNull ProcessValidLoanSimulationWorkflow processValidLoanSimulationWorkflow;
    private final @NonNull CreateLoanSimulationErrorResponseActivity createLoanSimulationErrorResponseActivity;

    public LoanSimulationItemResponse execute(LoanSimulationData loanSimulationData) {
        var invalidParametersList = validateLoanSimulationDataAsyncProcessActivity.execute(loanSimulationData);
        return isValidLoanSimulationPredicate(invalidParametersList) ?
                processValidLoanSimulationWorkflow.execute(loanSimulationData) :
                createLoanSimulationErrorResponseActivity.execute(loanSimulationData, invalidParametersList);
    }

    private static boolean isValidLoanSimulationPredicate(List<String> invalidParametersList) {
        return invalidParametersList.isEmpty();
    }
}
