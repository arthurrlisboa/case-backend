package challenge.domain.workflow.activity.entry.multiple.handler;

import challenge.model.LoanSimulationData;
import challenge.model.LoanSimulationItemResponse;
import challenge.model.LoanSimulationResponse;
import org.springframework.stereotype.Component;

import static challenge.domain.constants.MessageConstants.UNEXPECTED_LOAN_SIMULATION_ERROR_MESSAGE_TEMPLATE;

@Component
public class HandleLoanSimulationUnexpectedExcpetion {

    public LoanSimulationItemResponse handleException(LoanSimulationData loanSimulationData, Exception e) {
        return new LoanSimulationItemResponse()
                .success(false)
                .errorMessage(getErrorMessage(e))
                .loanSimulationResponse(getLoanSimulationResponse(loanSimulationData.getUserEmail()));
    }

    private LoanSimulationResponse getLoanSimulationResponse(String userEmail) {
        return new LoanSimulationResponse().userEmail(userEmail);
    }

    private String getErrorMessage(Exception ex) {
        return UNEXPECTED_LOAN_SIMULATION_ERROR_MESSAGE_TEMPLATE + ex.getClass().getSimpleName();
    }
}
