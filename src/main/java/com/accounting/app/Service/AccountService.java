package com.accounting.app.Service;

import com.accounting.app.exeption.ResourceNotFoundExeption;
import com.accounting.app.models.Account;
import com.accounting.app.repasitory.AccountRepository;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundExeption("حساب یافت نشد."));
    }
}
