package challenge.domain.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import static challenge.domain.constants.AppConstants.DateConstants.DATE_FORMAT;

@UtilityClass
public class DateUtils {

    public static LocalDate getFormattedDate(String clientBirthDate) {
        return LocalDate.parse(clientBirthDate, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static Integer getClientAge(String clientBirthDate){
        var formattedClientBirthDate = getFormattedDate(clientBirthDate);
        return Period.between(formattedClientBirthDate, LocalDate.now()).getYears();
    }
}
