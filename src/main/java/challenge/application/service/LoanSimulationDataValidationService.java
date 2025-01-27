package challenge.application.service;

import org.springframework.stereotype.Service;

import static challenge.domain.constants.AppConstants.LoanSimulationDataConstants.MIN_LOAN_AMOUNT;
import static challenge.domain.constants.AppConstants.LoanSimulationDataConstants.MIN_PAYMENT_TERM_MONTHS;

@Service
public class LoanSimulationDataValidationService {

    public boolean isValidLoanAmount(Double loanAmount) {
        return loanAmount.compareTo(MIN_LOAN_AMOUNT) >= 0;
    }

    public boolean isValidPaymentTerm(Integer paymentTermMonths) {
        return paymentTermMonths >= MIN_PAYMENT_TERM_MONTHS;
    }
}
