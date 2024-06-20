package in.krishna.expensetrackerapi.service;

import in.krishna.expensetrackerapi.entity.Expense;
import in.krishna.expensetrackerapi.entity.ExpenseModel;
import in.krishna.expensetrackerapi.entity.User;
import in.krishna.expensetrackerapi.repository.ExpenseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
    public Expense saveExpenseDetails(ExpenseModel expenseModel) {
        Expense expense = new Expense();
        expense.setName(expenseModel.getName());
        expense.setAmount(expenseModel.getAmount());
        expense.setDescription(expenseModel.getDescription());
        expense.setCategory(expenseModel.getCategory());
        User user = userService.getLoggedInUser();
        user.setBudget(user.getBudget().subtract(expenseModel.getAmount()));
        userService.updateUser(user);
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

    public Expense updateExpenseDetails(Long id, ExpenseModel expenseModel){
        Expense existingExpense = getExpenseById(id);
        existingExpense.setName(expenseModel.getName() != null ? expenseModel.getName() : existingExpense.getName());
        existingExpense.setDescription(expenseModel.getDescription() != null ? expenseModel.getDescription() : existingExpense.getDescription());
        existingExpense.setCategory(expenseModel.getCategory() != null ? expenseModel.getCategory() : existingExpense.getCategory());
//        existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());
        existingExpense.setAmount(expenseModel.getAmount() != null ? expenseModel.getAmount() : existingExpense.getAmount());
        existingExpense.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return expenseRepository.save(existingExpense);
    }
}
