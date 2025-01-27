package integration;

import challenge.application.exceptions.handler.InvalidLoanExceptionHandler;
import challenge.controller.LoanController;
import challenge.model.LoanSimulationData;
import challenge.model.LoanSimulationErrorResponse;
import challenge.model.LoanSimulationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static integration.fixture.TestUtils.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LoanControllerTest extends ApplicationTest {

    private MockMvc mockMvc;

    @Autowired
    private LoanController controller;

    private static final String LOAN_SIMULATION_URL = "/loan/simulation";
    private static final String LOAN_SIMULATION_LIST_URL = "/loan/simulation/list";

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new InvalidLoanExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("Deve calcular simulação de empréstimo e retornar resposta de sucesso com status 200")
    void processLoanSimulationTestCase01() throws Exception {
        var userEmail = "user@email.com";
        var loanAmount = 5000.00;
        var clientBirthDate = "15/05/1985";
        var paymentTermMonths = 12;

        var totalPaymentAmount = "R$ 6.027,73";
        var monthlyInstallmentAmount= "R$ 502,32";
        var totalInterestPaid = "R$ 1.027,73";

        var loanSimulationData = new LoanSimulationData(userEmail, loanAmount, clientBirthDate, paymentTermMonths);
        var loanSimulationExpectedResponse = new LoanSimulationResponse()
                .userEmail(userEmail)
                .totalPaymentAmount(totalPaymentAmount)
                .monthlyInstallmentAmount(monthlyInstallmentAmount)
                .totalInterestPaid(totalInterestPaid);

        mockMvc.perform(getRequest(LOAN_SIMULATION_URL, getJson(loanSimulationData)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(getJson(loanSimulationExpectedResponse)));
    }

    @Test
    @DisplayName("Deve retornar status 400 com mensagem de erro de email inválido")
    void processLoanSimulationTestCase02() throws Exception {
        var userEmail = "useremail.com";
        var loanAmount = 5000.00;
        var clientBirthDate = "15/05/1985";
        var paymentTermMonths = 12;

        var loanSimulationData = new LoanSimulationData(userEmail, loanAmount, clientBirthDate, paymentTermMonths);

        var expectedErrorMessage = "Invalid loan simulation request data: The email address is invalid." +
                " Please provide a valid email address";

        var loanSimulationExpectedResponse = new LoanSimulationErrorResponse().error(expectedErrorMessage);

        mockMvc.perform(getRequest(LOAN_SIMULATION_URL, getJson(loanSimulationData)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(getJson(loanSimulationExpectedResponse)));
    }

    @Test
    @DisplayName("Deve retornar status 400 com mensagem de erro de todos parâmetros inválidos")
    void processLoanSimulationTestCase03() throws Exception {
        var userEmail = "useremail.com";
        var loanAmount = -5000.00;
        var clientBirthDate = "15-05-1985";
        var paymentTermMonths = 1;

        var loanSimulationData = new LoanSimulationData(userEmail, loanAmount, clientBirthDate, paymentTermMonths);

        var expectedErrorMessage = "Invalid loan simulation request data: The email address is invalid. " +
                "Please provide a valid email address || The birth date format is invalid. " +
                "Please use the format DD/MM/YYYY || The loan amount must be greater than R$ 50,00 ||" +
                " The loan term must be at least 2 months. Please provide a valid term";

        var loanSimulationExpectedResponse = new LoanSimulationErrorResponse().error(expectedErrorMessage);

        mockMvc.perform(getRequest(LOAN_SIMULATION_URL, getJson(loanSimulationData)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(getJson(loanSimulationExpectedResponse)));
    }

    @Test
    @DisplayName("Deve retornar status 200 com todos as simulações presentes")
    void processLoanSimulationListTestCase01() throws Exception {
        var input = loadJsonFromFile("src/test/java/integration/fixture/simulations_10000.json");
        var output = loadJsonFromFile("src/test/java/integration/fixture/simulations_response.json");

        mockMvc.perform(getRequest(LOAN_SIMULATION_LIST_URL, input))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(output));
    }

    @Test
    @DisplayName("Deve retornar status 200 com todos as simulações presentes mas respostas devem conter erro de validação")
    void processLoanSimulationListTestCase02() throws Exception {
        var input = loadJsonFromFile("src/test/java/integration/fixture/simulations_invalid_data.json");
        var output = loadJsonFromFile("src/test/java/integration/fixture/simulations_invalid_response.json");

        mockMvc.perform(getRequest(LOAN_SIMULATION_LIST_URL, input))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(output));
    }
}
