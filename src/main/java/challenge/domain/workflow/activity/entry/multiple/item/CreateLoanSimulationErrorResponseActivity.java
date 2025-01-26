package challenge.domain.workflow.activity.entry.multiple.item;

import challenge.model.LoanSimulationData;
import challenge.model.LoanSimulationItemResponse;
import challenge.model.LoanSimulationResponse;
import org.springframework.stereotype.Component;

import java.util.List;

import static challenge.domain.constants.MessageConstants.INVALID_LOAN_SIMULATION_DATA_MESSAGE_TEMPLATE;
import static challenge.domain.constants.MessageConstants.MESSAGE_DELIMITER;

@Component
public class CreateLoanSimulationErrorResponseActivity {

    public LoanSimulationItemResponse execute(
            LoanSimulationData loanSimulationData,
            List<String> invalidParametersList
    ) {
        return new LoanSimulationItemResponse()
                .success(false)
                .errorMessage(getErrorMessage(invalidParametersList))
                .loanSimulationResponse(getLoanSimulationResponse(loanSimulationData.getUserEmail()));
    }

    private LoanSimulationResponse getLoanSimulationResponse(String userEmail) {
        return new LoanSimulationResponse().userEmail(userEmail);
    }

    private String getErrorMessage(List<String> invalidParametersList) {
        var errorMessage = String.join(MESSAGE_DELIMITER, invalidParametersList);
        return INVALID_LOAN_SIMULATION_DATA_MESSAGE_TEMPLATE + errorMessage;
    }
}
