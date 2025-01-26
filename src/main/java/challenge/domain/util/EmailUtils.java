package challenge.domain.util;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

import static challenge.domain.constants.AppConstants.EmailConstants.VALID_EMAIL_REGEX;

@UtilityClass
public class EmailUtils {

    public static boolean isValidEmail(String email) {
        var pattern = Pattern.compile(VALID_EMAIL_REGEX);
        var matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
