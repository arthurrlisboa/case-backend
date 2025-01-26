package challenge.domain.workflow.activity.usecase;

import challenge.domain.service.LoanCalculationService;
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
