package in.krishna.expensetrackerapi.repository;

import in.krishna.expensetrackerapi.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Page<Expense> findByUserId(Long user_id, Pageable pageable);

    Optional<Expense> findByUserIdAndId(Long userId, Long expenseId);
}
