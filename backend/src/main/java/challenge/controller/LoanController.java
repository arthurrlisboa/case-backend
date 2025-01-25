package challenge.controller;

import com.challenge.backend.api.LoanApi;
import com.challenge.backend.model.LoanSimulationData;
import com.challenge.backend.model.LoanSimulationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController implements LoanApi {

    @Override
    public ResponseEntity<LoanSimulationResponse> processLoanSimulation(LoanSimulationData loanSimulationData) {
        return LoanApi.super.processLoanSimulation(loanSimulationData);
    }
}
