package com.accounting.app.controller;

import com.accounting.app.dto.AccountRequest;
import com.accounting.app.dto.AccountResponse;
import com.accounting.app.exeption.AccessDeniedExeption;
import com.accounting.app.service.AccountService;
import com.accounting.app.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;
    private final UserService userService;

    public AccountController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<AccountResponse> UserAccount(Principal principal){
        return accountService.getAccountsByUserId(userService.getUserByEmailEntity(principal.getName()).getId());
    }

    @GetMapping("/{id}")
    public AccountResponse getAccountById(@PathVariable Long id){
        return accountService.getAccountById(id);
    }

    @PostMapping("")
    public AccountResponse creatAccount (@RequestBody AccountRequest accountRequest, Principal principal){
        return accountService.createAccount(accountRequest,userService.getUserByEmailEntity(principal.getName()).getId() );
    }
    @PutMapping("/{accountId}")
    public AccountResponse updateAccount(@RequestBody AccountRequest accountRequest , Principal principal , @PathVariable Long accountId){
        if(accountService.isAccountOwner(userService.getUserByEmailEntity(principal.getName()).getId(),accountId))
            return accountService.updateAccount(accountRequest,accountId);
        else
            throw new AccessDeniedExeption("شما دسترسی ندارد.");
    }

    @DeleteMapping("{id}")
    public void deleteAccount(@PathVariable Long id, Principal principal){
        if(accountService.isAccountOwner(userService.getUserByEmailEntity(principal.getName()).getId(),id))
            accountService.deleteAccount(id);
        else
            throw new AccessDeniedExeption("شما دسترسی ندارد.");
    }



}
