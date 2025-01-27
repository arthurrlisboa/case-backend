package challenge.application.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Objects;

import static challenge.domain.constants.AppConstants.CurrencyConstants.*;

@UtilityClass
public class CurrencyUtil {

    public static String formatToBRL(Double value) {
        if (Objects.isNull(value)) {
            return ZERO_BRL;
        }

        var roundedValue = BigDecimal.valueOf(value).setScale(2, RoundingMode.CEILING);
        var decimalFormat = new DecimalFormat(CURRENCY_PATTERN_BRL);
        decimalFormat.setCurrency(Currency.getInstance(CURRENCY_CODE_BRL));

        return decimalFormat.format(roundedValue);
    }
}
