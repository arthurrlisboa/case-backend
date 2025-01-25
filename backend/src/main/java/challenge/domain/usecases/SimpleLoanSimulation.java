package challenge.domain.usecases;


import com.challenge.backend.model.LoanSimulationData;
import com.challenge.backend.model.LoanSimulationResponse;

public interface SimpleLoanSimulation {
    LoanSimulationResponse simulateLoan(LoanSimulationData data);
}
