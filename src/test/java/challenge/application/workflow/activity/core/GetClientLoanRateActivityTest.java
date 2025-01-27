package challenge.application.workflow.activity.core;

import challenge.application.service.InterestRateService;
import challenge.application.util.DateUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static challenge.application.util.DateUtils.getClientAge;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetClientLoanRateActivityTest {

    @InjectMocks
    private GetClientLoanRateActivity unit;

    @Mock
    private InterestRateService interestRateService;

    @Test
    @DisplayName("Deve obter idade de cliente e em seguida obter taxa de empréstimo")
    void executeTest() {
        try(var mockedStatic = Mockito.mockStatic(DateUtils.class)) {
            var clientBirthDate = "12/10/1998";
            var age = 26;
            mockedStatic.when(() -> getClientAge(clientBirthDate)).thenReturn(age);

            var expected = 0.03;
            when(interestRateService.getInterestRate(age)).thenReturn(expected);
            assertEquals(expected, unit.execute(clientBirthDate));
        }
    }
}