package challenge.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RatePerAgeGroupEnum {

    UP_TO_25_YEARS(25, 0.05),
    FROM_26_TO_40_YEARS(40, 0.03),
    FROM_41_TO_60_YEARS(60, 0.02),
    ABOVE_60_YEARS(Integer.MAX_VALUE, 0.04);

    private final int upperLimit;
    private final double rate;
}
