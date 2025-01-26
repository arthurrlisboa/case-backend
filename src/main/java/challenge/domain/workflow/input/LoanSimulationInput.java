package challenge.domain.workflow.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanSimulationInput {

    private String userEmail;
    private Double loanAmount;
    private String clientBirthDate;
    private Integer paymentTermMonths;
}
