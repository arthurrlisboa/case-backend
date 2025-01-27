package challenge.application.workflow.activity.entry.multiple.item;

import challenge.application.service.LoanSimulationDataValidationService;
import challenge.model.LoanSimulationData;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static challenge.domain.constants.AppConstants.LoanSimulationDataConstants.MIN_LOAN_AMOUNT;
import static challenge.domain.constants.AppConstants.LoanSimulationDataConstants.MIN_PAYMENT_TERM_MONTHS;
import static challenge.domain.enums.LoanSimulationInvalidParametersEnum.*;
import static challenge.application.util.CurrencyUtil.formatToBRL;
import static challenge.application.util.DateUtils.isValidDateFormatDdMmYyyy;
import static challenge.application.util.EmailUtils.isValidEmail;

@Component
@RequiredArgsConstructor
public class ValidateLoanSimulationDataAsyncProcessActivity {

    private final LoanSimulationDataValidationService loanSimulationDataValidationService;

    public List<String> execute(LoanSimulationData loanSimulationData) {
        var invalidParameterMessages = new ArrayList<String>();
        validateUserEmail(loanSimulationData.getUserEmail(), invalidParameterMessages);
        validateClientBirthDate(loanSimulationData.getClientBirthDate(), invalidParameterMessages);
        validateLoanAmount(loanSimulationData.getLoanAmount(), invalidParameterMessages);
        validatePaymentTermMonths(loanSimulationData.getPaymentTermMonths(), invalidParameterMessages);

        return invalidParameterMessages;
    }

    private void validateUserEmail(String userEmail, List<String> invalidParameterMessageList) {
        if(StringUtils.isBlank(userEmail) || !isValidEmail(userEmail)) {
            invalidParameterMessageList.add(INVALID_USER_EMAIL.getMessage());
        }
    }

    private void validateClientBirthDate(String clientBirthDate, List<String> invalidParameterMessageList) {
        if(StringUtils.isBlank(clientBirthDate) || !isValidDateFormatDdMmYyyy(clientBirthDate)){
            invalidParameterMessageList.add(INVALID_CLIENT_BIRTH_DATE.getMessage());
        }
    }

    private void validateLoanAmount(Double loanAmount, List<String> invalidParameterMessageList) {
        if(!loanSimulationDataValidationService.isValidLoanAmount(loanAmount)){
            var message = String.format(INVALID_LOAN_AMOUNT.getMessage(), formatToBRL(MIN_LOAN_AMOUNT));
            invalidParameterMessageList.add(message);
        }
    }

    private void validatePaymentTermMonths(Integer paymentTermMonths, List<String> invalidParameterMessageList) {
        if(!loanSimulationDataValidationService.isValidPaymentTerm(paymentTermMonths)){
            var message = String.format(INVALID_PAYMENT_TERM_MONTH.getMessage(), MIN_PAYMENT_TERM_MONTHS);
            invalidParameterMessageList.add(message);
        }
    }
}
