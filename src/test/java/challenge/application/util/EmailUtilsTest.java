package challenge.application.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailUtilsTest {

    @Test
    @DisplayName("Deve retornar true para e-mail com formato válido")
    void isValidEmailTestCase01() {
        assertTrue(EmailUtils.isValidEmail("test@email.com"));
    }

    @Test
    @DisplayName("Deve retornar false para e-mail sem parte inicial")
    void isValidEmailTestCase02() {
        assertFalse(EmailUtils.isValidEmail("@email.com"));
    }

    @Test
    @DisplayName("Deve retornar false para e-mail sem arroba")
    void isValidEmailTestCase03() {
        assertFalse(EmailUtils.isValidEmail("testeemail.com"));
    }

    @Test
    @DisplayName("Deve retornar false para e-mail sem dominio")
    void isValidEmailTestCase04() {
        assertFalse(EmailUtils.isValidEmail("teste@.com"));
    }

    @Test
    @DisplayName("Deve retornar false para e-mail sem ponto")
    void isValidEmailTestCase05() {
        assertFalse(EmailUtils.isValidEmail("teste@exemplecom"));
    }
}