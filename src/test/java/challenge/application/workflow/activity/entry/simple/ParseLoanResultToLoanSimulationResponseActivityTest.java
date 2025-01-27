package challenge.application.workflow.activity.entry.simple;

import challenge.application.workflow.output.LoanSimulationResult;
import challenge.domain.mappers.LoanMapper;
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
class ParseLoanResultToLoanSimulationResponseActivityTest {

    @InjectMocks
    private ParseLoanResultToLoanSimulationResponseActivity unit;

    @Mock
    private LoanMapper loanMapper;

    @Mock
    private LoanSimulationResult loanSimulationResult;

    @Mock
    private LoanSimulationResponse loanSimulationResponse;

    @Test
    @DisplayName("Deve fazer o parse do resultado para resposta")
    void executeTestCase() {
        var userEmail = "user@example.com";
        when(loanMapper.toLoanSimulationResponse(loanSimulationResult, userEmail)).thenReturn(loanSimulationResponse);

        var output = unit.execute(loanSimulationResult, userEmail);

        assertEquals(loanSimulationResponse, output);
    }
}