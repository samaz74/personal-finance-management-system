package com.accounting.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne
    @JoinColumn(name = "TRANSACTION_CATEGORY")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "MAIN_ACCOUNT")
    private Account mainAccount;
    @ManyToOne
    @JoinColumn(name = "RECEIVER_ACCOUNT")
    private Account receiverAccount;
    @NotNull(message = "هزینه باید مشخص شود")
    private BigDecimal amount;
    private String description;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Transaction(Category category,Account mainAccount, Account receiverAccount,String description, BigDecimal amount){
        this.category =category;
        this.mainAccount=mainAccount;
        this.receiverAccount = receiverAccount;
        this.description= description;
        this.amount=amount;
    }


}
