package challenge.domain.workflow.activity.entry;

import challenge.domain.mappers.LoanMapper;
import challenge.domain.workflow.input.LoanSimulationInput;
import challenge.model.LoanSimulationData;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParseLoanSimulationDataToInputActivity {

    private final @NonNull LoanMapper loanMapper;

    public LoanSimulationInput execute(LoanSimulationData loanSimulationData) {
        return loanMapper.toLoanSimulationInput(loanSimulationData);
    }
}
