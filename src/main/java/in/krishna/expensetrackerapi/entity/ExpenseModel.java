package in.krishna.expensetrackerapi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class ExpenseModel {
    @NotBlank(message = "Expense name must not be null")
    @Size(min = 3, message = "Expense name must be at least 3 characters")
    private String name;


    private String description;

    @NotNull(message = "Expense amount should not be null")
    private BigDecimal amount;

    @NotBlank(message = "Category should not be null")
    private String category;
}
