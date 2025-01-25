package challenge.application.workflow.activity.entry.simple;

import com.challenge.backend.model.LoanSimulationResponse;
import org.springframework.stereotype.Component;

@Component
public class ParseLoanResultToLoanSimulationResponseActivity {
    public LoanSimulationResponse execute(Object loanSimulationResult) {
        return null;
    }
}
