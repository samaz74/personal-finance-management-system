package com.accounting.app.dto;


import com.accounting.app.models.enums.Currency;
import com.accounting.app.models.enums.TypeOfAccount;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AccountRequest {
    @NotBlank(message = "نام حساب نمی تواند خالی باشد.")
    private String accountName;
    @NotBlank(message = "شماره حساب نمی تواند خالی باشد.")
    private String accountNumber;
    private Long accountBankId;
    private TypeOfAccount typeOfAccount;
    private Currency accountCurrency;
    private BigDecimal accountBalance;
}
