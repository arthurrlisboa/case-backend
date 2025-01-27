package challenge.domain.mappers;

import challenge.application.util.CurrencyUtil;
import challenge.application.workflow.input.LoanSimulationInput;
import challenge.application.workflow.output.LoanSimulationResult;
import challenge.model.LoanSimulationData;
import challenge.model.LoanSimulationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface LoanMapper {

    LoanSimulationInput toLoanSimulationInput(LoanSimulationData loanSimulationData);

    @Mapping(target = "totalPaymentAmount", expression = "java(formatToBRL(source.getTotalPaymentAmount()))")
    @Mapping(target = "monthlyInstallmentAmount", expression = "java(formatToBRL(source.getMonthlyInstallmentAmount()))")
    @Mapping(target = "totalInterestPaid", expression = "java(formatToBRL(source.getTotalInterestPaid()))")
    @Mapping(target = "userEmail", source = "userEmail")
    LoanSimulationResponse toLoanSimulationResponse(LoanSimulationResult source, String userEmail);

    default String formatToBRL(Double amount) {
        return CurrencyUtil.formatToBRL(amount);
    }
}
