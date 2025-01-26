package challenge.domain.workflow.activity.entry.simple;

import challenge.domain.mappers.LoanMapper;
import challenge.domain.workflow.output.LoanSimulationResult;
import challenge.model.LoanSimulationResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParseLoanResultToLoanSimulationResponseActivity {

    private final @NonNull LoanMapper loanMapper;

    public LoanSimulationResponse execute(LoanSimulationResult loanSimulationResult, String userEmail) {
        return loanMapper.toLoanSimulationResponse(loanSimulationResult, userEmail);
    }
}
