package challenge.application.workflow.activity.entry.multiple.handler;

import challenge.application.exceptions.MultipleLoanSimulationInternalServerErrorException;
import challenge.model.LoanSimulationItemResponse;
import org.springframework.stereotype.Component;

import java.util.List;

import static challenge.domain.constants.MessageConstants.UNEXPECTED_LOAN_SIMULATION_ERROR_MESSAGE_TEMPLATE;

@Component
public class MultipleLoanSimulationUnexpectedExceptionHandler {

    public List<LoanSimulationItemResponse> handleException(Exception ex) {
        throw new MultipleLoanSimulationInternalServerErrorException(getErrorMessage(ex));
    }

    private String getErrorMessage(Exception ex) {
        return UNEXPECTED_LOAN_SIMULATION_ERROR_MESSAGE_TEMPLATE + ex.getClass().getSimpleName();
    }
}
