package challenge.application.workflow.definition.entry.asyncprocess.item;

import challenge.application.workflow.activity.entry.ParseLoanSimulationDataToInputActivity;
import challenge.application.workflow.activity.entry.multiple.handler.HandleLoanSimulationUnexpectedExcpetion;
import challenge.application.workflow.activity.entry.multiple.item.ParseResultToLoanSimulationItemResponseActivity;
import challenge.application.workflow.definition.core.LoanSimulationWorkflow;
import challenge.model.LoanSimulationData;
import challenge.model.LoanSimulationItemResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProcessValidLoanSimulationWorkflow {

    private final @NonNull ParseLoanSimulationDataToInputActivity parseLoanSimulationDataToInputActivity;
    private final @NonNull LoanSimulationWorkflow loanSimulationWorkflow;
    private final @NonNull ParseResultToLoanSimulationItemResponseActivity
            parseResultToLoanSimulationItemResponseActivity;
    private final @NonNull HandleLoanSimulationUnexpectedExcpetion handleLoanSimulationUnexpectedExcpetion;

    public LoanSimulationItemResponse execute(LoanSimulationData loanSimulationData){
        try{
            var loanSimulationInput = parseLoanSimulationDataToInputActivity.execute(loanSimulationData);
            var loanSimulationResult = loanSimulationWorkflow.execute(loanSimulationInput);
            return parseResultToLoanSimulationItemResponseActivity.execute(
                    loanSimulationResult,
                    loanSimulationInput.getUserEmail()
            );
        } catch (Exception e) {
            return handleLoanSimulationUnexpectedExcpetion.handleException(loanSimulationData, e);
        }
    }
}
