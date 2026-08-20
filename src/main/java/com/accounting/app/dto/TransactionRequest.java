package com.accounting.app.dto;

import com.accounting.app.models.Account;
import com.accounting.app.models.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TransactionRequest {
    private Long categoryId;
    private Long mainAccountId;
    private Long receiverAccountId;
    @NotNull(message = "هزینه باید مشخص شود")
    private BigDecimal amount;
    private String description;
    private TransactionType transactionType;
}
