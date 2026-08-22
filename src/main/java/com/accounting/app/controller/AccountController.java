package com.accounting.app.controller;

import com.accounting.app.dto.AccountRequest;
import com.accounting.app.dto.AccountResponse;
import com.accounting.app.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("user/{userId}")
    public List<AccountResponse> UserAccount(@PathVariable Long userId){
        return accountService.getAccountsByUserId(userId);
    }

    @GetMapping("/{id}")
    public AccountResponse getAccountById(@PathVariable Long id){
        return accountService.getAccountById(id);
    }

    @PostMapping("/{userId}")
    public AccountResponse creatAccount (@RequestBody AccountRequest accountRequest, @PathVariable Long userId){
        return accountService.createAccount(accountRequest,userId );
    }
    @PutMapping("/{id}")
    public AccountResponse updateAccount(@RequestBody AccountRequest accountRequest , @PathVariable Long id){
        return accountService.updateAccount(accountRequest,id);
    }

    @DeleteMapping("{id}")
    public void deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
    }



}
