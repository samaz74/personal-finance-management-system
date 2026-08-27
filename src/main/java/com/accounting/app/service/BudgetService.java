package com.accounting.app.service;

import com.accounting.app.dto.BudgetRequest;
import com.accounting.app.dto.BudgetResponse;
import com.accounting.app.dto.mapper.BudgetMapper;
import com.accounting.app.exeption.ResourceNotFoundExeption;
import com.accounting.app.models.Budget;
import com.accounting.app.repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class BudgetService {
    private final BudgetRepository budgetRepository;
    private final BudgetMapper budgetMapper;
    private final UserService userService;
    private final TransactionService transactionService;

    public BudgetService(BudgetRepository budgetRepository, BudgetMapper budgetMapper, UserService userService, TransactionService transactionService) {
        this.budgetRepository = budgetRepository;
        this.budgetMapper = budgetMapper;
        this.userService = userService;
        this.transactionService = transactionService;
    }
    public Budget getbudgetByIdEntity(long id) {
        return budgetRepository.findById(id).orElseThrow(()-> new ResourceNotFoundExeption("بودجه یافت نشد."));
    }
    public BudgetResponse getBudgetById(long id) {
        return budgetMapper.toResponse(getbudgetByIdEntity(id));
    }
    public BudgetResponse addBudget(BudgetRequest budgetRequest, Long userId) {
        return budgetMapper.toResponse(budgetRepository.save(budgetMapper.toEntity(budgetRequest,userService.getUserByIdEntity(userId))));
    }
    public BudgetResponse updateBudget(BudgetRequest budgetRequest, Long userId, Long budgetId) {
        Budget budget = budgetMapper.toEntity(budgetRequest,userService.getUserByIdEntity(userId));
        budget.setId(budgetId);
        return budgetMapper.toResponse(budgetRepository.save(budget));
    }
    public BudgetResponse getUserBudgetById(long userId) {
        return budgetMapper.toResponse(budgetRepository.findBudgetByUser_IdAndMonthAndYear(userId, LocalDateTime.now().getMonth().getValue(), LocalDateTime.now().getYear()).orElseThrow(()-> new ResourceNotFoundExeption("بودجه یافت نشد.")));
    }
    public Budget getUserBudgetByMonthAndYear(Long userId, Integer month, Integer year) {
        return budgetRepository.findBudgetByUserAndMonthAndYear(userService.getUserByIdEntity(userId),month,year).orElseThrow(()-> new ResourceNotFoundExeption("بودجه یافت نشد."));
    }
    public BigDecimal BudgetStatus(long userId, Integer month, Integer year) {
        BigDecimal used = transactionService.getTransactionsForMonthAndYear(userId, month, year);
        BigDecimal budget = getUserBudgetByMonthAndYear(userId,month,year).getMonthBudget();
        return budget.subtract(used);




    }
}
