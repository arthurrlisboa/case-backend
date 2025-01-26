package challenge.domain.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Objects;

@UtilityClass
public class CurrencyUtil {

    public static String formatToBRL(Double value) {
        if (Objects.isNull(value)) {
            return "R$ 0,00";
        }

        var roundedValue = BigDecimal.valueOf(value).setScale(2, RoundingMode.CEILING);
        var decimalFormat = new DecimalFormat("¤ #,##0.00");
        decimalFormat.setCurrency(Currency.getInstance("BRL"));

        return decimalFormat.format(roundedValue);
    }
}
