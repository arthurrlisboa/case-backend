package challenge.domain.exceptions;

public class InvalidLoanSimulationRequestDataException extends RuntimeException{
    public InvalidLoanSimulationRequestDataException(String message) {
        super(message);
    }
}
