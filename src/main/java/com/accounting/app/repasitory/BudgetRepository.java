package com.accounting.app.repasitory;

import com.accounting.app.models.Budget;
import com.accounting.app.models.Category;
import com.accounting.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget,Long> {
    Optional<Budget> findBudgetByUserAndMonthAndYear(User user, Integer month, Integer year);

    List<Budget> findBudgetsByCategory(Category category);

    Optional<Budget> findBudgetByUser_IdAndMonthAndYear(Long userId, Integer month, Integer year);
}
