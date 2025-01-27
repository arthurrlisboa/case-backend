package challenge.application.workflow.activity.entry.multiple.item;

import challenge.application.workflow.output.LoanSimulationResult;
import challenge.domain.mappers.LoanMapper;
import challenge.model.LoanSimulationItemResponse;
import challenge.model.LoanSimulationResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParseResultToLoanSimulationItemResponseActivityTest {

    @InjectMocks
    private ParseResultToLoanSimulationItemResponseActivity unit;

    @Mock
    private LoanMapper loanMapper;

    @Mock
    private LoanSimulationResult loanSimulationResult;

    @Mock
    private LoanSimulationResponse loanSimulationResponse;

    @Test
    @DisplayName("Deve retornar parse de response de sucesso")
    void executeTest() {
        var userEmail = "user@example.com";
        var expected = new LoanSimulationItemResponse()
                .success(true)
                .loanSimulationResponse(loanSimulationResponse);
        when(loanMapper.toLoanSimulationResponse(loanSimulationResult, userEmail)).thenReturn(loanSimulationResponse);

        assertEquals(expected, unit.execute(loanSimulationResult, userEmail));
    }
}