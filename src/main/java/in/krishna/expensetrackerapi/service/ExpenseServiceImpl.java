package in.krishna.expensetrackerapi.service;

import in.krishna.expensetrackerapi.entity.Expense;
import in.krishna.expensetrackerapi.entity.User;
import in.krishna.expensetrackerapi.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Expense> getAllListOfExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense saveExpenseDetails(Expense expense) {
        User user = userService.getLoggedInUser();
        expense.setUser(user);
        return expenseRepository.save(expense);
    }
}
