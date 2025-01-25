package challenge.application.workflow.activity.core.simple;

import challenge.application.workflow.output.LoanSimulationResult;
import org.springframework.stereotype.Component;

@Component
public class CreateLoanSimulationResponseActivity {
    public LoanSimulationResult execute(Double clientMonthlyInstallmentAmount) {
        return null;
    }
}
