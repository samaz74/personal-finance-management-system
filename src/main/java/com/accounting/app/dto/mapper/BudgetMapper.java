package com.accounting.app.dto.mapper;


import com.accounting.app.dto.BudgetRequest;
import com.accounting.app.dto.BudgetResponse;
import com.accounting.app.models.Budget;
import com.accounting.app.models.User;
import com.accounting.app.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class BudgetMapper {
    private final CategoryService categoryService;
    public BudgetMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    public Budget toEntity(BudgetRequest budgetRequest, User user) {
        return new Budget(
                categoryService.getCategoryByIdEntity(budgetRequest.getCategoryId()),
                budgetRequest.getMonthBudget(),
                budgetRequest.getYear(),
                budgetRequest.getMonth(),
                user
        );
    }
    public BudgetResponse toResponse(Budget budget) {
        return new BudgetResponse(
                budget.getId(),
                budget.getCategory().getId(),
                budget.getCategory().getName(),
                budget.getMonthBudget(),
                budget.getYear(),
                budget.getMonth(),
                budget.getUser().getId(),
                budget.getUser().getFirstName().concat(" " + budget.getUser().getLastName()),
                budget.getCreatedAt()
        );
    }
}
