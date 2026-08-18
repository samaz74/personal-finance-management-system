package com.accounting.app.repasitory;

import com.accounting.app.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankRepository extends JpaRepository<Bank,Long> {
    List<Bank> findBanksByNameContaining(String name);
}
