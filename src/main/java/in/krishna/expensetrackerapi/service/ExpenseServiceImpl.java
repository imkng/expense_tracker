package in.krishna.expensetrackerapi.service;

import in.krishna.expensetrackerapi.entity.Expense;
import in.krishna.expensetrackerapi.entity.User;
import in.krishna.expensetrackerapi.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserService userService;


    @Override
    public Page<Expense> getAllListOfExpenses(Pageable pageable) {
        return expenseRepository.findByUserId(userService.getLoggedInUser().getId(), pageable);
    }

    @Override
    public Expense saveExpenseDetails(Expense expense) {
        User user = userService.getLoggedInUser();
        expense.setUser(user);
        return expenseRepository.save(expense);
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepository.findByUserIdAndId(userService.getLoggedInUser().getId(), id);

        if(expense.isPresent()){
            return expense.get();
        }
        throw new RuntimeException("Expense not found with the id: " + id);
    }

    @Override
    public void deleteExpenseById(Long id) {
        Expense expense = getExpenseById(id);
        expenseRepository.delete(expense);
    }

    public Expense updateExpenseDetails(Long id, Expense expense){
        Expense existingExpense = getExpenseById(id);
        existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
        existingExpense.setDescription(expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
        existingExpense.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
        existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());
        existingExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());

        return expenseRepository.save(existingExpense);
    }
}
