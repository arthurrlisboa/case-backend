package challenge.application.workflow.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanSimulationResult {

    private Double totalPaymentAmount;
    private Double monthlyInstallmentAmount;
    private Double totalInterestPaid;
}
