package com.accounting.app.models;

import jakarta.persistence.*;
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
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "CATEGORY")
    private Category category;
    private BigDecimal monthBudget;
    private Integer year;
    private Integer month;
    @ManyToOne
    @JoinColumn(name = "USER")
    private User user;
    @CreationTimestamp
    private LocalDateTime CreatedAt;

    public Budget(Category category, BigDecimal monthBudget, Integer year, Integer month,User user){
        this.category=category;
        this.monthBudget=monthBudget;
        this.year=year;
        this.month=month;
        this.user=user;
    }
}
