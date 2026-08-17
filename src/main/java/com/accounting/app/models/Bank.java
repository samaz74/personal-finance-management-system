package com.accounting.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (unique = true)
    private String name;
    @NotNull(message = "کد بانک اجباری است.")
    @Column (unique = true)
    private Long code;
    private Boolean active = true;
    public Bank(String name, Long code){
        this.name = name;
        this.code = code;
    }

}
