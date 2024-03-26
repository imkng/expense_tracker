package in.krishna.expensetrackerapi.service;

import in.krishna.expensetrackerapi.entity.Expense;
import in.krishna.expensetrackerapi.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private ExpenseRepository expenseRepository;
    @Override
    public List<Expense> getAllListOfExpenses() {
        return expenseRepository.findAll();
    }
}
