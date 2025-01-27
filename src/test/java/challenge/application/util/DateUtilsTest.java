package challenge.application.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static challenge.application.util.DateUtils.isValidDateFormatDdMmYyyy;
import static challenge.domain.constants.AppConstants.DateConstants.DATE_FORMAT;
import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    @DisplayName("Deve retornar a idade correta para cliente")
    void getClientAge() {
        var expectedAge = 22;
        var birthDateLocalDate = LocalDate.now().minusYears(expectedAge);
        var birthDateStr = convertToString(birthDateLocalDate);

        assertEquals(expectedAge, DateUtils.getClientAge(birthDateStr));
    }

    public static String convertToString(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return localDate.format(formatter);
    }

    @Test
    @DisplayName("Deve retornar true para data no formato dd/mm/yyyy")
    void isValidDateFormatDdMmYyyyTestCase01() {
        assertTrue(isValidDateFormatDdMmYyyy("10/11/1978"));
    }

    @Test
    @DisplayName("Deve retornar false para data no formato inválido")
    void isValidDateFormatDdMmYyyyTestCase02() {
        assertFalse(isValidDateFormatDdMmYyyy("10-11-1978"));
    }

    @Test
    @DisplayName("Deve retornar false para data com dia inválido")
    void isValidDateFormatDdMmYyyyTestCase03() {
        assertFalse(isValidDateFormatDdMmYyyy("43/11/1978"));
    }

    @Test
    @DisplayName("Deve retornar false para data com mês inválido")
    void isValidDateFormatDdMmYyyyTestCase04() {
        assertFalse(isValidDateFormatDdMmYyyy("13/13/1978"));
    }

    @Test
    @DisplayName("Deve retornar false para data com ano inválido")
    void isValidDateFormatDdMmYyyyTestCase05() {
        assertFalse(isValidDateFormatDdMmYyyy("12/10/0"));
    }
}