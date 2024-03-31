package in.krishna.expensetrackerapi.service;

import in.krishna.expensetrackerapi.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExpenseService {
    Page<Expense> getAllListOfExpenses(Pageable pageable);

    Expense saveExpenseDetails(Expense expense);

    Expense getExpenseById(Long id);

    void deleteExpenseById(Long id);

    Expense updateExpenseDetails(Long id, Expense expense);
}
