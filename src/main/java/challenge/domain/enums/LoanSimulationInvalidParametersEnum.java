package challenge.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoanSimulationInvalidParametersEnum {

    INVALID_CLIENT_BIRTH_DATE("The birth date format is invalid. Please use the format DD/MM/YYYY"),
    INVALID_LOAN_AMOUNT("The loan amount must be greater than %s"),
    INVALID_PAYMENT_TERM_MONTH("The loan term must be at least %s months. Please provide a valid term");


    private final String message;

}
