package challenge.application.workflow.item.core;

import challenge.application.workflow.input.LoanSimulationInput;
import challenge.application.workflow.output.LoanSimulationResult;
import challenge.domain.usecases.LoanSimulation;
import org.springframework.stereotype.Component;

@Component
public class LoanSimulationWorkflow implements LoanSimulation {

    public LoanSimulationResult execute(LoanSimulationInput loanSimulationInput) {
        return null;
    }
}
