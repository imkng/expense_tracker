package in.krishna.expensetrackerapi.service;

import in.krishna.expensetrackerapi.entity.Expense;

import java.util.List;

public interface ExpenseService {
    List<Expense> getAllListOfExpenses();
}
