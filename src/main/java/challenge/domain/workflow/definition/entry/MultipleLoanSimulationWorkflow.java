package challenge.domain.workflow.definition.entry;

import challenge.model.LoanSimulationData;
import challenge.model.LoanSimulationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MultipleLoanSimulationWorkflow {

    public List<LoanSimulationResponse> execute(List<LoanSimulationData> loanSimulationDataList) {
        return Collections.emptyList();
    }
}
