package challenge.application.workflow.activity.entry;

import challenge.application.workflow.input.LoanSimulationInput;
import challenge.domain.mappers.LoanMapper;
import challenge.model.LoanSimulationData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParseLoanSimulationDataToInputActivityTest {

    @InjectMocks
    private ParseLoanSimulationDataToInputActivity unit;

    @Mock
    private LoanMapper loanMapper;

    @Mock
    private LoanSimulationData loanSimulationData;

    @Mock
    private LoanSimulationInput loanSimulationInput;

    @Test
    @DisplayName("Deve retornar o parse do request para o input")
    void executeTest() {
        when(loanMapper.toLoanSimulationInput(loanSimulationData)).thenReturn(loanSimulationInput);

        assertEquals(loanSimulationInput, unit.execute(loanSimulationData));
    }
}