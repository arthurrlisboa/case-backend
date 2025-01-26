package challenge.domain.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppConstants {

    @UtilityClass
    public static class DateConstants {
        public static final String DATE_FORMAT = "dd/MM/yyyy";
    }

    @UtilityClass
    public static class CurrencyConstants {
        public static final String ZERO_BRL = "R$ 0,00";
        public static final String CURRENCY_CODE_BRL = "BRL";
        public static final String CURRENCY_PATTERN_BRL = "¤ #,##0.00";
    }
}
