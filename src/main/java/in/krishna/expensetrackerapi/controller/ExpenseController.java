package in.krishna.expensetrackerapi.controller;

import in.krishna.expensetrackerapi.entity.Expense;
import in.krishna.expensetrackerapi.entity.ExpenseModel;
import in.krishna.expensetrackerapi.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    @Operation(summary = "This is to fetch loggedIn user expenses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Fetched all expense from Db",
            content = {@Content(mediaType = "application/json")}),

            @ApiResponse(responseCode = "404",
            description = "Not Available",
            content = @Content)
    })
    public List<Expense> getAllExpenses(Pageable pageable) {
        return expenseService.getAllListOfExpenses(pageable).toList();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/expenses")
    public Expense saveExpenseDetails(@Valid @RequestBody ExpenseModel expense) {
        return expenseService.saveExpenseDetails(expense);
    }

    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable Long id){
        return expenseService.getExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses")
    public void deleteExpenseById(@RequestParam Long id) {
        expenseService.deleteExpenseById(id);
    }

    @PutMapping("/expenses/{id}")
    public Expense updateExpenseDetails(@RequestBody ExpenseModel expenseModel, @PathVariable Long id){
        return expenseService.updateExpenseDetails(id, expenseModel);
    }


}
