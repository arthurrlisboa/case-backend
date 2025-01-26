package challenge.domain.workflow.activity.entry.multiple.item;

import challenge.domain.mappers.LoanMapper;
import challenge.domain.workflow.output.LoanSimulationResult;
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
