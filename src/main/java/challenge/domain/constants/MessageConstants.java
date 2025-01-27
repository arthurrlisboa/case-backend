package challenge.domain.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageConstants {
     public static final String MESSAGE_DELIMITER = " || ";

     public static final String INVALID_LOAN_SIMULATION_DATA_MESSAGE_TEMPLATE = "Invalid loan simulation request data: ";
     public static final String UNEXPECTED_LOAN_SIMULATION_ERROR_MESSAGE_TEMPLATE =
             "Unexpected error processing loan simulations: ";
}
