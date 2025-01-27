package challenge.application.workflow.activity.entry.multiple.item;

import challenge.domain.mappers.LoanMapper;
import challenge.application.workflow.output.LoanSimulationResult;
import challenge.model.LoanSimulationItemResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParseResultToLoanSimulationItemResponseActivity {

    private final @NonNull LoanMapper loanMapper;

    public LoanSimulationItemResponse execute(LoanSimulationResult loanSimulationResult, String userEmail) {
        return new LoanSimulationItemResponse()
                .success(true)
                .loanSimulationResponse(loanMapper.toLoanSimulationResponse(loanSimulationResult, userEmail));
    }
}
