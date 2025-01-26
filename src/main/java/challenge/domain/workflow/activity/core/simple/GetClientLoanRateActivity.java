package challenge.domain.workflow.activity.core.simple;

import challenge.domain.service.InterestRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static challenge.domain.util.DateUtils.getClientAge;

@Component
@RequiredArgsConstructor
public class GetClientLoanRateActivity {

    private final InterestRateService interestRateService;

    public Double execute(String clientBirthDate) {
        var clientAge = getClientAge(clientBirthDate);
        return interestRateService.getInterestRate(clientAge);
    }
}
