package challenge.domain.workflow.definition.entry;

import challenge.model.LoanSimulationData;
import challenge.model.LoanSimulationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MultipleLoanSimulationWorkflow {

    private final SimpleLoanSimulationWorkflow simpleLoanSimulationWorkflow;

    public List<LoanSimulationResponse> execute(List<LoanSimulationData> loanSimulationDataList) {
        return loanSimulationDataList.stream().map(simpleLoanSimulationWorkflow::execute).toList();
    }
}
