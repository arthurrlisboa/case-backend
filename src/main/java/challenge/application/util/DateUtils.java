package challenge.application.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import static challenge.domain.constants.AppConstants.DateConstants.DATE_FORMAT;
import static challenge.domain.constants.AppConstants.DateConstants.DATE_FORMAT_REGEX;

@UtilityClass
public class DateUtils {

    public static LocalDate getFormattedDate(String clientBirthDate) {
        return LocalDate.parse(clientBirthDate, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static Integer getClientAge(String clientBirthDate){
        var formattedClientBirthDate = getFormattedDate(clientBirthDate);
        return Period.between(formattedClientBirthDate, LocalDate.now()).getYears();
    }

    public static boolean isValidDateFormatDdMmYyyy(String date) {
        var pattern = Pattern.compile(DATE_FORMAT_REGEX);
        var matcher = pattern.matcher(date);

        return matcher.matches();
    }
}
