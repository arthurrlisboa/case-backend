package challenge.application.exceptions;

public class InvalidLoanSimulationRequestDataException extends RuntimeException{
    public InvalidLoanSimulationRequestDataException(String message) {
        super(message);
    }
}
