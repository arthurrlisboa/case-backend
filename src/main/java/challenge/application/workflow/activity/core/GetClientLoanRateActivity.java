package challenge.application.workflow.activity.core;

import challenge.application.service.InterestRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static challenge.application.util.DateUtils.getClientAge;

@Component
@RequiredArgsConstructor
public class GetClientLoanRateActivity {

    private final InterestRateService interestRateService;

    public Double execute(String clientBirthDate) {
        var clientAge = getClientAge(clientBirthDate);
        return interestRateService.getInterestRate(clientAge);
    }
}
