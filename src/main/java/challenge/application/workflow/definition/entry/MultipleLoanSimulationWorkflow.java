package challenge.application.workflow.definition.entry;

import challenge.application.workflow.activity.entry.multiple.handler.MultipleLoanSimulationUnexpectedExceptionHandler;
import challenge.application.workflow.definition.entry.asyncprocess.LoanSimulationAsyncProcessWorkflow;
import challenge.model.LoanSimulationData;
import challenge.model.LoanSimulationItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

import static challenge.domain.constants.AppConstants.ConfigConstants.MAX_CONCURRENT_THREADS;

@Component
@RequiredArgsConstructor
public class MultipleLoanSimulationWorkflow {

    private final LoanSimulationAsyncProcessWorkflow loanSimulationAsyncProcessWorkflow;
    private final MultipleLoanSimulationUnexpectedExceptionHandler multipleLoanSimulationUnexpectedExceptionHandler;

    public List<LoanSimulationItemResponse> executeAsync(List<LoanSimulationData> loanSimulationDataList) {
        try (var executor = Executors.newFixedThreadPool(MAX_CONCURRENT_THREADS)) {
            var futures = loanSimulationDataList.stream()
                    .map(simulation ->
                            CompletableFuture.supplyAsync(
                                    () -> loanSimulationAsyncProcessWorkflow.execute(simulation),
                                    executor
                            )
                    )
                    .toList();

            return futures.stream()
                    .map(CompletableFuture::join)
                    .toList();
        } catch (Exception ex) {
            return multipleLoanSimulationUnexpectedExceptionHandler.handleException(ex);
        }
    }
}
