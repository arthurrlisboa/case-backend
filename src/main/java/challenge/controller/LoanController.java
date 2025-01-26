package challenge.controller;

import challenge.domain.workflow.item.entry.SimpleLoanSimulationWorkflow;
import challenge.api.LoanApi;
import challenge.model.LoanSimulationData;
import challenge.model.LoanSimulationResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class LoanController implements LoanApi {

    private final @NonNull SimpleLoanSimulationWorkflow simpleLoanSimulationWorkflow;

    @Override
    public ResponseEntity<LoanSimulationResponse> processLoanSimulation(LoanSimulationData loanSimulationData) {
        return ResponseEntity.ok(simpleLoanSimulationWorkflow.execute(loanSimulationData));
    }
}
