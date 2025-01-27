package challenge.application.exceptions.handler;

import challenge.application.exceptions.MultipleLoanSimulationInternalServerErrorException;
import challenge.model.LoanSimulationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MultipleLoanSimulationsErrorHandler {

    @ExceptionHandler(MultipleLoanSimulationInternalServerErrorException.class)
    public ResponseEntity<LoanSimulationErrorResponse> handleInvalidDataException(
            MultipleLoanSimulationInternalServerErrorException ex
    ) {
        return new ResponseEntity<>(getErrorResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private LoanSimulationErrorResponse getErrorResponse(String message) {
        return new LoanSimulationErrorResponse().error(message);
    }
}
