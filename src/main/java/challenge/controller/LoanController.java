package challenge.controller;

import challenge.api.LoanApi;
import challenge.domain.workflow.usecase.entry.SimpleLoanSimulationWorkflow;
import challenge.model.LoanSimulationData;
import challenge.model.LoanSimulationResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoanController implements LoanApi {

    private final @NonNull SimpleLoanSimulationWorkflow simpleLoanSimulationWorkflow;

    @Override
    public ResponseEntity<LoanSimulationResponse> processLoanSimulation(LoanSimulationData loanSimulationData) {
        return ResponseEntity.ok(simpleLoanSimulationWorkflow.execute(loanSimulationData));
    }
}
