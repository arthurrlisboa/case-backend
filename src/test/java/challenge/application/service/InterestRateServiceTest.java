package challenge.application.service;

import challenge.domain.enums.RatePerAgeGroupEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InterestRateServiceTest {

    private InterestRateService interestRateService;

    @BeforeEach
    void setUp() {
        interestRateService = new InterestRateService();
    }

    @Test
    @DisplayName("Deve retornar a taxa correta para um cliente com menos de 25 anos")
    void getInterestRateCase01() {
        var expectedRate = RatePerAgeGroupEnum.UP_TO_25_YEARS.getRate();
        var output = interestRateService.getInterestRate(24);

        assertEquals(expectedRate, output);
    }

    @Test
    @DisplayName("Deve retornar a taxa correta para um cliente de 25 anos")
    void getInterestRateCase02() {
        var expectedRate = RatePerAgeGroupEnum.UP_TO_25_YEARS.getRate();
        var output = interestRateService.getInterestRate(25);

        assertEquals(expectedRate, output);
    }

    @Test
    @DisplayName("Deve retornar a taxa correta para um cliente com mais de 25 anos e menos de 40")
    void getInterestRateCase03() {
        var expectedRate = RatePerAgeGroupEnum.FROM_26_TO_40_YEARS.getRate();
        var output = interestRateService.getInterestRate(27);

        assertEquals(expectedRate, output);
    }

    @Test
    @DisplayName("Deve retornar a taxa correta para um cliente de 25 anos")
    void getInterestRateCase04() {
        var expectedRate = RatePerAgeGroupEnum.FROM_26_TO_40_YEARS.getRate();
        var output = interestRateService.getInterestRate(40);

        assertEquals(expectedRate, output);
    }

    @Test
    @DisplayName("Deve retornar a taxa correta para um cliente com mais de 40 anos e menos de 60")
    void getInterestRateCase05() {
        var expectedRate = RatePerAgeGroupEnum.FROM_41_TO_60_YEARS.getRate();
        var output = interestRateService.getInterestRate(43);

        assertEquals(expectedRate, output);
    }

    @Test
    @DisplayName("Deve retornar a taxa correta para um cliente de 60 anos")
    void getInterestRateCase06() {
        var expectedRate = RatePerAgeGroupEnum.FROM_41_TO_60_YEARS.getRate();
        var output = interestRateService.getInterestRate(60);

        assertEquals(expectedRate, output);
    }

    @Test
    @DisplayName("Deve retornar a taxa correta para um cliente com mais de 60 anos")
    void getInterestRateCase07() {
        var expectedRate = RatePerAgeGroupEnum.ABOVE_60_YEARS.getRate();
        var output = interestRateService.getInterestRate(61);

        assertEquals(expectedRate, output);
    }
}