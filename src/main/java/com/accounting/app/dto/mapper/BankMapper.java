package com.accounting.app.dto.mapper;

import com.accounting.app.dto.BankRequest;
import com.accounting.app.dto.BankResponse;
import com.accounting.app.models.Bank;
import org.springframework.stereotype.Component;

@Component
public class BankMapper {

    public Bank toEntity(BankRequest bankRequest) {
        return new Bank(
                bankRequest.getName(),
                bankRequest.getCode()
        );
    }
    public BankResponse toResponse(Bank bank) {
        return new BankResponse(
                bank.getId(),
                bank.getName(),
                bank.getCode(),
                bank.getActive()
        );
    }
}
