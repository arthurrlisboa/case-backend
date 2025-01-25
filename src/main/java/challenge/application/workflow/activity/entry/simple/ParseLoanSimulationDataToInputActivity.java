package challenge.application.workflow.activity.entry.simple;

import challenge.application.workflow.input.LoanSimulationInput;
import com.challenge.backend.model.LoanSimulationData;
import org.springframework.stereotype.Component;

@Component
public class ParseLoanSimulationDataToInputActivity {
    public LoanSimulationInput execute(LoanSimulationData loanSimulationData) {
        return null;
    }
}
