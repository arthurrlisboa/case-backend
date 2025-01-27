package challenge.application.workflow.activity.core;

import challenge.application.service.LoanCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CalculateMonthlyInstallmentAmountActivity {

    private final LoanCalculationService loanCalculationService;

    public Double execute(Integer paymentTermMonths, Double loanAmount, Double monthlyInterestRate) {
        return loanCalculationService.calculateInstallmentAmount(
                paymentTermMonths,
                loanAmount,
                monthlyInterestRate
        );
    }
}
