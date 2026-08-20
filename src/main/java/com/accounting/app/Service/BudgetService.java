package com.accounting.app.Service;

import com.accounting.app.exeption.ResourceNotFoundExeption;
import com.accounting.app.models.Budget;
import com.accounting.app.repasitory.BudgetRepository;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {
    private final BudgetRepository budgetRepository;
    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }
    public Budget getbudgetById(long id) {
        return budgetRepository.findById(id).orElseThrow(()-> new ResourceNotFoundExeption("بودجه یافت نشد."));
    }
}
