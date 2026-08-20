package com.accounting.app.dto;


import com.accounting.app.models.enums.Currency;
import com.accounting.app.models.enums.TypeOfAccount;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AccountResponse {
    private Long id;
    private String accountName;
    private String accountNumber;
    private Long accountBankId;
    private String accountBankName;
    private TypeOfAccount typeOfAccount;
    private Currency accountCurrency;
    private Long creatorUserId;
    private String creatorUserName;
    private BigDecimal accountBalance;
    private LocalDateTime createdAt;
}
