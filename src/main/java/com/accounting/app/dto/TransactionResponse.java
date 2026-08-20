package com.accounting.app.dto;

import com.accounting.app.models.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionResponse {
    private Long Id;
    private Long categoryId;
    private String categoryName;
    private Long mainAccountId;
    private String mainAccountName;
    private Long receiverAccountId;
    private String receiverAccountName;
    private BigDecimal amount;
    private String description;
    private TransactionType transactionType;
    private Long userId;
    private String userName;
    private LocalDateTime createdAt;
}
