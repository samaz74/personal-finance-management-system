package com.accounting.app.controller;

import com.accounting.app.dto.BudgetRequest;
import com.accounting.app.dto.BudgetResponse;
import com.accounting.app.service.BudgetService;
import com.accounting.app.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService budgetService;
    private final UserService userService;

    public BudgetController(BudgetService budgetService, UserService userService) {
        this.budgetService = budgetService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public BudgetResponse getBudgetWithId(@PathVariable Long id){
        return budgetService.getBudgetById(id);
    }
    @GetMapping("/current")
    public BudgetResponse getCurrentUserBudget(Principal principal){
        return budgetService.getUserBudgetById(userService.getUserByEmailEntity(principal.getName()).getId());
    }
    @GetMapping("/status")
    public BigDecimal getStatusOfBudget(Principal principal){
        return budgetService.BudgetStatus(userService.getUserByEmailEntity(principal.getName()).getId(), LocalDateTime.now().getMonthValue(),LocalDateTime.now().getYear());
    }

    @PostMapping("/")
    public BudgetResponse createBudget(@RequestBody BudgetRequest budgetRequest,Principal principal){
        return budgetService.addBudget(budgetRequest, userService.getUserByEmailEntity(principal.getName()).getId());
    }
    @PutMapping("/{id}")
    public BudgetResponse updateBudget(@RequestBody BudgetRequest budgetRequest,@PathVariable Long id,Principal principal){
        return budgetService.updateBudget(budgetRequest,userService.getUserByEmailEntity(principal.getName()).getId(),id);
    }
}
