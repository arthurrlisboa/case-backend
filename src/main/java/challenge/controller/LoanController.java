package challenge.controller;

import challenge.application.workflow.item.entry.SimpleLoanSimulationWorkflow;
import com.challenge.backend.api.LoanApi;
import com.challenge.backend.model.LoanSimulationData;
import com.challenge.backend.model.LoanSimulationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoanController implements LoanApi {

    private final SimpleLoanSimulationWorkflow simpleLoanSimulationWorkflow;

    @Override
    public ResponseEntity<LoanSimulationResponse> processLoanSimulation(LoanSimulationData loanSimulationData) {
        return ResponseEntity.ok(simpleLoanSimulationWorkflow.execute(loanSimulationData));
    }
}
