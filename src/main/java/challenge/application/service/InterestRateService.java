package challenge.application.service;

import challenge.domain.enums.RatePerAgeGroupEnum;
import challenge.application.exceptions.RateByAgeGroupNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class InterestRateService {

    public Double getInterestRate(Integer clientAge) {
        return Arrays.stream(RatePerAgeGroupEnum.values())
                .filter(group -> clientAge.compareTo(group.getUpperLimit()) <= 0)
                .findFirst()
                .map(RatePerAgeGroupEnum::getRate)
                .orElseThrow(RateByAgeGroupNotFoundException::new);
    }
}
