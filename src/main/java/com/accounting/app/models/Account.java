package com.accounting.app.models;

import com.accounting.app.models.enums.Currency;
import com.accounting.app.models.enums.TypeOfAccount;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "نام حساب نمی تواند خالی باشد.")
    private String accountName;
    @NotBlank(message = "شماره حساب نمی تواند خالی باشد.")
    private String accountNumber;
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_BANK")
    private Bank accountBank;
    @Enumerated(EnumType.STRING)
    private TypeOfAccount typeOfAccount;
    @Enumerated(EnumType.STRING)
    private Currency accountCurrency;
    @ManyToOne
    @JoinColumn(name = "CREATOR_USER")
    private User creatorUser;
    private BigDecimal accountBalance;
    @CreationTimestamp
    private LocalDateTime createdAt;


    public Account(String accountName, String accountNumber, Bank accountBank, TypeOfAccount typeOfAccount, Currency accountCurrency,User creatorUser,BigDecimal accountBalance){
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.accountBank = accountBank;
        this.typeOfAccount = typeOfAccount;
        this.accountCurrency = accountCurrency;
        this.creatorUser = creatorUser;
        this.accountBalance = accountBalance;
    }



}
