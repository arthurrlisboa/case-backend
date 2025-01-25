package challenge.application.workflow.activity.core.simple;

import challenge.application.workflow.input.LoanSimulationInput;
import org.springframework.stereotype.Component;

@Component
public class CalculateMonthlyInstallmentAmountActivity {
    public Double execute(LoanSimulationInput loanSimulationInput, Double clientLoanRate) {
        return null;
    }
}
