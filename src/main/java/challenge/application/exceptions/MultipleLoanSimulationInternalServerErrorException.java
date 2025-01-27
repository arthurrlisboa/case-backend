package challenge.application.exceptions;

public class MultipleLoanSimulationInternalServerErrorException extends RuntimeException{
    public MultipleLoanSimulationInternalServerErrorException(String message) {
        super(message);
    }
}
