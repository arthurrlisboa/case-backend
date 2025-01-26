package challenge.domain.service;

import org.springframework.stereotype.Service;

@Service
public class LoanCalculationService {

    public Double calculateInstallmentAmount(
            Integer numberOfInstallments,
            Double loanAmount,
            Double monthlyInterestRate
    ) {
        var factor = Math.pow(1 + monthlyInterestRate, -numberOfInstallments);
        return loanAmount * monthlyInterestRate/ (1 - factor);
    }
}
