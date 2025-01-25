package challenge.domain.usecases;


import challenge.application.workflow.input.LoanSimulationInput;
import challenge.application.workflow.output.LoanSimulationResult;

public interface LoanSimulation {
    LoanSimulationResult execute(LoanSimulationInput loanSimulationData);
}
