package challenge.controller;

import challenge.api.LoanApi;
import challenge.application.workflow.definition.entry.MultipleLoanSimulationWorkflow;
import challenge.application.workflow.definition.entry.SimpleLoanSimulationWorkflow;
import challenge.model.LoanSimulationData;
import challenge.model.LoanSimulationItemResponse;
import challenge.model.LoanSimulationResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanController implements LoanApi {

    private final @NonNull SimpleLoanSimulationWorkflow simpleLoanSimulationWorkflow;
    private final @NonNull MultipleLoanSimulationWorkflow multipleLoanSimulationWorkflow;

    @Override
    public ResponseEntity<LoanSimulationResponse> processLoanSimulation(LoanSimulationData loanSimulationData) {
        return ResponseEntity.ok(simpleLoanSimulationWorkflow.execute(loanSimulationData));
    }

    @Override
    public ResponseEntity<List<LoanSimulationItemResponse>> processLoanSimulationList(
            List<LoanSimulationData> loanSimulationData
    ) {
        return ResponseEntity.ok(multipleLoanSimulationWorkflow.executeAsync(loanSimulationData));
    }
}
