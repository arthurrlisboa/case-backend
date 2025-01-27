package challenge.domain.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppConstants {

    @UtilityClass
    public static class DateConstants {
        public static final String DATE_FORMAT = "dd/MM/yyyy";
        public static final String DATE_FORMAT_REGEX = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
    }

    @UtilityClass
    public static class EmailConstants {
        public static final String VALID_EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    }

    @UtilityClass
    public static class CurrencyConstants {
        public static final String ZERO_BRL = "R$ 0,00";
        public static final String CURRENCY_CODE_BRL = "BRL";
        public static final String CURRENCY_PATTERN_BRL = "¤ #,##0.00";
    }

    @UtilityClass
    public static class LoanSimulationDataConstants {
        public static final Integer MIN_PAYMENT_TERM_MONTHS = 2;
        public static final Double MIN_LOAN_AMOUNT = 50.00;
    }

    @UtilityClass
    public static class ConfigConstants {
        public static final Integer MAX_CONCURRENT_THREADS = 200;
    }
}
