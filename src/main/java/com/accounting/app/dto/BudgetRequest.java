package com.accounting.app.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class BudgetRequest {
    private Long categoryId;
    private BigDecimal monthBudget;
    private Integer year;
    private Integer month;
}
