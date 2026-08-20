package com.accounting.app.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankResponse {
    private Long id;
    private String name;
    private Long code;
    private Boolean active;
}
