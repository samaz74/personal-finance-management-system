package com.accounting.app.Service;

import com.accounting.app.exeption.ResourceNotFoundExeption;
import com.accounting.app.models.Bank;
import com.accounting.app.repasitory.BankRepository;
import org.springframework.stereotype.Service;

@Service
public class BankService {
    private final BankRepository bankRepository;
    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }
    public Bank getBankById(Long id) {
        return bankRepository.findById(id).orElseThrow(()-> new ResourceNotFoundExeption("بانک یافت نشد."));
    }
}
