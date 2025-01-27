package challenge.application.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static challenge.application.util.CurrencyUtil.formatToBRL;
import static challenge.domain.constants.AppConstants.CurrencyConstants.ZERO_BRL;
import static org.junit.jupiter.api.Assertions.*;

class CurrencyUtilTest {

    @Test
    @DisplayName("Deve retornar zero reais formatado quando o valor é nulo")
    void formatToBRLTestCase01() {
        assertEquals(ZERO_BRL, formatToBRL(null));
    }

    @Test
    @DisplayName("Deve retornar valor em reais arrendondando uma casa decimal para cima")
    void formatToBRLTestCase02() {
        var expected = "R$ 1.295,05";
        var input = 1295.046547865;
        assertEquals(expected, formatToBRL(input));
    }
}