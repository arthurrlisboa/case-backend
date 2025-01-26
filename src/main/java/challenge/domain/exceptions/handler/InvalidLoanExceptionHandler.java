package challenge.domain.exceptions.handler;

import challenge.domain.exceptions.InvalidLoanSimulationRequestDataException;
import challenge.model.LoanSimulationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvalidLoanExceptionHandler {

    @ExceptionHandler(InvalidLoanSimulationRequestDataException.class)
    public ResponseEntity<LoanSimulationErrorResponse> handleInvalidDataException(
            InvalidLoanSimulationRequestDataException ex
    ) {
        return new ResponseEntity<>(getErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private LoanSimulationErrorResponse getErrorResponse(String message) {
        return new LoanSimulationErrorResponse().error(message);
    }
}
